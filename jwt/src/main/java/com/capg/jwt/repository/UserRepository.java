package com.capg.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
}
