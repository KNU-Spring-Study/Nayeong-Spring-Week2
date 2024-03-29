package com.example.springlogin.repository;

import com.example.springlogin.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
//@Repository
public class MapUserRepository implements UserRepository {

    private static final Map<Long, User> map = new ConcurrentHashMap();
    private static long sequence = 0L;

    /**
     * 유저 저장
     *
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        user.setId(++sequence);
        map.put(user.getId(), user);
        log.info("회원가입 완료={}", user.getId());
        log.info("회원가입한 유저 아이디={}", user.getEmail());
        return null;
    }

    /**
     * 이메일로 유저 찾기
     * @param email
     * @return
     */
    @Override
    public Optional<User> findUserByEmail(String email) {

        for(Long key : map.keySet()) {
            if(map.get(key).getEmail().equals(email)) {
                return Optional.ofNullable(map.get(key));
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

/*
    @Override
    public void updateUser(Long userId, User updateUser) {
        Optional<User> optionalUser = findById(userId);

        User user = optionalUser.get();
        user.setName(updateUser.getName());
        user.setPassword(updateUser.getPassword());
        user.setPhoneNumber(updateUser.getPhoneNumber());
    }
    */

    @Override
    public void deleteById(Long userId) {
        map.remove(userId);
    }
}
