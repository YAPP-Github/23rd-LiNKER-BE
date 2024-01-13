package com.imlinker.linker.auth.application;

import com.imlinker.linker.auth.presentation.dto.request.AuthReqDto;
import com.imlinker.linker.auth.presentation.dto.response.KakaoUserDto;
import com.imlinker.linker.auth.presentation.dto.response.AuthResDto;
import com.imlinker.linker.common.exception.custom.BusinessException;
import com.imlinker.linker.common.exception.enums.ErrorMessage;
import com.imlinker.linker.config.security.JwtTokenProvider;
import com.imlinker.linker.user.domain.User;
import com.imlinker.linker.user.domain.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public AuthResDto login(AuthReqDto authReqDto){
        KakaoUserDto kakaoUserDto = getKakaoUser(authReqDto.getAccessToken());
        User user = userRepository.findBySocialId(kakaoUserDto.getSocialId())
                .orElse(signUpUser(kakaoUserDto));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getSocialId(),
                        user.getEmail()
                )
        );

        return AuthResDto.builder()
                .accessToken(jwtTokenProvider.createAccessToken(authentication))
                .refreshToken(jwtTokenProvider.createRefreshToken(authentication))
                .build();
    }

    public AuthResDto reissueToken(String refreshToken) {
        // Refresh Token 검증
        jwtTokenProvider.validateToken(refreshToken);

        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);

        String redisRefreshToken = redisTemplate.opsForValue().get(authentication.getName());
        if(!Objects.equals(redisRefreshToken, refreshToken)) {
            throw new BusinessException(ErrorMessage.NOT_EXIST_REFRESH_JWT);
        }

        return AuthResDto.builder()
                .accessToken(jwtTokenProvider.createAccessToken(authentication))
                .refreshToken(jwtTokenProvider.createRefreshToken(authentication))
                .build();
    }

    public User signUpUser(KakaoUserDto kakaoUserDto){
        return userRepository.save(User.builder()
                .socialId(kakaoUserDto.getSocialId())
                .name(kakaoUserDto.getNickname())
                .profileUrl(kakaoUserDto.getProfileUrl())
                .email(kakaoUserDto.getEmail())
                .build());
    }

    public static KakaoUserDto getKakaoUser(String token){

        String OAUTH_URL = "https://kapi.kakao.com/v2/user/me";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(token);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<KakaoUserDto> response = restTemplate.exchange(
                    OAUTH_URL,
                    HttpMethod.GET,
                    requestEntity,
                    KakaoUserDto.class
            );

            KakaoUserDto kakaoUserDto = response.getBody();
            if (kakaoUserDto == null) {
                throw new BusinessException(ErrorMessage.USER_NOT_FOUND);
            }
            return kakaoUserDto;
        } catch (HttpClientErrorException e){
            throw new BusinessException(ErrorMessage.KAKAO_TOKEN_INVALID);
        }
    }
}
