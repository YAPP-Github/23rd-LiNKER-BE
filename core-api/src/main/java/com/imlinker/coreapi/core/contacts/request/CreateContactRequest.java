package com.imlinker.coreapi.core.contacts.request;

public record CreateContactRequest(
        String name, String profileImgUrl, String job, String association) {}
