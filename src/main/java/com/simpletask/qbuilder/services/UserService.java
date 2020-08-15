package com.simpletask.qbuilder.services;

import com.simpletask.qbuilder.DTO.PaginationSearchDTO;
import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<UserDTO> findAll();
    UserDTO findByEmail(String email);
    Page<UserDTO> getInterviewers(PaginationSearchDTO paginationSearchDTO);
    UserDTO changePassword(String oldPassword,String newPassword,String confirmPassword,Integer userId);


}
