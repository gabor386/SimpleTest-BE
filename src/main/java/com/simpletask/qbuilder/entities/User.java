package com.simpletask.qbuilder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    @GeneratedValue()
    private Integer id;

    @Column()
    private String firstName;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private Candidate candidate;

    @Column()
    private String lastName;

    @Column()
    private String email;

    @Column()
    private String password;

    @Column()
    private Boolean passwordChanged;

    @ManyToOne
    @JoinColumn(name = "Role_ID", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "interviewer")
    @JsonIgnore
    @ToString.Exclude private Collection<Test> tests;

    public String getUsername(){
        return this.email;
    }


}
