package com.simpletask.qbuilder.controllers;

import com.simpletask.qbuilder.DTO.TestGroupStatisticsDTO;
import com.simpletask.qbuilder.DTO.TestStatisticsDTO;
import com.simpletask.qbuilder.services.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "Statistics operations ", description = "Operations for getting user statistics")
@RestController
public class StatisticsController {
    StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @ApiOperation(value = "Operation for getting statistics data for all tests.")
    @GetMapping("/statistics/tests/all")
    public ResponseEntity<TestStatisticsDTO> getTests() {
        return new ResponseEntity<TestStatisticsDTO>(statisticsService.getTotalTestStatistics(), HttpStatus.OK);
    }

    @ApiOperation(value = "Operation for getting statistics data for tests grouped by test groups.")
    @GetMapping("/statistics/tests")
    public ResponseEntity<List<TestGroupStatisticsDTO>> getTestsByTestGroupName() {
        return new ResponseEntity<List<TestGroupStatisticsDTO>>(statisticsService.getStatisticsPerTestGroup(), HttpStatus.OK);
    }

}
