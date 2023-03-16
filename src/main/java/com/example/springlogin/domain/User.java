package com.example.springlogin.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate //update 할때 실제 값이 변경됨 컬럼으로만 update 쿼리를 만듦
public class User {

    @Id
    @Column(nullable = false)
    private Long id;

    @NotBlank(message = "필수 항목입니다.")
    @Email(message = "이메일 형식으로 작성해야 합니다.")
    @Column(length = 30)
    private String email;

    @NotBlank(message = "필수 항목입니다.")
    @Column(length = 20)
    private String password;

    @NotBlank(message = "필수 항목입니다.")
    @Column(length = 20)

    private String name;

    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    @Column(length = 20)
    private String phoneNumber;

}
