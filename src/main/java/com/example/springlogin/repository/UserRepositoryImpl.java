package com.example.springlogin.repository;

import com.example.springlogin.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Map<Long, User> map = new ConcurrentHashMap();
    private static long sequence = 0L;

    /**
     * 유저 저장
     * @param user
     * @return
     */
    @Override
    public void save(User user) {
        user.setId(++sequence);
        map.put(user.getId(), user);
        log.info("회원가입 완료={}", user.getId());
    }

    /**
     * 이메일로 유저 찾기
     * @param email
     * @return
     */
    @Override
    public Optional<User> findUserByUserEmail(String email) {

        for(Long key : map.keySet()) {
            if(map.get(key).getEmail().equals(email)) {
                return Optional.ofNullable(map.get(key));
            }
        }

        return Optional.empty();
    }

}
