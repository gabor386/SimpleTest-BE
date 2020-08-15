package com.simpletask.qbuilder.controllers;


import com.simpletask.qbuilder.DTO.TestGroupDTO;
import com.simpletask.qbuilder.entities.TestGroup;
import com.simpletask.qbuilder.entities.TestTemplate;
import com.simpletask.qbuilder.services.TestGroupService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value ="Test groups operations", description = "Operations for managing test groups")
@RestController()
public class TestGroupController {

    public TestGroupService testGroupService;

    @Autowired
    public TestGroupController(TestGroupService testGroupService) { this.testGroupService=testGroupService; }

    @ApiOperation(value = "Get all test groups")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/test-groups")
    public ResponseEntity<List<TestGroupDTO>> getAllGroups() {
        return new ResponseEntity<>(testGroupService.getAllGroups(),HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new test group")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/test-groups")
    public ResponseEntity<TestGroupDTO> addGroup(@RequestBody TestGroup testGroup) {
        return new ResponseEntity<>(testGroupService.addTestGroup(testGroup),HttpStatus.OK);

    }
}
