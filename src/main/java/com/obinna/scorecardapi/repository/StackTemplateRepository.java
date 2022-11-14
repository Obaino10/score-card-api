package com.obinna.scorecardapi.repository;

import com.obinna.scorecardapi.model.StackTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StackTemplateRepository extends JpaRepository<StackTemplate,Long> {
    StackTemplate findByStackNameIgnoreCase(String stackName);
}
