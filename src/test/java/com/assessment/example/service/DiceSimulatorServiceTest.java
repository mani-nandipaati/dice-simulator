package com.assessment.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.assessment.example.model.DiceRun;
import com.assessment.example.repository.DiceRunRepository;

@SpringBootTest
class DiceSimulatorServiceTest {
	
	@Mock
	private DiceRunRepository diceRunRepository;
	
	@InjectMocks
	DiceSimulatorService diceSimulatorService;
	
	@Test
	void testRunDice() {
		DiceRun diceRun = diceSimulatorService.runDice(3, 6, 5);
		System.out.println(diceRun);
		assertEquals(3, diceRun.getNoOfDice());
		assertEquals(5, diceRun.getNoOfRuns());
		assertEquals(6, diceRun.getNoOfSides());
		assertTrue(diceRun.getDiceRolls().stream().allMatch((diceroll)-> diceroll.getDiceSum()<18));
		assertTrue(diceRun.getDiceRolls().stream().allMatch((diceroll)-> diceroll.getDiceSum()>2));
	}

}
