package com.simpletask.qbuilder.services;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.simpletask.qbuilder.entities.FileCV;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface AmazonService {
    public String deleteFile(FileCV file);
    public String uploadFile(MultipartFile multipartFile, String fileHashName, String email) throws Exception;
    public InputStream downloadFile(FileCV fileCV);
}
