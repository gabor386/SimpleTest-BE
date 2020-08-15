package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.DTO.RoleDTO;
import com.simpletask.qbuilder.entities.Role;
import com.simpletask.qbuilder.mappers.RoleMapper;
import com.simpletask.qbuilder.repositories.RoleRepository;
import com.simpletask.qbuilder.repositories.UserRepository;
import com.simpletask.qbuilder.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;


    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDTO getRoleById(int id) {
        return roleMapper.map(roleRepository.getOne(id));
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.mapList(roles);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name);
    }
}
