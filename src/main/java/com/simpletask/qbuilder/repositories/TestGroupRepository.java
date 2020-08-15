package com.simpletask.qbuilder.repositories;

import com.simpletask.qbuilder.entities.TestGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestGroupRepository extends JpaRepository<TestGroup,Integer> {
}
