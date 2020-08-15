package com.simpletask.qbuilder.mappers;

import com.simpletask.qbuilder.DTO.TestTemplateDTO;
import com.simpletask.qbuilder.entities.TestTemplate;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TestTemplateMapper {

    TestTemplateDTO testTemplateMapper(TestTemplate testTemplate);
    List<TestTemplateDTO> testTemplatesToTestTemplatesDto(List<TestTemplate> testTemplates);
}
