package com.anz.cam.repository;

import org.springframework.data.repository.CrudRepository;

import com.anz.cam.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
