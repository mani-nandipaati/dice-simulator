package com.assessment.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class DiceRun{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long runId;
	
	@Column
	private int noOfDice;
	
	@Column
	private int noOfSides;
	
	@Column
	private long noOfRuns;
	
	@OneToMany(mappedBy = "diceRun",
			cascade = CascadeType.ALL)
	private List<DiceRoll> diceRolls;

	public long getRunId() {
		return runId;
	}

	public void setRunId(long runId) {
		this.runId = runId;
	}

	public int getNoOfDice() {
		return noOfDice;
	}

	public void setNoOfDice(int noOfDice) {
		this.noOfDice = noOfDice;
	}

	public int getNoOfSides() {
		return noOfSides;
	}

	public void setNoOfSides(int noOfSides) {
		this.noOfSides = noOfSides;
	}

	public long getNoOfRuns() {
		return noOfRuns;
	}

	public void setNoOfRuns(long noOfRuns) {
		this.noOfRuns = noOfRuns;
	}

	public List<DiceRoll> getDiceRolls() {
		return diceRolls;
	}

	public void setDiceRolls(List<DiceRoll> diceRolls) {
		this.diceRolls = diceRolls;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiceRun [noOfDices=");
		builder.append(noOfDice);
		builder.append(", noOfSides=");
		builder.append(noOfSides);
		builder.append(", noOfTimes=");
		builder.append(noOfRuns);
		builder.append(", diceRolls=");
		builder.append(diceRolls);
		builder.append("]");
		return builder.toString();
	}
	
	
}
