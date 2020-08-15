package com.simpletask.qbuilder.repositories;

import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findAllByRoleId(Integer id);
    Page<User> findAllByRoleId(Integer id, Pageable pageable);
    Page<User> findAllByRoleIdAndLastNameContains(Integer id, String search, Pageable pageable);

}
