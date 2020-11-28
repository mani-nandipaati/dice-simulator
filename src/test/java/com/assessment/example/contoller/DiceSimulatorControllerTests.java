package com.assessment.example.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.assessment.example.model.DiceRoll;
import com.assessment.example.model.DiceRun;
import com.assessment.example.service.DiceSimulatorService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
class DiceSimulatorControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DiceSimulatorService diceSimulatorService;
	
	private ObjectMapper MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Test
	void testRunDice() throws Exception{
		DiceRun diceRun = new DiceRun();
		List<DiceRoll> diceRolls = new ArrayList<>();
		diceRolls.add(new DiceRoll(18,5,diceRun));
		diceRun.setDiceRolls(diceRolls);
		diceRun.setNoOfDice(3);
		diceRun.setNoOfSides(6);
		diceRun.setNoOfRuns(5);
		when(diceSimulatorService.runDice(3, 6, 5)).thenReturn(diceRun);
		MvcResult requestResult = mockMvc.perform(post("/api/v1/dice")
				.queryParam("numberOfDice", "3")
				.queryParam("numberOfSides", "6")
				.queryParam("numberOfRolls", "5"))
				.andExpect(status().isOk())
				.andReturn();
		DiceRun response = parseResponse(requestResult, DiceRun.class);
		assertEquals(diceRun.getNoOfDice(), response.getNoOfDice());
		assertEquals(diceRun.getNoOfRuns(), response.getNoOfRuns());
		assertEquals(diceRun.getNoOfSides(), response.getNoOfSides());
		assertEquals(diceRun.getDiceRolls().size(), response.getDiceRolls().size());
		assertTrue(diceRun.getDiceRolls().containsAll(response.getDiceRolls()));
	}

	

	public <T> T parseResponse(MvcResult result, Class<T> responseClass) {
		try {
			String contentAsString = result.getResponse().getContentAsString();
			return MAPPER.readValue(contentAsString, responseClass);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
