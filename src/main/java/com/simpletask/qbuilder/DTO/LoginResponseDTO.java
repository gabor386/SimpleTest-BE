package com.simpletask.qbuilder.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private UserDTO userDTO;
    private String token;

}
