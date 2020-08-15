package com.simpletask.qbuilder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column()
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private Collection<User> users;
}
