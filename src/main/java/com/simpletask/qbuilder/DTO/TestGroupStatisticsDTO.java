package com.simpletask.qbuilder.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestGroupStatisticsDTO {
    String testGroupName;
    TestStatisticsDTO testStatistics;
}
