package com.imlinker.coreapi.core.contacts.request;

public record UpdateContactRequest(
        String name, String profileImgUrl, String job, String association) {}
