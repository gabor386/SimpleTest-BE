package com.simpletask.qbuilder.services;

import com.simpletask.qbuilder.DTO.TestGroupDTO;
import com.simpletask.qbuilder.entities.TestGroup;

import java.util.List;

public interface TestGroupService {
    List<TestGroupDTO> getAllGroups();
    TestGroupDTO addTestGroup(TestGroup testGroup);
}
