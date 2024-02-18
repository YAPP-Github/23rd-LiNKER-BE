package com.imlinker.coreapi.core.contacts;

import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContext;
import com.imlinker.coreapi.core.auth.context.AuthenticatedUserContextHolder;
import com.imlinker.coreapi.core.contacts.request.CreateContactRequest;
import com.imlinker.coreapi.core.contacts.request.UpdateContactRequest;
import com.imlinker.coreapi.core.contacts.response.GetContactInterestNewsResponse;
import com.imlinker.coreapi.core.contacts.response.GetContactResponse;
import com.imlinker.coreapi.core.contacts.response.GetContactsResponse;
import com.imlinker.coreapi.core.contacts.response.SearchContactResponse;
import com.imlinker.coreapi.support.response.ApiResponse;
import com.imlinker.domain.contacts.ContactSearchService;
import com.imlinker.domain.contacts.ContactsService;
import com.imlinker.domain.contacts.CreateContactParam;
import com.imlinker.domain.contacts.UpdateContactParam;
import com.imlinker.domain.contacts.model.ContactProfile;
import com.imlinker.domain.contacts.model.Contacts;
import com.imlinker.domain.news.TagSpecificNews;
import com.imlinker.enums.OperationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contacts")
@Tag(name = "Contacts API", description = "연락처 관련 API")
public class ContactsController {

    private final ContactsService service;
    private final ContactSearchService searchService;

    @GetMapping
    @Operation(summary = "연락처 모두 가져오기")
    public ApiResponse<GetContactsResponse.Contacts> getContacts(
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        List<Contacts> contacts = searchService.getAllContacts(userContext.getId());
        List<GetContactsResponse.SimpleContact> simpleContacts =
                contacts.stream()
                        .map(
                                contact ->
                                        new GetContactsResponse.SimpleContact(
                                                contact.id(),
                                                contact.name(),
                                                contact.profileImgUrl().getValue(),
                                                contact.email().getValue(),
                                                contact.school(),
                                                contact.careers()))
                        .toList();

        return ApiResponse.success(new GetContactsResponse.Contacts(simpleContacts));
    }

    @GetMapping("/bookmarks")
    @Operation(summary = "즐겨찾기 된 연락처 모두 가져오기")
    public ApiResponse<GetContactsResponse.Contacts> getBookMarkContacts(
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        List<Contacts> contacts = searchService.getAllBookMarkContacts(userContext.getId());
        List<GetContactsResponse.SimpleContact> simpleContacts =
                contacts.stream()
                        .map(
                                contact ->
                                        new GetContactsResponse.SimpleContact(
                                                contact.id(),
                                                contact.name(),
                                                contact.profileImgUrl().getValue(),
                                                contact.email().getValue(),
                                                contact.school(),
                                                contact.careers()))
                        .toList();

        return ApiResponse.success(new GetContactsResponse.Contacts(simpleContacts));
    }

    @GetMapping("/search")
    @Operation(summary = "연락처 검색하기")
    public ApiResponse<SearchContactResponse.Contacts> searchContacts(
            @RequestParam String query,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        List<Contacts> searchedContacts = searchService.search(query, userContext.getId());
        SearchContactResponse.Contacts contacts =
                new SearchContactResponse.Contacts(
                        searchedContacts.stream()
                                .map(
                                        contact ->
                                                new SearchContactResponse.SimpleContact(
                                                        contact.id(),
                                                        contact.name(),
                                                        contact.profileImgUrl().getValue(),
                                                        contact.email().getValue(),
                                                        contact.school(),
                                                        contact.careers()))
                                .toList());

        return ApiResponse.success(contacts);
    }

    @GetMapping("/{contactId}")
    @Operation(summary = "연락처 가져오기")
    public ApiResponse<GetContactResponse> getContact(
            @PathVariable Long contactId,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        ContactProfile contactProfile = searchService.getContactProfile(contactId, userContext.getId());

        GetContactResponse response =
                new GetContactResponse(
                        contactProfile.id(),
                        contactProfile.name(),
                        contactProfile.profileImgUrl().getValue(),
                        contactProfile.phoneNumber().getValue(),
                        contactProfile.email().getValue(),
                        contactProfile.school(),
                        contactProfile.careers(),
                        contactProfile.interests(),
                        contactProfile.recentMeetingDate());

        return ApiResponse.success(response);
    }

    @GetMapping("/{contactId}/interest/news")
    @Operation(summary = "연락처 관심사 기반 뉴스 가져오기")
    public ApiResponse<GetContactInterestNewsResponse.Recommendations> getContactInterestRelatedNews(
            @PathVariable Long contactId,
            @RequestParam(defaultValue = "20") int size,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContextHolder) {
        List<TagSpecificNews> tagSpecificNewsList =
                searchService.searchContactInterestRelatedNews(contactId, userContextHolder.getId(), size);

        GetContactInterestNewsResponse.Recommendations recommendations =
                new GetContactInterestNewsResponse.Recommendations(
                        tagSpecificNewsList.stream()
                                .map(GetContactInterestNewsResponse.Recommendation::fromTagSpecificNews)
                                .toList());
        return ApiResponse.success(recommendations);
    }

    @PostMapping
    @Operation(summary = "연락처 생성하기")
    public ApiResponse<OperationResult> createContact(
            @Valid @RequestBody CreateContactRequest request,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        CreateContactParam param = request.toParam(userContext.getId());
        OperationResult result = service.createContact(param);

        return ApiResponse.success(result);
    }

    @PostMapping("/{contactId}/bookmark")
    @Operation(summary = "연락처 즐겨찾기")
    public ApiResponse<OperationResult> bookMarkContact(
            @PathVariable Long contactId,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        OperationResult result = service.bookmark(userContext.getId(), contactId);
        return ApiResponse.success(result);
    }

    @PutMapping("/{contactId}")
    @Operation(summary = "연락처 수정하기")
    public ApiResponse<OperationResult> updateContact(
            @PathVariable Long contactId,
            @Valid @RequestBody UpdateContactRequest request,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        UpdateContactParam param = request.toParam(contactId, userContext.getId());
        OperationResult result = service.updateContact(param);

        return ApiResponse.success(result);
    }

    @DeleteMapping("/{contactId}")
    @Operation(summary = "연락처 삭제하기")
    public ApiResponse<OperationResult> deleteContact(
            @PathVariable Long contactId,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        OperationResult result = service.deleteContact(contactId, userContext.getId());
        return ApiResponse.success(result);
    }

    @DeleteMapping("/{contactId}/bookmark")
    @Operation(summary = "연락처 즐겨찾기 해제")
    public ApiResponse<OperationResult> unBookMarkContact(
            @PathVariable Long contactId,
            @AuthenticatedUserContext AuthenticatedUserContextHolder userContext) {
        OperationResult result = service.unBookmark(userContext.getId(), contactId);
        return ApiResponse.success(result);
    }
}
