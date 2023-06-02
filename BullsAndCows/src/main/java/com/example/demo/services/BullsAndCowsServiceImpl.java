package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GameRepository;
import com.example.demo.dao.RoundRepository;
import com.example.demo.exceptions.*;
import com.example.demo.models.Game;
import com.example.demo.models.Round;

@Service
public class BullsAndCowsServiceImpl implements BullsAndCowsService {
	
	@Autowired
	GameRepository gameRepo;
	
	@Autowired
	RoundRepository roundRepo;

	// Use Random to generate 4 unique digits.
	public String generateAnswer() {
		Random rand = new Random();
		boolean isUnique = true;
		
		Integer[] correctAnswerArr = new Integer[Game.getLength()];
		for (int i = 0; i < correctAnswerArr.length; i++ ) {
			do {				
				isUnique = true;
				correctAnswerArr[i] = rand.nextInt(10);		// It generates a digit from 0 to 9.
				for (int j = 0; j < i; j++) {
					if ( correctAnswerArr[i] == correctAnswerArr[j] ) {
						isUnique = false;
					}
				}
			} while(!isUnique);			
		}
		
		String answer = "";
		
		for (int i = 0; i < correctAnswerArr.length; i++) {
				answer += correctAnswerArr[i].toString();

		}
		return answer;
	}
	
	
	@Override
	public Game addGame() {
		// TODO Auto-generated method stub
		Game game = new Game(gameRepo.findMaxGameId()+1,generateAnswer());
		gameRepo.save(game);
		return game;
	}

	@Override
	public Game getGame(Integer gameId) {
		// TODO Auto-generated method stub
		return gameRepo.findById(gameId);
	}

	@Override
	public List<Game> getAllGames() {
		// TODO Auto-generated method stub
		return gameRepo.findAll();
	}

	
	public boolean getGameStatus(Integer gameId) {
		// TODO Auto-generated method stub
		return gameRepo.findById(gameId).getIsFinished();
	}

	@Override
	public void changeGameStatus(Integer gameId, boolean isFinished) {
		// TODO Auto-generated method stub
		gameRepo.findById(gameId).setIsFinished(isFinished);
	}

	
	
	@Override
	public Round addLatestRound(Integer gameId, Round round) {
		// TODO Auto-generated method stub
		roundRepo.save(round);
		gameRepo.saveRelation(gameId, round.getRoundId());		
		return round;
		
	}


	@Override
	public List<Round> getAllRounds() {
		// TODO Auto-generated method stub
		return roundRepo.findAll();
	}

	@Override
	public void setRound(Round Round) {
		// TODO Auto-generated method stub
		roundRepo.update(Round);
	}

	// Check if the guess matches the answer
	@Override
	public Round makeAGuess(Integer gameId, Integer[] guess, String strGuess) {
		// TODO Auto-generated method stub
		int[] match = new int[2];		// match[0] for exact matches, match[1] for partial matches.
		Integer[] answer = convertStringToArray(getGame(gameId).getAnswer());
		for (int i = 0; i < guess.length; i++) {			
			for (int j = 0; j< guess.length; j++) {
				if (guess[i] == answer[j]) {
					if (i==j) {
						match[0]++;
						
					} else {
						match[1]++;
					}
				}
				
			}
		}
		Round round = new Round(roundRepo.findMaxRoundId()+1, strGuess, match[0], match[1]);
		addLatestRound(gameId, round);
		return round;
	}

	// A helper function 
	public String convertArrayToString (Integer[] arr) {
		String tempStr = "";
		
		for (int i = 0; i < arr.length; i++) {
				tempStr += arr[i].toString();

		}
		return tempStr;
	}
	
	// A helper function 
	public Integer[] convertStringToArray (String input) {
		Integer[] arr = new Integer[Game.getLength()];
		Integer digit = -1;
		for (int i = 0; i < arr.length; i++) {
				digit = Integer.parseInt(input.substring(i,i+1));
				arr[i] = digit;
				
		}
		
		return arr;
	}
	
	// Set the isFinished value to true when a guess matches the answer exactly.
	public boolean isFinished(Integer gameId, Round round) {
		Game game = null;
		if (round.getExactMatch() == Game.getLength()) {

			game = getGame(gameId);
			game.setIsFinished(true);
			gameRepo.update(game);
			return true;
		}
		return false;
	}
	
	// Valid the input and throw various exceptions
	@Override
	public Integer[] validateGuess(String strGuess) throws InvalidInputException {
		// TODO Auto-generated method stub
		if (strGuess.length() != 4) {
			throw new InvalidInputException("Invalid Input: A guess must have exactly 4 integers.");
		}
			
		
		Integer[] guess = new Integer[Game.getLength()];
		Integer digit = -1;
		for (int i = 0; i < guess.length; i++) {
			try {
				digit = Integer.parseInt(strGuess.substring(i,i+1));			
				guess[i] = digit;
				for (int j = 0; j < i; j++) {
					if (guess[j]==guess[i]) {
						throw new InvalidInputException("Invalid Input: The four digits cannot be repeated.");
					}
				}
				
			} catch (NumberFormatException nfex) {
				throw new InvalidInputException("Invalid Input: A guess can only have integers.");
			}
		}

		return guess;
	}

	@Override
	public Integer validateGameId(String strGameId) throws InvalidInputException, NotFoundException {
		// TODO Auto-generated method stub
		Integer gameId = -1;
		try {
			gameId = Integer.parseInt(strGameId);
		} catch (NumberFormatException nfex) {
			throw new InvalidInputException("Invalid Input: A gameId must be an integer.");
		}
		
		if (gameId <= 0) {
			throw new InvalidInputException("Invalid Input: A gameId must be a positive integer.");
		}
		
		if (gameRepo.findById(gameId) == null) {
			throw new NotFoundException("Invalid Input: No game with this id is found.");
		}
		
		return gameId;
	}
	// hide the answer of games that are in progress.
	public List<Game> copyForDisplay(List<Game> gameList) {
		List<Game> gameListDisplay = new ArrayList<Game>();
		
		
		for (Game game: gameList) {
			Game gameDisplay = game.copy();
			if (!gameDisplay.getIsFinished()) {
				gameDisplay.setAnswer(null);
			}
			gameListDisplay.add(gameDisplay);
		}
		
		return gameListDisplay;
	}
	// Hide the answer of all games that are in progress.
	public Game getGameForDisplay(Integer gameId) {
		
		Game gameDisplay = getGame(gameId).copy();
		if (!gameDisplay.getIsFinished()) {
			gameDisplay.setAnswer(null);
		}
		return gameDisplay;
	}
	// Hide the answer of a game that is in progress.
	public List<Round> getRoundsOfGame(Integer gameId) {

		return roundRepo.findRoundsByGameId(gameId);
	}
}
