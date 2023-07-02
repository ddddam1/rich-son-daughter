package com.starter.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    SUPERADMIN("ROLE_SUPERADMIN,ROLE_ADMIN"),
    ADMIN("ROLE_ADMIN"),
    GUEST("ROLE_GUEST");

    private String value;
}
