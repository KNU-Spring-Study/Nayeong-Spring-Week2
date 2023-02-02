package com.example.springlogin.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class User {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

}
