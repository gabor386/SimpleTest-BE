package com.simpletask.qbuilder.repositories;

import com.simpletask.qbuilder.entities.FileCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileCV, Integer> {
}
