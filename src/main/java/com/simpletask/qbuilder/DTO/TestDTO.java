package com.simpletask.qbuilder.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OrderBy;
import java.util.Collection;
import java.util.List;

@Data
public class TestDTO {
    private Integer id;

    private CandidateDTO candidate;

    private UserDTO interviewer;

    private TestTemplateDTO testTemplate;

    private String status;

    private Collection<AnswerDTO> answers;

}
