package com.simpletask.qbuilder.controllers;


import com.simpletask.qbuilder.services.QuestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(value ="Questions operations", description = "Operations for managing test questions")
@RestController("questions")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {this.questionService=questionService;}

}
