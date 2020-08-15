package com.simpletask.qbuilder.DTO;


import lombok.Data;

@Data
public class FileCVDTO {
    private int id;
    private String fileName;
    private String extension;
    private String hash;
}
