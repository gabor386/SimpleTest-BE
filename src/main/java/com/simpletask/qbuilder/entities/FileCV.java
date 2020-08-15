package com.simpletask.qbuilder.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="file")
public class FileCV {

    @Id
    @GeneratedValue()
    private int id;

    @Column()
    private String hash;

    @Column()
    private String fileName;

    @Column()
    private String extension;

    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;



}
