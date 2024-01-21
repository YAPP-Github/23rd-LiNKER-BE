package com.imlinker.coreapi.core.auth.oauth2;

public enum OAuth2ActionType {
    LOGIN,
    ACTION;

    public static OAuth2ActionType of(String action) {
        return OAuth2ActionType.valueOf(action.toUpperCase());
    }
}