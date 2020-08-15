package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.DTO.PaginationSearchDTO;
import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.entities.User;
import com.simpletask.qbuilder.exception.PasswordMismatchException;
import com.simpletask.qbuilder.mappers.UserMapper;
import com.simpletask.qbuilder.repositories.UserRepository;
import com.simpletask.qbuilder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


import static java.util.Collections.emptyList;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private UserMapper userMapper;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        return userMapper.usersToUsersDto(users);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userMapper.userToUserDto(userRepository.findByEmail(email));
    }

    @Override
    public Page<UserDTO> getInterviewers(PaginationSearchDTO paginationSearchDTO){
        Pageable pageable = PageRequest.of(paginationSearchDTO.getPage(),paginationSearchDTO.getSize());
        Page<User> interviewers;
        if(paginationSearchDTO.getSearchText().isEmpty()){
            interviewers = userRepository.findAllByRoleId(2,pageable);
        }else{
            interviewers = userRepository.findAllByRoleIdAndLastNameContains(2,paginationSearchDTO.getSearchText(),pageable);
        }
        return interviewers.map(interviewer -> userMapper.userToUserDto(interviewer));
    }

    @Override
    public UserDTO changePassword(String oldPassword,String newPassword,String confirmPassword,Integer userId){
        Optional<User> u = userRepository.findById(userId);
        User user = u.get();
        if(bCryptPasswordEncoder.matches(oldPassword,user.getPassword()) && newPassword.equals(confirmPassword)){
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            user.setPasswordChanged(true);
            return userMapper.userToUserDto(userRepository.save(user));
        }else{
            throw new PasswordMismatchException(userId);
        }
    }

    //use Email instead of username
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), emptyList());
    }
}
