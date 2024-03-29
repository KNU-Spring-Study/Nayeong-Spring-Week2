package com.example.springlogin.repository;

import com.example.springlogin.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findUserByEmail(String email);

    Optional<User> findById(Long id);

//    void updateUser(Long userId, User updateUser);

    void deleteById(Long userId);
}
