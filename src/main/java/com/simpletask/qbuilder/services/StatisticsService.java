package com.simpletask.qbuilder.services;

import com.simpletask.qbuilder.DTO.TestGroupStatisticsDTO;
import com.simpletask.qbuilder.DTO.TestStatisticsDTO;


import java.util.List;

public interface StatisticsService {
    TestStatisticsDTO getTotalTestStatistics();
    List<TestGroupStatisticsDTO> getStatisticsPerTestGroup();
}
