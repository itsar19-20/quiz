package com.ctfly.docker.test.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
