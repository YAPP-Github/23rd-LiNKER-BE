package com.imlinker.coreapi.core.contacts;

import com.imlinker.coreapi.core.contacts.request.CreateContactRequest;
import com.imlinker.coreapi.core.contacts.request.UpdateContactRequest;
import com.imlinker.coreapi.core.contacts.response.GetContactResponse;
import com.imlinker.coreapi.core.contacts.response.GetContactsResponse;
import com.imlinker.coreapi.core.contacts.response.SearchContactResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.enums.OperationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contacts")
@Tag(name = "Contacts API", description = "연락처 관련 API")
public class ContactsController {

    @GetMapping
    @Operation(summary = "연락처 모두 가져오기")
    public ApiResponse<GetContactsResponse.Contacts> getContacts() {

        List<GetContactsResponse.SimpleContact> contacts =
                List.of(
                        new GetContactsResponse.SimpleContact(
                                1L,
                                "윤대용",
                                "https://postfiles.pstatic.net/MjAyMjA5MTdfMTE1/MDAxNjYzMzc3MDc1MTA2.bToArUww9E15OT_Mmt5mz7xAkuK98KGBbeI_dsJeaDAg.WJAhfo5kHehNQKWLEWKURBlZ7m_GZVZ9hoCBM2b_lL0g.JPEG.drusty97/IMG_0339.jpg?type=w966",
                                "프론트앤드 개발자",
                                "Yapp23기 Web1팀"),
                        new GetContactsResponse.SimpleContact(
                                2L,
                                "이우성",
                                "https://postfiles.pstatic.net/MjAyMjA5MTdfMTE1/MDAxNjYzMzc3MDc1MTA2.bToArUww9E15OT_Mmt5mz7xAkuK98KGBbeI_dsJeaDAg.WJAhfo5kHehNQKWLEWKURBlZ7m_GZVZ9hoCBM2b_lL0g.JPEG.drusty97/IMG_0339.jpg?type=w966",
                                "프론트앤드 개발자",
                                "Yapp23기 Web1팀"),
                        new GetContactsResponse.SimpleContact(
                                3L,
                                "이정민",
                                "https://postfiles.pstatic.net/MjAyMjA5MTdfMTE1/MDAxNjYzMzc3MDc1MTA2.bToArUww9E15OT_Mmt5mz7xAkuK98KGBbeI_dsJeaDAg.WJAhfo5kHehNQKWLEWKURBlZ7m_GZVZ9hoCBM2b_lL0g.JPEG.drusty97/IMG_0339.jpg?type=w966",
                                "프론트앤드 개발자",
                                "Yapp23기 Web1팀"));

        return ApiResponse.success(new GetContactsResponse.Contacts(contacts));
    }

    @GetMapping("/search")
    @Operation(summary = "연락처 검색하기")
    public ApiResponse<SearchContactResponse.Contacts> searchContacts(@RequestParam String query) {
        SearchContactResponse.Contacts contacts =
                new SearchContactResponse.Contacts(
                        List.of(
                                new SearchContactResponse.SimpleContact(
                                        1L,
                                        "윤대용",
                                        "https://postfiles.pstatic.net/MjAyMjA5MTdfMTE1/MDAxNjYzMzc3MDc1MTA2.bToArUww9E15OT_Mmt5mz7xAkuK98KGBbeI_dsJeaDAg.WJAhfo5kHehNQKWLEWKURBlZ7m_GZVZ9hoCBM2b_lL0g.JPEG.drusty97/IMG_0339.jpg?type=w966",
                                        "프론트앤드 개발자",
                                        "Yapp23기 Web1팀"),
                                new SearchContactResponse.SimpleContact(
                                        2L,
                                        "이우성",
                                        "https://postfiles.pstatic.net/MjAyMjA5MTdfMTE1/MDAxNjYzMzc3MDc1MTA2.bToArUww9E15OT_Mmt5mz7xAkuK98KGBbeI_dsJeaDAg.WJAhfo5kHehNQKWLEWKURBlZ7m_GZVZ9hoCBM2b_lL0g.JPEG.drusty97/IMG_0339.jpg?type=w966",
                                        "프론트앤드 개발자",
                                        "Yapp23기 Web1팀"),
                                new SearchContactResponse.SimpleContact(
                                        3L,
                                        "이정민",
                                        "https://postfiles.pstatic.net/MjAyMjA5MTdfMTE1/MDAxNjYzMzc3MDc1MTA2.bToArUww9E15OT_Mmt5mz7xAkuK98KGBbeI_dsJeaDAg.WJAhfo5kHehNQKWLEWKURBlZ7m_GZVZ9hoCBM2b_lL0g.JPEG.drusty97/IMG_0339.jpg?type=w966",
                                        "프론트앤드 개발자",
                                        "Yapp23기 Web1팀")));

        return ApiResponse.success(contacts);
    }

    @GetMapping("/{contactId}")
    @Operation(summary = "연락처 가져오기")
    public ApiResponse<GetContactResponse> getContact(@PathVariable Long contactId) {
        List<com.imlinker.domain.tag.model.Tag> tags =
                List.of(
                        new com.imlinker.domain.tag.model.Tag(1L, "101/101", "스포츠"),
                        new com.imlinker.domain.tag.model.Tag(2L, "101/101", "엔터테인먼트"),
                        new com.imlinker.domain.tag.model.Tag(3L, "101/101", "비즈니스"));

        GetContactResponse response =
                new GetContactResponse(
                        1L,
                        "김태준",
                        "https://postfiles.pstatic.net/MjAyMjA5MTdfMTE1/MDAxNjYzMzc3MDc1MTA2.bToArUww9E15OT_Mmt5mz7xAkuK98KGBbeI_dsJeaDAg.WJAhfo5kHehNQKWLEWKURBlZ7m_GZVZ9hoCBM2b_lL0g.JPEG.drusty97/IMG_0339.jpg?type=w966",
                        "Json 상하차 담당",
                        "Yapp23기 Web1팀",
                        tags);

        return ApiResponse.success(response);
    }

    @PostMapping
    @Operation(summary = "연락처 생성하기")
    public ApiResponse<OperationResult> createContact(@RequestBody CreateContactRequest request) {
        return ApiResponse.success(OperationResult.SUCCESS);
    }

    @PutMapping("/{contactId}")
    @Operation(summary = "연락처 수정하기")
    public ApiResponse<OperationResult> updateContact(
            @PathVariable Long contactId, @RequestBody UpdateContactRequest request) {
        return ApiResponse.success(OperationResult.SUCCESS);
    }
}
