package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Game;
import com.example.demo.models.Round;

public interface GameRepository{
	  int save(Game game);

	  int update(Game game);

	  Game findById(Integer gameId);

	  List<Game> findAll();
	  
	  Integer findMaxGameId();
	  
	  int saveRelation(Integer gameId, Integer roundId);
	  
	  Integer deleteAllRelation();

	  public int deleteById(Integer gameId);
	  public int delete(Game game);
}
