package com.crud.test.model.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_NOT_AUTH;

    @Override
    public String getAuthority() {
        return name();
    }
}