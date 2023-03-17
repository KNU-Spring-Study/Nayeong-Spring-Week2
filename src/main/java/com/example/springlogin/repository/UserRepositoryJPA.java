package com.example.springlogin.repository;

import com.example.springlogin.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User, Long>, UserRepository {

    Object save(User user);
    Optional<User> findUserByEmail(String email);
    Optional<User> findById(Long id);
    void deleteUser(Long userId);
}
