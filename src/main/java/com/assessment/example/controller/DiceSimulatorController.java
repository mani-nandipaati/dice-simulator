package com.assessment.example.controller;

import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.example.model.DiceRun;
import com.assessment.example.service.DiceSimulatorService;

@RestController
@Validated
@RequestMapping("api/v1/dice")
public class DiceSimulatorController {

	Logger logger = LoggerFactory.getLogger("DiceSimulatorController.class");
	
	@Autowired
	private DiceSimulatorService diceSimulatorService;
	
	@PostMapping(produces = "application/json")
	public DiceRun runDice(
			@RequestParam @Min(value = 1, message = "The number of dice must be at least 1") int numberOfDice, 
			@RequestParam @Min(value = 4, message = "The sides of a dice must be at least 4") int numberOfSides, 
			@RequestParam @Min(value = 1, message = "The total number of rolls must be at least 1") long numberOfRolls) {
		logger.info("Request to run dice for numberOfDice {} numberOfSides {} numberOfRolls {}",numberOfDice,numberOfSides,numberOfRolls);
		return diceSimulatorService.runDice(numberOfDice, numberOfSides, numberOfRolls);
	}
	
	@GetMapping(path = "/{id}")
	public DiceRun retrieveDiceRunInformation(@PathVariable("id") long id) {
		return diceSimulatorService.getDiceRunById(id);
	}
}
