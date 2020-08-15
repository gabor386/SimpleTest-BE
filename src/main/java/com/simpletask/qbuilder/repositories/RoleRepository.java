package com.simpletask.qbuilder.repositories;

import com.simpletask.qbuilder.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role getRoleByName(String name);
}
