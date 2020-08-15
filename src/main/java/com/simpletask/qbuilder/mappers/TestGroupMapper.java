package com.simpletask.qbuilder.mappers;


import com.simpletask.qbuilder.DTO.TestGroupDTO;
import com.simpletask.qbuilder.entities.TestGroup;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TestGroupMapper{

    TestGroupDTO testGroupMap(TestGroup testGroup);
    List<TestGroupDTO> testGroupsMapList(List<TestGroup> testGroup);
}
