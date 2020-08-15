package com.simpletask.qbuilder.DTO;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
public class CandidateDTO {
    private Integer id;
    private String status;
    private Date lastStatusUpdate;
    private UserDTO user;
    private List<FileCVDTO> files;
}
