package com.simpletask.qbuilder.controllers;

import com.simpletask.qbuilder.DTO.AnswerDTO;
import com.simpletask.qbuilder.entities.Answer;
import com.simpletask.qbuilder.services.AnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Api(value ="Answers operations ", description = "Operations for managing candidate answers")
@RestController()
public class AnswerController {

    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) { this.answerService=answerService; }

    @ApiOperation(value = "Add a list of answers for a specific test")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("answers/{id}")
    public ResponseEntity<Collection<AnswerDTO>> addAnswers(@RequestBody ArrayList<Answer> answers, @PathVariable("id") int id ){
        return new ResponseEntity<>(answerService.addAnswers(answers, id), HttpStatus.OK);
    }


    @ApiOperation(value = "Add a list of answers and scores for a specific test")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PutMapping(value = "answers/{id}")
    public ResponseEntity<Collection<AnswerDTO>> updateScore(@RequestBody ArrayList<Answer> answers, @PathVariable("id") int id ){
        Collection<AnswerDTO> result=answerService.updateScore(answers,id);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
