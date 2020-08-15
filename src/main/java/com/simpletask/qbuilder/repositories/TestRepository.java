package com.simpletask.qbuilder.repositories;

import com.simpletask.qbuilder.entities.Test;
import com.simpletask.qbuilder.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test,Integer> {
     List<Test> findTestsByInterviewer_IdOrderByStatusAsc(int id);
     List<Test> findTestsByCandidate_IdOrderByStatusAsc(int id);
     long countByStatusLike(Status status);
     long countAllByTestTemplateTestGroupName(String testGroupName);
     long countAllByTestTemplateTestGroupNameAndStatusLike(String testGroupName, Status s2);
     List<Test> findTestByTestTemplateId(int id);
}
