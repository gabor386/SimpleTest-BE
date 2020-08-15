package com.simpletask.qbuilder.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table( name="test_group")
@Data
public class TestGroup implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column()
    private String name;

    @OneToMany(mappedBy = "testGroup")
    private Collection<TestTemplate> testTemplate;
}
