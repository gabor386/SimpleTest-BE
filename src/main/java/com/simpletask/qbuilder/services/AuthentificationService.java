package com.simpletask.qbuilder.services;

import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.entities.User;

public interface AuthentificationService  {
    UserDTO addUser(User user);
}
