package com.test.user_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.test.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}