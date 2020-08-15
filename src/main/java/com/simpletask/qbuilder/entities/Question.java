package com.simpletask.qbuilder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="question")
@Data
public class Question implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String questionText;

    @ManyToOne
    @JoinColumn(name="Test_template_ID",referencedColumnName = "id")
    @JsonBackReference
    private TestTemplate testTemplate;

}
