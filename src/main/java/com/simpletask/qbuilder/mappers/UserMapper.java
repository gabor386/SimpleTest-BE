package com.simpletask.qbuilder.mappers;

import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.entities.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDto(User user);
    List<UserDTO> usersToUsersDto(List<User> users);
}
