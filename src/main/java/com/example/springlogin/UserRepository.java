package com.example.springlogin;

import com.example.springlogin.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private Map map = new ConcurrentHashMap();

    public Long save(User user) {
        map.put("user", user);
        return user.getId();
    }

}
