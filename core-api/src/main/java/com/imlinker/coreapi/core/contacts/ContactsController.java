package com.imlinker.coreapi.core.contacts;

import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContext;
import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContextHolder;
import com.imlinker.coreapi.core.contacts.request.CreateContactRequest;
import com.imlinker.coreapi.core.contacts.request.UpdateContactRequest;
import com.imlinker.coreapi.core.contacts.response.GetContactResponse;
import com.imlinker.coreapi.core.contacts.response.GetContactsResponse;
import com.imlinker.coreapi.core.contacts.response.SearchContactResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.domain.contacts.ContactsService;
import com.imlinker.domain.contacts.CreateContactParam;
import com.imlinker.domain.contacts.UpdateContactParam;
import com.imlinker.domain.contacts.model.ContactProfile;
import com.imlinker.domain.contacts.model.Contacts;
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

    private final ContactsService service;

    @GetMapping
    @Operation(summary = "연락처 모두 가져오기")
    public ApiResponse<GetContactsResponse.Contacts> getContacts(
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        List<Contacts> contacts = service.getAllContacts(userContext.getId());
        List<GetContactsResponse.SimpleContact> simpleContacts =
                contacts.stream()
                        .map(
                                contact ->
                                        new GetContactsResponse.SimpleContact(
                                                contact.id(),
                                                contact.name(),
                                                contact.profileImgUrl().getValue(),
                                                contact.job(),
                                                contact.association()))
                        .toList();

        return ApiResponse.success(new GetContactsResponse.Contacts(simpleContacts));
    }

    @GetMapping("/search")
    @Operation(summary = "연락처 검색하기 (mock)")
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
    public ApiResponse<GetContactResponse> getContact(
            @PathVariable Long contactId,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        ContactProfile contactProfile = service.getContactProfile(contactId, userContext.getId());

        GetContactResponse response =
                new GetContactResponse(
                        contactProfile.id(),
                        contactProfile.name(),
                        contactProfile.profileImgUrl().getValue(),
                        contactProfile.phoneNumber().getValue(),
                        contactProfile.job(),
                        contactProfile.association(),
                        contactProfile.interests());

        return ApiResponse.success(response);
    }

    @PostMapping
    @Operation(summary = "연락처 생성하기")
    public ApiResponse<OperationResult> createContact(
            @RequestBody CreateContactRequest request,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        CreateContactParam param = request.toParam(userContext.getId());
        OperationResult result = service.createContact(param);

        return ApiResponse.success(result);
    }

    @PutMapping("/{contactId}")
    @Operation(summary = "연락처 수정하기")
    public ApiResponse<OperationResult> updateContact(
            @PathVariable Long contactId,
            @RequestBody UpdateContactRequest request,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        UpdateContactParam param = request.toParam(contactId, userContext.getId());
        OperationResult result = service.updateContact(param);

        return ApiResponse.success(result);
    }
}
