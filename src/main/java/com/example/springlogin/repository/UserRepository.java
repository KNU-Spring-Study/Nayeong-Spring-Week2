package com.example.springlogin.repository;

import com.example.springlogin.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findUserByUserEmail(String email);

    User findById(Long id);

    void updateUser(Long userId, User updateUser);
}
