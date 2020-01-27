package com.ctfly.docker.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctfly.docker.test.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
