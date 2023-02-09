package com.example.springlogin.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserConfirmationDTO {
    @NotEmpty
    private String password;
}
