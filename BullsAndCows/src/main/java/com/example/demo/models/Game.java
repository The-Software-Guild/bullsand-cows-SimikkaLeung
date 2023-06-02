package com.example.demo.models;

import java.util.Arrays;

import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.demo.exceptions.InvalidInputException;



public class Game {

	private static int length = 4;
	private Integer gameId;
	//private Integer[] answer;	// Since the answer may start with a zero, we cannot use a numeric data type.	
	private String answer;
	
	private boolean isFinished;		// The status of the game. since there are only two statuses, a boolean type is good.
	
	public Game() {
		super();
		this.isFinished = false;
	}
	
	
	public Game(Integer gameId, String answer) {
		super();
		this.gameId = gameId;
		this.answer = answer;
		this.isFinished = false;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean getIsFinished() {
		return isFinished;
	}
	
	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	
	public static int getLength() {
		return length;
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", answer=" + answer + ", isFinished=" + isFinished + "]";
	}

	public Game copy() {
		Game newGame = new Game(gameId,answer);
		newGame.setIsFinished(isFinished);
		return newGame;
	}
	
	
}
