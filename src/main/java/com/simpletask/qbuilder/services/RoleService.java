package com.simpletask.qbuilder.services;

import com.simpletask.qbuilder.DTO.RoleDTO;
import com.simpletask.qbuilder.entities.Role;

import java.util.Collection;
import java.util.List;

public interface RoleService {
    public List<RoleDTO> findAll();
    public RoleDTO getRoleById(int id);
    public Role getRoleByName(String name);
}
