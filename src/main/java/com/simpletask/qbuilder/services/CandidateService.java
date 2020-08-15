package com.simpletask.qbuilder.services;

import com.simpletask.qbuilder.DTO.CandidateDTO;
import com.simpletask.qbuilder.DTO.PaginationSearchDTO;
import com.simpletask.qbuilder.entities.Candidate;
import com.simpletask.qbuilder.enums.Status;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CandidateService {

    CandidateDTO addCandidate(Candidate candidate);
    Page<CandidateDTO> getCandidates(PaginationSearchDTO paginationSearchDTO);
    CandidateDTO setCandidateStatus(int id, Status status);
    Candidate getCandidateById(int id);
    CandidateDTO getCandidateDTOById(int id);
}
