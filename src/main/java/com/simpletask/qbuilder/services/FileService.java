package com.simpletask.qbuilder.services;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.simpletask.qbuilder.DTO.FileCVDTO;
import com.simpletask.qbuilder.entities.FileCV;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    FileCVDTO uploadFile(MultipartFile file, int id) throws Exception;
    void deleteFile(int id);
    InputStream downloadFile(int id);
    String getFileNameById(int id);
}
