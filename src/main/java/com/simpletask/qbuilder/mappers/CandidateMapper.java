package com.simpletask.qbuilder.mappers;

import com.simpletask.qbuilder.DTO.CandidateDTO;
import com.simpletask.qbuilder.entities.Candidate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    CandidateDTO candidateToCandidateDTO(Candidate candidate);
}
