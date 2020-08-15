package com.simpletask.qbuilder.services.impl;

import com.simpletask.qbuilder.DTO.TestGroupDTO;
import com.simpletask.qbuilder.DTO.TestGroupStatisticsDTO;
import com.simpletask.qbuilder.DTO.TestStatisticsDTO;
import com.simpletask.qbuilder.enums.Status;
import com.simpletask.qbuilder.mappers.TestGroupMapper;
import com.simpletask.qbuilder.repositories.TestGroupRepository;
import com.simpletask.qbuilder.repositories.TestRepository;
import com.simpletask.qbuilder.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    TestRepository testRepository;
    TestGroupRepository testGroupRepository;
    TestGroupMapper testGroupMapper;

    @Autowired
    public StatisticsServiceImpl(TestRepository testRepository, TestGroupRepository testGroupRepository, TestGroupMapper testGroupMapper) {
        this.testRepository = testRepository;
        this.testGroupRepository = testGroupRepository;
        this.testGroupMapper = testGroupMapper;

    }

    @Override
    public TestStatisticsDTO getTotalTestStatistics() {
        int forReview = (int) testRepository.countByStatusLike(Status.REVIEW);
        int reviewed = (int) testRepository.countByStatusLike(Status.REVIEWED);
        int invited = (int) testRepository.countByStatusLike(Status.INVITE);
        return new TestStatisticsDTO(invited, reviewed, forReview);
    }

    @Override
    public List<TestGroupStatisticsDTO> getStatisticsPerTestGroup() {
        List<TestGroupDTO> testGroups = testGroupMapper.testGroupsMapList(testGroupRepository.findAll());
        List<TestGroupStatisticsDTO> statisticsDTOS = new ArrayList<>();
        for (TestGroupDTO testGroup : testGroups) {
            String testGroupName = testGroup.getName();
            int forReview = (int) testRepository.countAllByTestTemplateTestGroupNameAndStatusLike(testGroupName, Status.REVIEW);
            int reviewed = (int) testRepository.countAllByTestTemplateTestGroupNameAndStatusLike(testGroupName, Status.REVIEWED);
            int invited = (int) testRepository.countAllByTestTemplateTestGroupNameAndStatusLike(testGroupName, Status.INVITE);
            TestStatisticsDTO testStatistics = new TestStatisticsDTO(invited, reviewed, forReview);
            TestGroupStatisticsDTO testGroupStatistics = new TestGroupStatisticsDTO(testGroupName, testStatistics);
            statisticsDTOS.add(testGroupStatistics);
        }
        return statisticsDTOS;
    }

}
