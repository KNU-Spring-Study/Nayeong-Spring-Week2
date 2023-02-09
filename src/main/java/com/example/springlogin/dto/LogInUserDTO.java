package com.example.springlogin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class LogInUserDTO {

    @NotEmpty
    @Email(message = "이메일 형식으로 작성해야 합니다.")
    private String email;

    @NotEmpty
    private String password;
}
