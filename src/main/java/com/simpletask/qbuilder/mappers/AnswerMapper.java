package com.simpletask.qbuilder.mappers;

import com.simpletask.qbuilder.DTO.AnswerDTO;
import com.simpletask.qbuilder.entities.Answer;
import com.simpletask.qbuilder.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerDTO answerToAnswerDTO(Answer answer);
    List<AnswerDTO> answersToAnswersDTO(List<Answer> answers);
}
