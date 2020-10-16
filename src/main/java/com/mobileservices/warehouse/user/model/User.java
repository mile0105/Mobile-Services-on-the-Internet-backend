package com.mobileservices.warehouse.user.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private long id;
    private String username;
    private String password;
    private Role role;
}
