package com.simpletask.qbuilder.controllers;


import com.simpletask.qbuilder.DTO.TestDTO;
import com.simpletask.qbuilder.entities.Test;
import com.simpletask.qbuilder.services.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value ="Test operations", description = "Operations for getting test informations and adding new tests")
@RestController()
public class TestController {

    private TestService testService;

    @Autowired
    public TestController (TestService testService) { this.testService=testService; }

    @ApiOperation(value = "Get all tests")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/test")
    public ResponseEntity<List<TestDTO>> getAllTests(){
        return new ResponseEntity<>(testService.getAllTests(),HttpStatus.OK);
    }

    @ApiOperation(value = "Get information about a test, determined by test ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("test/{id}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable ("id") int id) {
        return new ResponseEntity<>( testService.getTestById(id),HttpStatus.OK);
    }

    @ApiOperation(value = "Schedule a new test")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("test/add/{id}")
    public ResponseEntity<TestDTO> addTest(@RequestBody Test test,@PathVariable ("id") int candidateId) {
        return new ResponseEntity<>(testService.addTest(test,candidateId), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all tests for a specific interviewer, determined by his user ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/test/by-interviewer-id/{id}")
    public ResponseEntity<List<TestDTO>> getTestsByInterviewerId(@PathVariable ("id") int id) {
        return new ResponseEntity<>(testService.getTestsByInterviewerID(id),HttpStatus.OK);
    }

    @ApiOperation(value = "Get all tests for a specific candidate, determined by his user ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/test/by-candidate-id/{id}")
    public ResponseEntity<List<TestDTO>> getTestsByCandidateId(@PathVariable ("id") int id) {
        List<TestDTO> tests = testService.getTestsByCandidateID(id);
        return new ResponseEntity<>(tests,HttpStatus.OK);
    }

    @GetMapping("/test/undone-by-candidate-id/{id}")
    public ResponseEntity<List<TestDTO>> getUndoneTestsByCandidateId(@PathVariable ("id") int id) {
        return new ResponseEntity<>(testService.getUndoneTestsByCandidateID(id),HttpStatus.OK);
    }

}
