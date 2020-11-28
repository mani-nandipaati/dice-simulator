package com.assessment.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.example.model.DiceRoll;
import com.assessment.example.model.DiceRun;
import com.assessment.example.repository.DiceRunRepository;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class DiceSimulatorService {

	private final Random randomGenerator = new Random();
	
	@Autowired
	private DiceRunRepository diceRunRepository;
	
	public DiceRun runDice(int noOfDice, int noOfSides, long noOfRuns) {
		DiceRun diceRun = new DiceRun();
    	Map<Integer, Long> result = new HashMap<>();
    	List<DiceRoll> diceRolls = new ArrayList<>();
    	int sumOfDiceResult;
    	for (int count = 0; count < noOfRuns; count++) {
    		sumOfDiceResult = randomGenerator.ints(noOfDice, 1, noOfSides+1).sum();
    		result.put(sumOfDiceResult, result.getOrDefault(sumOfDiceResult, 0L) + 1);
    	}
    	diceRun.setNoOfDice(noOfDice);
    	diceRun.setNoOfSides(noOfSides);
    	diceRun.setNoOfRuns(noOfRuns);
    	result.forEach( (diceSum, diceSumCount) -> diceRolls.add(new DiceRoll(diceSum, diceSumCount, diceRun)));
    	diceRun.setDiceRolls(diceRolls);
    	diceRunRepository.save(diceRun);
    	return diceRun;
	}
	
	public DiceRun getDiceRunById(long id) {
		return  diceRunRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
}
