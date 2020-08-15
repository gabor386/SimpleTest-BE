package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.DTO.TestGroupDTO;
import com.simpletask.qbuilder.entities.TestGroup;
import com.simpletask.qbuilder.mappers.TestGroupMapper;
import com.simpletask.qbuilder.repositories.TestGroupRepository;
import com.simpletask.qbuilder.repositories.TestRepository;
import com.simpletask.qbuilder.services.TestGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestGroupServiceImpl implements TestGroupService {
    private TestGroupRepository testGroupRepository;
    private TestGroupMapper testGroupMapper;

    @Autowired
    public  TestGroupServiceImpl(TestGroupRepository testGroupRepository,TestGroupMapper testGroupMapper){
        this.testGroupRepository=testGroupRepository;
        this.testGroupMapper=testGroupMapper;
    }

    public List<TestGroupDTO> getAllGroups(){
        return testGroupMapper.testGroupsMapList(testGroupRepository.findAll());
    }

    public TestGroupDTO addTestGroup(TestGroup testGroup){

        return testGroupMapper.testGroupMap(testGroupRepository.save(testGroup));
    }
}
