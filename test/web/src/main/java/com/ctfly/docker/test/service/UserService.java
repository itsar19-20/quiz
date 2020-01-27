package com.ctfly.docker.test.service;

import com.ctfly.docker.test.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
