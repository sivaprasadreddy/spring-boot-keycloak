package com.sivalabs.catalog.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthUserModel {
    private String id;
    private String username;
    private String email;
    private String fullName;
    private List<String> roles;
}
