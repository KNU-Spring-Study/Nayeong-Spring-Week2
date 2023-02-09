package com.example.springlogin.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class User {

    private Long id;

    @NotEmpty
    @Email(message = "이메일 형식으로 작성해야 합니다.")
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    private String phoneNumber;

}
