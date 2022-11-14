package com.obinna.scorecardapi.repository;

import com.obinna.scorecardapi.model.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface StackRepository extends JpaRepository<Stack, Long> {
    List<Stack> findAllStackBySquadId(Long squadId);

    Stack findByStackName(String stackName);
    Optional<Stack> findById(Long id);



}
