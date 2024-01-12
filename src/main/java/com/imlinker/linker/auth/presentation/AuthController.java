package com.imlinker.linker.auth.presentation;

import com.imlinker.linker.auth.application.AuthService;
import com.imlinker.linker.auth.presentation.dto.request.AuthReqDto;
import com.imlinker.linker.auth.presentation.dto.response.AuthResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<AuthResDto> login(@RequestBody AuthReqDto authReqDto){
        return ResponseEntity.ok(authService.login(authReqDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<AuthResDto> reissue(@RequestParam String refreshToken){
        return ResponseEntity.ok(authService.reissueToken(refreshToken));
    }

}
