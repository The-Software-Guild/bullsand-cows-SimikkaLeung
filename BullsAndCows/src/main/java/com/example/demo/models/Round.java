package com.example.demo.models;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;


public class Round {
	private Integer roundId;
	private String guess;
	private Integer exactMatch;
	private Integer partialMatch;
	private LocalDateTime time;

	
	public Round() {
		super();
		this.time = LocalDateTime.now();
	}
	
	public Round(Integer roundId, String guess, Integer exactMatch, Integer partialMatch) {
		super();
		this.roundId = roundId;
		this.guess = guess;
		this.exactMatch = exactMatch;
		this.partialMatch = partialMatch;
		this.time = LocalDateTime.now();
	}

	

	public String getGuess() {
		return guess;
	}



	public void setGuess(String guess) {
		this.guess = guess;
	}



	public Integer getExactMatch() {
		return exactMatch;
	}


	public void setExactMatch(Integer exactMatch) {
		this.exactMatch = exactMatch;
	}


	public Integer getPartialMatch() {
		return partialMatch;
	}


	public void setPartialMatch(Integer partialMatch) {
		this.partialMatch = partialMatch;
	}


	public Integer getRoundId() {
		return roundId;
	}

	public void setRoundId(Integer roundId) {
		this.roundId = roundId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	public int compareTo(Round round) {
		return this.time.compareTo(round.time);
	}



	@Override
	public String toString() {
		return "Round [roundId=" + roundId + ", guess=" + guess + ", exactMatch=" + exactMatch + ", partialMatch="
				+ partialMatch + ", time=" + time + "]";
	}


	
	
}
