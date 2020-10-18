package com.anz.cam.repository;

import org.springframework.data.repository.CrudRepository;

import com.anz.cam.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
