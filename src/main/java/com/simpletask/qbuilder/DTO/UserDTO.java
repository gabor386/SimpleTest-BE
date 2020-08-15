package com.simpletask.qbuilder.DTO;


import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private RoleDTO role;
    private Boolean passwordChanged;
}
