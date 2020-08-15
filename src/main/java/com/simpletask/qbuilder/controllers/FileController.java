package com.simpletask.qbuilder.controllers;


import com.simpletask.qbuilder.DTO.FileCVDTO;
import com.simpletask.qbuilder.entities.Candidate;
import com.simpletask.qbuilder.services.AmazonService;
import com.simpletask.qbuilder.services.CandidateService;
import com.simpletask.qbuilder.services.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@Api(value ="File operations", description = "Operations for managing candidate CVs")
@RestController
public class FileController {

    private FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "Upload a new Multipart file for a specific candidate, determined by User ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value="/file/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@PathVariable ("id") int id, @RequestParam(value = "file") MultipartFile file) {
        try {
            return new ResponseEntity<>(fileService.uploadFile(file, id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(value = "Delete a file, determined by file ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping(value = "/file/{id}")
    public ResponseEntity deleteFile(@PathVariable ("id") int id) {
        try {
            fileService.deleteFile(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ApiOperation(value = "Download a file, determined by file ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "file/{id}", produces = "application/octet-stream")
    public ResponseEntity getFile(@PathVariable ("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .cacheControl(CacheControl.noCache())
                    .header("Content-Disposition", "attachment; filename=" + fileService.getFileNameById(id))
                    .body(new InputStreamResource(fileService.downloadFile(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
