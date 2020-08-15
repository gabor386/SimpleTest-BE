package com.simpletask.qbuilder.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="answer")
@Data
public class Answer {

    @Id
    @GeneratedValue
    private Integer id;

    @Column()
    private String answerText;

    @Column()
    private double score;

    @ManyToOne
    @JoinColumn(name = "Question_ID", referencedColumnName = "id")
    private Question question;

    @ManyToOne
    @JoinColumn(name="test_id", referencedColumnName = "id")
    private Test test;
}
