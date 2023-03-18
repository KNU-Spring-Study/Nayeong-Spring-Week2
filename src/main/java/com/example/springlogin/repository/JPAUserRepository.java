package com.example.springlogin.repository;

import com.example.springlogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAUserRepository extends JpaRepository<User, Long>, UserRepository {

    @Override
    User save(User user);

    @Override
    Optional<User> findUserByEmail(String email);

    @Override
    Optional<User> findById(Long id);

    @Override
    void deleteById(Long userId);
}
