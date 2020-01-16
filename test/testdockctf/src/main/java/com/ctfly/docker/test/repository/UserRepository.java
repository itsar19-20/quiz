package com.ctfly.docker.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctfly.docker.test.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
