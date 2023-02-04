package com.example.springlogin.dto;

import com.example.springlogin.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String name;
    private String phoneNumber;

    public static UserDTO from(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getPhoneNumber());
    }

}
