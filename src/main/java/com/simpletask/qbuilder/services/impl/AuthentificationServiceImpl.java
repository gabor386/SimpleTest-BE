package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.entities.User;
import com.simpletask.qbuilder.exception.AccessDeniedException;
import com.simpletask.qbuilder.mappers.RoleMapper;
import com.simpletask.qbuilder.mappers.UserMapper;
import com.simpletask.qbuilder.repositories.RoleRepository;
import com.simpletask.qbuilder.repositories.UserRepository;
import com.simpletask.qbuilder.services.AuthentificationService;
import com.simpletask.qbuilder.services.EmailService;
import com.simpletask.qbuilder.services.RoleService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class AuthentificationServiceImpl implements AuthentificationService {

    private UserRepository userRepository;
    private EmailService emailService;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleService roleService;
    private RoleMapper roleMapper;
    private RoleRepository roleRepository;

    @Autowired
    public AuthentificationServiceImpl(UserRepository userRepository,UserMapper userMapper,BCryptPasswordEncoder bCryptPasswordEncoder,
                                       EmailService emailService, RoleService roleService, RoleMapper roleMapper,RoleRepository roleRepository) {
        this.userRepository=userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.emailService=emailService;
        this.roleService=roleService;
        this.roleMapper=roleMapper;
        this.roleRepository=roleRepository;
    }

    @Override
    public UserDTO addUser(User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userRepository.findByEmail((String) auth.getPrincipal());
        if(u.getRole().equals(roleRepository.getRoleByName("HR"))) {
            String generatedString = RandomStringUtils.random(15, true, true);
            user.setPassword(bCryptPasswordEncoder.encode(generatedString));
            userRepository.save(user);
            if (roleMapper.map(user.getRole()).equals(roleService.getRoleById(1))) {
                emailService.sendSimpleMessage(user.getEmail(), "Hr addition", "Hi, " + user.getFirstName() + "!" +
                        " We are happy to add you as HR for our company. Go to http://localhost:4200/login and use generated password:" + generatedString);
            } else {
                emailService.sendSimpleMessage(user.getEmail(), "Interviewer addition", "Hi, " + user.getFirstName() + "!" +
                        " We are happy to add you as a interviewer for our company. Go to http://localhost:4200/login and use generated password:" + generatedString);
            }
            return userMapper.userToUserDto(user);
        }else{
            throw new AccessDeniedException(u.getId());
        }
    }


}
