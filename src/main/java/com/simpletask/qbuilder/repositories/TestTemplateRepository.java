package com.simpletask.qbuilder.repositories;

import com.simpletask.qbuilder.entities.TestTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TestTemplateRepository extends JpaRepository<TestTemplate,Integer> {

}
