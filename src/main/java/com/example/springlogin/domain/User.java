package com.example.springlogin.domain;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class User {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

}
