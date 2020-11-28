package com.assessment.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assessment.example.model.DiceRun;

@Repository
public interface DiceRunRepository extends CrudRepository<DiceRun, Long>{

}
