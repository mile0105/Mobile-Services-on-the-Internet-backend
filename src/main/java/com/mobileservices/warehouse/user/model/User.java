package com.mobileservices.warehouse.user.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Builder
@Table("users")
public class User {
    @Id
    private Long id;
    @Column("username")
    private String username;
    @Column("password")
    private String password;
    @Column("role")
    private String role;
}
