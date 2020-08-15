package com.simpletask.qbuilder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simpletask.qbuilder.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "candidate")
public class Candidate extends User implements Serializable {


    private Integer id;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @ToString.Exclude
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastStatusUpdate;

    @OneToMany(mappedBy = "candidate")
    @JsonIgnore
    private Collection<Test> tests;

    @OneToMany(mappedBy = "candidate")
    @ToString.Exclude
    private List<FileCV> files;
}
