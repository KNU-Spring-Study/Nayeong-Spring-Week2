package com.example.springlogin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserConfirmationDTO {

    @NotBlank(message = "필수 항목입니다.")
    private String password;
}
