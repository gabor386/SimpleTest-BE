package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.DTO.CandidateDTO;
import com.simpletask.qbuilder.DTO.PaginationSearchDTO;
import com.simpletask.qbuilder.entities.Candidate;
import com.simpletask.qbuilder.enums.Status;
import com.simpletask.qbuilder.mappers.CandidateMapper;
import com.simpletask.qbuilder.repositories.CandidateRepository;
import com.simpletask.qbuilder.services.CandidateService;
import com.simpletask.qbuilder.services.EmailService;
import com.simpletask.qbuilder.services.RoleService;
import com.simpletask.qbuilder.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleService roleService;
    private CandidateMapper candidateMapper;
    private UserService userService;
    private EmailService emailService;


    @Autowired
    public CandidateServiceImpl(CandidateRepository candidateRepository,BCryptPasswordEncoder bCryptPasswordEncoder,
                                RoleService roleService, CandidateMapper candidateMapper,UserService userService,EmailService emailService) {
        this.candidateRepository=candidateRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.roleService=roleService;
        this.candidateMapper=candidateMapper;
        this.userService=userService;
        this.emailService=emailService;
    }

    @Override
    public CandidateDTO addCandidate(Candidate candidate) {
        String generatedString = RandomStringUtils.random(15,true,true);
        candidate.setStatus(Status.INVITE);
        candidate.setLastStatusUpdate(new Date());
        candidate.setPassword(bCryptPasswordEncoder.encode(generatedString));
        candidate.setRole(roleService.getRoleByName("CANDIDATE"));
        CandidateDTO cand = candidateMapper.candidateToCandidateDTO(candidateRepository.save(candidate));
        cand.setUser(userService.findByEmail(candidate.getEmail()));
        emailService.sendSimpleMessage(cand.getUser().getEmail(),"Test invitation","Hi, " + cand.getUser().getFirstName()+"!"+
            " We are happy to invite you to take a test for our company. Go to http://localhost:4200/login and use generated password:"+generatedString );
        return cand;
    }

    @Override
    public Page<CandidateDTO> getCandidates(PaginationSearchDTO paginationSearchDTO) {
        Pageable pageable = PageRequest.of(paginationSearchDTO.getPage(),paginationSearchDTO.getSize());
        Page<Candidate> candidates;
        if(paginationSearchDTO.getSearchText().isEmpty()){
            candidates = candidateRepository.findAll(pageable);
        }else{
            candidates = candidateRepository.findAllByLastNameContains(paginationSearchDTO.getSearchText(),pageable);
        }
        return candidates.map(candidate -> candidateMapper.candidateToCandidateDTO(candidate));
    }
    public CandidateDTO setCandidateStatus(int id, Status status) {
        Candidate cand = candidateRepository.getOne(id);
        cand.setLastStatusUpdate(new Date());
        cand.setStatus(status);
        return candidateMapper.candidateToCandidateDTO(candidateRepository.save(cand));
    }

    public CandidateDTO getCandidateDTOById(int id) {
        return candidateMapper.candidateToCandidateDTO(candidateRepository.getOne(id));
    }

    public Candidate getCandidateById(int id) {
        return candidateRepository.getOne(id);
    }

}
