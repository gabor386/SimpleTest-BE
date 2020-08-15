package com.simpletask.qbuilder.mappers;

import com.simpletask.qbuilder.DTO.QuestionDTO;
import com.simpletask.qbuilder.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.awt.*;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionDTO map(Question question);

}
