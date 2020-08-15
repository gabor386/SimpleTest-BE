package com.simpletask.qbuilder.mappers;

import com.simpletask.qbuilder.DTO.RoleDTO;
import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses= UserMapper.class)
public interface RoleMapper {
    RoleDTO map(Role role);
    List<RoleDTO> mapList(List<Role> roles);
}
