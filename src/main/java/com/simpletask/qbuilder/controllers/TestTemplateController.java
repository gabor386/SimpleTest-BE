package com.simpletask.qbuilder.controllers;


import com.simpletask.qbuilder.DTO.TestTemplateDTO;
import com.simpletask.qbuilder.entities.TestTemplate;
import com.simpletask.qbuilder.services.TestTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value ="Test templates operations", description = "Operations for managing test templates")
@RestController()
public class TestTemplateController {

    private TestTemplateService testTemplateService;

    @Autowired
    public TestTemplateController(TestTemplateService testTemplateService) { this.testTemplateService=testTemplateService; }

    @ApiOperation(value = "Get all test templates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("getTemplates")
    public ResponseEntity<List<TestTemplateDTO>> getTemplates(){
        return new ResponseEntity<>(testTemplateService.findAll(), HttpStatus.OK);
    }


    @ApiOperation(value = "Add a new test template")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/addNewTestTemaplte")
    public ResponseEntity<TestTemplate> addNewTestTemplate(@RequestBody TestTemplate testTemplate){
        return new ResponseEntity<>(testTemplateService.addNewTestTemplate(testTemplate),HttpStatus.OK);

    }
    @ApiOperation(value = "Delete one test template")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("deleteTestTemplate/{id}")
    public ResponseEntity deleteFile(@PathVariable ("id") int id) {
        try {

            testTemplateService.removeTestTemplate(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
