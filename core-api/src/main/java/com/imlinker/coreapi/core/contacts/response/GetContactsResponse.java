package com.imlinker.coreapi.core.contacts.response;

import java.util.List;

public class GetContactsResponse {
    public record Contacts(List<SimpleContact> contacts) {}
    ;

    public record SimpleContact(
            String id, String name, String profileImgUrl, String job, String association) {}
}
