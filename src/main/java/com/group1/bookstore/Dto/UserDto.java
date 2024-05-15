package com.group1.bookstore.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
}
