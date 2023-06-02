package com.example.demo.services;

import java.util.List;
import java.util.Map;

import com.example.demo.exceptions.InvalidInputException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.models.Game;
import com.example.demo.models.Round;

public interface BullsAndCowsService {
	
//	public Map<Integer, Game> loadFromDB();
//	public Map<Integer, Game> saveToDB();	
	
	public String generateAnswer();
	public Game addGame();
	public Game getGame(Integer gameId);
	public List<Game> getAllGames();
	public boolean getGameStatus(Integer gameId);
	public void changeGameStatus(Integer gameId, boolean isFinished);

	public Round addLatestRound(Integer gameId, Round round);

	public List<Round> getAllRounds();
	public void setRound(Round Round);

	public Round makeAGuess(Integer gameId, Integer[] guess, String strGuess);
	String convertArrayToString (Integer[] arr);
	public Integer[] convertStringToArray (String input);
	public Integer[] validateGuess(String strGuess) throws InvalidInputException;
	public Integer validateGameId(String strGameId) throws InvalidInputException, NotFoundException;
	
	public boolean isFinished(Integer gameId, Round round);
	public List<Game> copyForDisplay(List<Game> gameList);
	public Game getGameForDisplay(Integer gameId);
	public List<Round> getRoundsOfGame(Integer gameId);
}
