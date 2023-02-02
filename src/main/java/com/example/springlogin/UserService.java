package com.example.springlogin;

import com.example.springlogin.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean join(User user) {

        if(validateDuplicateUser(user.getEmail()) == null) {
            userRepository.save(user);
            return true;
        }
        else return false;

    }

    public User validateDuplicateUser(String email) {
        Optional<User> userByUserEmail = userRepository.findUserByUserEmail(email);
        return userByUserEmail.orElse(null);
    }
}
