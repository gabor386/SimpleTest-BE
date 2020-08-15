package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.DTO.TestTemplateDTO;
import com.simpletask.qbuilder.entities.Test;
import com.simpletask.qbuilder.entities.TestTemplate;
import com.simpletask.qbuilder.exception.NotFoundException;
import com.simpletask.qbuilder.mappers.TestTemplateMapper;
import com.simpletask.qbuilder.repositories.TestRepository;
import com.simpletask.qbuilder.repositories.TestTemplateRepository;
import com.simpletask.qbuilder.services.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestTemplateServiceImpl implements TestTemplateService {

    private TestTemplateRepository testTemplateRepository;
    private TestTemplateMapper testTemplateMapper;
    private TestRepository testRepository;

    @Autowired
    public TestTemplateServiceImpl(TestTemplateRepository testTemplateRepository,TestTemplateMapper testTemplateMapper,TestRepository testRepository) {
        this.testTemplateRepository=testTemplateRepository;
        this.testTemplateMapper = testTemplateMapper;
        this.testRepository=testRepository;
    }

    @Override
    public List<TestTemplateDTO> findAll(){
        List<TestTemplate> templates = testTemplateRepository.findAll();
        return testTemplateMapper.testTemplatesToTestTemplatesDto(templates);
    }

    @Override
    public TestTemplate addNewTestTemplate(TestTemplate testTemplate) {
        return testTemplateRepository.save(testTemplate);
    }

    @Override
    public void removeTestTemplate(int id) {
        try{
            List<Test> tests=testRepository.findTestByTestTemplateId(id);
            if(tests.isEmpty()){
                testTemplateRepository.deleteById(id);
            }else{
                throw new NotFoundException(TestTemplate.class.getSimpleName());
            }

        }catch (Exception e){
            throw e;
        }

    }

}
