package com.example.springlogin.repository;

import com.example.springlogin.domain.User;

import java.util.Optional;

public interface UserRepository {
    public void save(User user);

    public Optional<User> findUserByUserEmail(String email);

    void updateUser(Long userId, User updateUser);
}
