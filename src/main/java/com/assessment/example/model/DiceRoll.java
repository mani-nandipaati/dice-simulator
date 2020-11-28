package com.assessment.example.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class DiceRoll{

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long rollId;
	
	@Column
	private int diceSum;
	
	@JsonProperty("count")
	@Column
    private long diceSumCount;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name ="diceRun_runId")
	private DiceRun diceRun;
	
	public DiceRoll() {	
	}
	
	public DiceRoll(int diceSum, long diceSumCount, DiceRun diceRun) {
		this.diceSum = diceSum;
		this.diceSumCount = diceSumCount;
		this.diceRun = diceRun;
	}
	
	public long getRollId() {
		return rollId;
	}

	public void setRollId(long rollId) {
		this.rollId = rollId;
	}

	public int getDiceSum() {
		return diceSum;
	}

	public void setDiceSum(int diceSum) {
		this.diceSum = diceSum;
	}

	public long getDiceSumCount() {
		return diceSumCount;
	}

	public void setDiceSumCount(long diceSumCount) {
		this.diceSumCount = diceSumCount;
	}

	public DiceRun getDiceRun() {
		return diceRun;
	}

	public void setDiceRun(DiceRun diceRun) {
		this.diceRun = diceRun;
	}
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiceRoll [diceSum=");
		builder.append(diceSum);
		builder.append(", diceSumCount=");
		builder.append(diceSumCount);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof DiceRoll) {
        	DiceRoll diceRoll = (DiceRoll)anObject;
            if (this.diceSum == diceRoll.getDiceSum() &&
            		this.diceSumCount == diceRoll.getDiceSumCount()) {
            	return true;
            }
        }
        return false;
    }
	
	@Override
	public int hashCode() {
        return Arrays.hashCode(new Object[] {diceSum,diceSumCount});
    }
}
