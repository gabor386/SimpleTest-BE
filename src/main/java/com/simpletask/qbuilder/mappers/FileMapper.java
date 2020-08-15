package com.simpletask.qbuilder.mappers;

import com.simpletask.qbuilder.DTO.FileCVDTO;
import com.simpletask.qbuilder.entities.FileCV;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileCVDTO fileToFileDTO(FileCV fileCV);
}
