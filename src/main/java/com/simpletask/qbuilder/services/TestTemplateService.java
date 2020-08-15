package com.simpletask.qbuilder.services;


import com.simpletask.qbuilder.DTO.TestTemplateDTO;
import com.simpletask.qbuilder.entities.TestTemplate;

import java.util.List;

public interface TestTemplateService {
    List<TestTemplateDTO> findAll();
    public TestTemplate addNewTestTemplate(TestTemplate testTemplate);
    public void removeTestTemplate(int testTemplateId);
}
