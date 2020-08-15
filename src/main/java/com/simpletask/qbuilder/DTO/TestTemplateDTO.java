package com.simpletask.qbuilder.DTO;


import com.simpletask.qbuilder.entities.Question;
import com.simpletask.qbuilder.entities.TestGroup;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;


import java.util.*;

@Data
public class TestTemplateDTO {

    private Integer id;

    private String testTemplateName;

    private TestGroupDTO testGroup;

    private List<QuestionDTO> question;

    private Integer timer;

//    private List<TestDTO> test;
}
