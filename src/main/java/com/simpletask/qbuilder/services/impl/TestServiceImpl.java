package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.DTO.TestDTO;
import com.simpletask.qbuilder.entities.Candidate;
import com.simpletask.qbuilder.entities.Test;
import com.simpletask.qbuilder.enums.Status;
import com.simpletask.qbuilder.mappers.TestMapper;
import com.simpletask.qbuilder.repositories.CandidateRepository;
import com.simpletask.qbuilder.repositories.TestRepository;
import com.simpletask.qbuilder.services.EmailService;
import com.simpletask.qbuilder.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;
    private TestMapper testMapper;
    private CandidateRepository candidateRepository;
    private EmailService emailService;

    @Autowired
    public TestServiceImpl(TestRepository testRepository, TestMapper testMapper,CandidateRepository candidateRepository,
                           EmailService emailService) {
        this.testMapper = testMapper;
        this.testRepository = testRepository;
        this.candidateRepository=candidateRepository;
        this.emailService=emailService;
    }

    @Override
    public List<TestDTO> getAllTests() {
        return testMapper.testsToTestsDTO(testRepository.findAll());
    }

    @Override
    public TestDTO getTestById(int id) {
        return testMapper.testToTestDTO(testRepository.getOne(id));
    }
    @Override

    public TestDTO addTest(Test test,int candidateId) {
        test.setStatus(Status.INVITE);
        Candidate cand = candidateRepository.getOne(candidateId);
        cand.setStatus(Status.INVITE);
        cand.setLastStatusUpdate(new Date());
        test.setCandidate(candidateRepository.save(cand));
        Test addedTest = testRepository.save(test);
        emailService.sendSimpleMessage(cand.getUser().getEmail(),"Test invitation","Hi, " + cand.getUser().getFirstName()+
                "! We are happy to invite you to take a test for our company.");
        return testMapper.testToTestDTO(testRepository.save(test));
    }
    @Override
    public List<TestDTO> getTestsByInterviewerID(int id) {
        List<Test> tests = testRepository.findTestsByInterviewer_IdOrderByStatusAsc(id);
        tests.sort((o1, o2) -> {
            if(o1.getStatus().equals(Status.REVIEWED)&& o2.getStatus().equals(Status.REVIEW))
                return 1;
            else if(o1.getStatus().equals(Status.INVITE)&& o2.getStatus().equals(Status.REVIEW))
                return 1;
            else if(o1.getStatus().equals(Status.REVIEW)&& o2.getStatus().equals(Status.REVIEWED))
                return -1;
            else if(o1.getStatus().equals(Status.REVIEW)&& o2.getStatus().equals(Status.INVITE))
                return -1;
            else
                return o1.getStatus().compareTo(o2.getStatus());
        });
        return testMapper.testsToTestsDTO(tests);
    }
    @Override
    public List<TestDTO> getTestsByCandidateID(int id) {
        return testMapper.testsToTestsDTO(testRepository.findTestsByCandidate_IdOrderByStatusAsc(id));
    }

    public TestDTO setTestStatusReview(int id, Status status){
        Test test = testRepository.getOne(id);
        test.setStatus(status);
        return testMapper.testToTestDTO(testRepository.save(test));
    }

    public List<TestDTO> getUndoneTestsByCandidateID(int id) {
        List<Test> allTests = testRepository.findTestsByCandidate_IdOrderByStatusAsc(id);
        List<Test> undone = new ArrayList<Test>();
        for(Test t: allTests) {
            if(t.getStatus() == Status.INVITE) {
                undone.add(t);
            }
        }
        return testMapper.testsToTestsDTO(undone);
    }

}



