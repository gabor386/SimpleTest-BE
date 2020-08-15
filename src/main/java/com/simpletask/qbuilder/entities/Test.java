package com.simpletask.qbuilder.entities;

import com.simpletask.qbuilder.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "test")
public class Test implements Serializable {

    @Id
    @GeneratedValue()
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Candidate_ID", referencedColumnName = "id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "Interviewer_ID", referencedColumnName = "id")
    private User interviewer;

    @ManyToOne
    @JoinColumn(name= "Test_template_ID", referencedColumnName = "id")
    private TestTemplate testTemplate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column()
    private int timer;

    @OneToMany(mappedBy = "test",cascade=CascadeType.ALL)
    private Collection<Answer> answers;



}
