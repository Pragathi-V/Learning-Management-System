package com.capg.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.jwt.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
