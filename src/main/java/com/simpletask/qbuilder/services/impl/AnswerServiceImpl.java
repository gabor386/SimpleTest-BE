package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.DTO.AnswerDTO;
import com.simpletask.qbuilder.DTO.TestDTO;
import com.simpletask.qbuilder.entities.Answer;
import com.simpletask.qbuilder.entities.User;
import com.simpletask.qbuilder.enums.Status;
import com.simpletask.qbuilder.exception.NotFoundException;
import com.simpletask.qbuilder.mappers.AnswerMapper;
import com.simpletask.qbuilder.repositories.AnswerRepository;
import com.simpletask.qbuilder.services.AnswerService;
import com.simpletask.qbuilder.services.CandidateService;
import com.simpletask.qbuilder.services.EmailService;
import com.simpletask.qbuilder.services.TestService;
import com.simpletask.qbuilder.repositories.UserRepository;
import com.simpletask.qbuilder.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;
    private AnswerMapper answerMapper;
    private TestService testService;
    private CandidateService candidateService;
    private EmailService emailService;
    private UserRepository userRepository;
    private RoleService roleService;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository,
                             AnswerMapper answerMapper,
                             TestService testService,
                             CandidateService candidateService,
                             EmailService emailService,
                             RoleService roleService,
                             UserRepository userRepository) {
        this.testService=testService;
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.candidateService = candidateService;
        this.emailService=emailService;

        this.userRepository=userRepository;
        this.roleService=roleService;
    }

    public Collection<AnswerDTO> addAnswers(ArrayList<Answer> answers, int id){
        if(!answers.isEmpty()) {
            TestDTO test = testService.setTestStatusReview(id, Status.REVIEW);
            candidateService.setCandidateStatus(test.getCandidate().getUser().getId(), Status.REVIEW);
            emailService.sendSimpleMessage(test.getInterviewer().getEmail(),"Test updated!","Candidate: "
                    + test.getCandidate().getUser().getFirstName() + " submited answers. See the test at http://localhost:4200/grading/"
                    + test.getId());
        }

        return answerMapper.answersToAnswersDTO(answerRepository.saveAll(answers));
    }


    @Override
    public Collection<AnswerDTO> updateScore(ArrayList<Answer> answers,int id){
        List<Answer> updatedAnswers = updateAnswers(answers, id);
        TestDTO test = testService.setTestStatusReview(id, Status.REVIEWED);
        candidateService.setCandidateStatus(test.getCandidate().getUser().getId(), Status.REVIEWED);
        sendEmailToHR(test);
        return answerMapper.answersToAnswersDTO(answerRepository.saveAll(updatedAnswers));
    }

    public void sendEmailToHR(TestDTO test) {
        List<User> hrUsers=userRepository.findAllByRoleId(1);
        if(hrUsers==null){
            throw new NotFoundException(User.class.getSimpleName());
        }
        String[] emails=new String[hrUsers.size()];
        for(int i=0;i< hrUsers.size();i++){
            String tmp=hrUsers.get(i).getEmail();
            emails[i]=tmp;
        }
        emailService.sendMultiplySimpleMessage(emails,"Test Reviewed", "Hi, the test review is finnish by interviewer :"
                +test.getInterviewer().getFirstName()+" " +test.getInterviewer().getLastName()+" for candidate: "
                +test.getCandidate().getUser().getFirstName()+" "+test.getCandidate().getUser().getLastName());
    }

    public List<Answer> updateAnswers(ArrayList<Answer> answers, int id) {
        List<Answer> updatedAnswers=new ArrayList<Answer>();
        for(Answer a: answers) {
            Answer currentAnswer = answerRepository.getOne(a.getId());
            if(currentAnswer==null){
                throw new NotFoundException(Answer.class.getSimpleName(),id);
            }
            currentAnswer.setScore(a.getScore());
            updatedAnswers.add(currentAnswer);
        }
        return updatedAnswers;
    }

}
