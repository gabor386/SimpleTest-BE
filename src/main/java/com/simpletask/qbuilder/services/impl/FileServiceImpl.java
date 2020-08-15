package com.simpletask.qbuilder.services.impl;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpletask.qbuilder.DTO.FileCVDTO;
import com.simpletask.qbuilder.entities.Candidate;
import com.simpletask.qbuilder.entities.FileCV;
import com.simpletask.qbuilder.mappers.FileMapper;
import com.simpletask.qbuilder.repositories.FileRepository;
import com.simpletask.qbuilder.services.AmazonService;
import com.simpletask.qbuilder.services.CandidateService;
import com.simpletask.qbuilder.services.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private FileRepository fileRepository;
    private AmazonService amazonService;
    private CandidateService candidateService;
    private FileMapper fileMapper;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, AmazonService amazonService, CandidateService candidateService, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.amazonService = amazonService;
        this.candidateService = candidateService;
        this.fileMapper = fileMapper;
    }

    @Override
    public FileCVDTO uploadFile(MultipartFile file, int id) throws Exception {
        FileCV newFileCV = new FileCV();
        Candidate candidate = candidateService.getCandidateById(id);
        newFileCV.setCandidate(candidate);
        newFileCV.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        newFileCV.setFileName(file.getOriginalFilename().replace(' ', '_'));
        newFileCV.setHash(UUID.randomUUID().toString());
        try {
            amazonService.uploadFile(file, newFileCV.getHash(), candidate.getUser().getEmail());
        } catch (Exception e) {
            throw e;
        }
        return fileMapper.fileToFileDTO(fileRepository.save(newFileCV));
    }

    @Override
    public void deleteFile(int id) {
        try {
            String message = amazonService.deleteFile(fileRepository.getOne(id));
            fileRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String getFileNameById(int id) {
        return fileRepository.getOne(id).getFileName();
    }

    @Override
    public InputStream downloadFile(int id) {
        return amazonService.downloadFile(fileRepository.getOne(id));
    }

}
