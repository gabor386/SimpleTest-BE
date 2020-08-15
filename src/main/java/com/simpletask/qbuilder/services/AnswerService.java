package com.simpletask.qbuilder.services;

import com.simpletask.qbuilder.DTO.AnswerDTO;
import com.simpletask.qbuilder.entities.Answer;

import java.util.ArrayList;
import java.util.Collection;

public interface AnswerService {
    public Collection<AnswerDTO> addAnswers(ArrayList<Answer> answers, int id);
    public Collection<AnswerDTO> updateScore(ArrayList<Answer> answers,int id);
}
