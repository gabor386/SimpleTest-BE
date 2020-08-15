package com.simpletask.qbuilder.mappers;


import com.simpletask.qbuilder.DTO.TestDTO;
import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.entities.Candidate;
import com.simpletask.qbuilder.entities.Test;
import com.simpletask.qbuilder.entities.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TestMapper {

    TestDTO testToTestDTO(Test test);
    List<TestDTO> testsToTestsDTO(List<Test> tests);
}
