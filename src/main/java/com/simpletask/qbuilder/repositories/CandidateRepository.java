package com.simpletask.qbuilder.repositories;

import com.simpletask.qbuilder.entities.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
    Candidate findCandidateByEmail(String email);
    Page<Candidate> findAll(Pageable pageable);
    Page<Candidate> findAllByLastNameContains(String search,Pageable pageable);
}
