package com.simpletask.qbuilder.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="test_template")
@Data
public class TestTemplate implements Serializable {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column(unique = true)
    private String testTemplateName;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="Test_group_ID",referencedColumnName = "id")
    private TestGroup testGroup;

    @OneToMany(mappedBy = "testTemplate",cascade=CascadeType.ALL)
    @JsonManagedReference
    private Collection<Question> question;

    @OneToMany(mappedBy = "testTemplate",cascade=CascadeType.ALL)
    private Collection<Test> test;


}
