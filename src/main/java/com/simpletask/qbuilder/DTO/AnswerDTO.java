package com.simpletask.qbuilder.DTO;

import lombok.Data;


@Data
public class AnswerDTO {
    private Integer id;
    private QuestionDTO question;
    private String answerText;
    private double score;
}
