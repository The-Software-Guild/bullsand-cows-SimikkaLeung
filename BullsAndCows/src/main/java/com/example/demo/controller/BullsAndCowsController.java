package com.example.demo.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.GameRepository;
import com.example.demo.dao.RoundRepository;
import com.example.demo.exceptions.*;
import com.example.demo.models.Game;
import com.example.demo.models.Round;
import com.example.demo.services.BullsAndCowsService;
@RestController
@ResponseBody
public class BullsAndCowsController {
	/*
	 * REST endpoints:

"begin" - POST – Starts a game, generates an answer, and sets the correct status. Should return a 201 CREATED message as well as the created gameId.
"guess" – POST – Makes a guess by passing the guess and gameId in as JSON. The program must calculate the results of the guess and mark the game finished if the guess is correct. It returns the Round object with the results filled in.
"game" – GET – Returns a list of all games. Be sure in-progress games do not display their answer.
"game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
"rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.
	 */
	
	@Autowired
	private BullsAndCowsService myService;
	
	
	@PostMapping("/begin")
	@ResponseStatus(HttpStatus.CREATED)
	public String begin() {
		Game game = myService.addGame();
		return "A new game has just been created! The game id is " + game.getGameId() + ".";
	}
	
	@PostMapping("/guess")
	public ResponseEntity<Object> makeAGuess(@RequestBody Map<String,String> gameAndGuess, String strGuess) throws InvalidInputException {
		
		Integer gameId = null;
		String guess = null;

		
		// Use ResponseHandler and ExceptiongHandler to deliver various HttpStatus and Exception Info.
		try {
			for (Entry<String,String> entry: gameAndGuess.entrySet()) {
				gameId = myService.validateGameId(entry.getKey());
				guess = entry.getValue();
				break;	// We only need to loop once.
			}
			Round round = myService.makeAGuess(gameId, myService.validateGuess(guess),guess);
			if (myService.isFinished(gameId,round)) {

				String message = "Good job! You have guessed correctly!";
				return ResponseHandler.generateResponse(message, HttpStatus.OK, round);
			} 
			return ResponseHandler.generateResponse("Keep going!", HttpStatus.OK, round);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
		
	}
	@GetMapping("/game")
	public List<Game> getAllGames() {
		List<Game> gameList= myService.getAllGames();

		return myService.copyForDisplay(gameList);		
	}
	
	
	@GetMapping("/get/{gameId}")
	public Game getGame(@PathVariable("gameId") Integer gameId) {
				
		return myService.getGameForDisplay(gameId);
	}
	
	@GetMapping("/rounds")
	public List<Round> getRoundList() {
		return myService.getAllRounds();
	}
	
	@GetMapping("/rounds/{gameId}")
	public List<Round> getRound(@PathVariable("gameId") Integer gameId) {
		return myService.getRoundsOfGame(gameId);
	}
}
