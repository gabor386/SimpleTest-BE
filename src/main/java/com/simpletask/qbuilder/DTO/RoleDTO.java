package com.simpletask.qbuilder.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;

@Data
public class RoleDTO {
    private Integer id;
    private String name;
//    public ArrayList<UserDTO> users;
}
