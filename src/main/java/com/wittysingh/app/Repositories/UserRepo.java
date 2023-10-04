package com.wittysingh.app.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wittysingh.app.Entities.User;

public interface UserRepo extends  JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
}
