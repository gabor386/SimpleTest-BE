package com.simpletask.qbuilder.services.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.simpletask.qbuilder.entities.Candidate;
import com.simpletask.qbuilder.entities.FileCV;
import com.simpletask.qbuilder.services.AmazonService;
import com.simpletask.qbuilder.services.CandidateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AmazonServiceImpl implements AmazonService {

    private AmazonS3 amazonS3;
    private CandidateService candidateService;

    @Autowired
    public AmazonServiceImpl(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Value("${endpointUrl}")
    private String endpointUrl;
    @Value("${bucketName}")
    private String bucketName;
    @Value("${aws_access_key_id}")
    private String accessKey;
    @Value("${aws_secret_access_key}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,secretKey);
        amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.EU_CENTRAL_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    @Override
    public String uploadFile(MultipartFile multipartFile, String fileHashName, String email) throws Exception {
        try {
            File file = convertMultipartFileToFile(multipartFile);
            String fileName = multipartFile.getOriginalFilename().replace(' ', '_');
            uploadFiletoBucket(fileHashName, file, email);
            file.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return "File uploaded for user " + email;
    }

    @Override
    public String deleteFile(FileCV file) {
        String dest = file.getCandidate().getUser().getEmail() + "/" +file.getHash();
        amazonS3.deleteObject(new DeleteObjectRequest(bucketName, dest));
        String fileName = file.getCandidate().getUser().getEmail() + "/" + file.getFileName();
        return "Removal successful for file " + fileName;
    }

    @Override
    public InputStream downloadFile(FileCV fileCV) {
        String path = fileCV.getCandidate().getUser().getEmail() + "/" + fileCV.getHash();
        InputStream f = amazonS3.getObject(new GetObjectRequest(bucketName, path)).getObjectContent();
        return f;
    }

    private void uploadFiletoBucket(String fileName, File file, String email)  {
        amazonS3.putObject(new PutObjectRequest(bucketName, email+"/"+fileName, file));
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException{
        File convertedFile = new File(multipartFile.getOriginalFilename().replace(' ', '_'));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convertedFile;
    }



}
