package com.simpletask.qbuilder.services;

import com.simpletask.qbuilder.DTO.TestDTO;
import com.simpletask.qbuilder.entities.Test;
import com.simpletask.qbuilder.enums.Status;
import java.util.List;

public interface TestService {
    List<TestDTO> getAllTests();
    TestDTO getTestById(int id);
    TestDTO addTest(Test test,int candidateId);
    TestDTO setTestStatusReview(int id, Status status);
    List<TestDTO> getTestsByInterviewerID(int id);
    List<TestDTO> getTestsByCandidateID(int id);
    List<TestDTO> getUndoneTestsByCandidateID(int id);
}
