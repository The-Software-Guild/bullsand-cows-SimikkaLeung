package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Game;
import com.example.demo.models.Round;
@Repository
public class GameRepositoryImpl implements GameRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(Game game) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("INSERT INTO game VALUES(?,?,?)", game.getGameId(),game.getAnswer(),game.getIsFinished());
	}

	@Override
	public int update(Game game) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("UPDATE game SET answer = ?, isFinished = ? WHERE gameid = ?", game.getAnswer(), game.getIsFinished() ,game.getGameId());
	}

	@Override
	public Game findById(Integer gameId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("SELECT * FROM game WHERE gameId = ?", 
				BeanPropertyRowMapper.newInstance(Game.class) , gameId);
	}

	@Override
	public List<Game> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM game", BeanPropertyRowMapper.newInstance(Game.class));
	}

	@Override
	public Integer findMaxGameId() {
		// TODO Auto-generated method stub
		
		
		return jdbcTemplate.queryForObject("SELECT MAX(gameId) FROM game", Integer.class);
	}

	@Override	
	public int saveRelation(Integer gameId, Integer roundId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("INSERT INTO games_rounds VALUES(?,?)", gameId,roundId);
	}
	
	public int deleteById(Integer gameId) {
		return jdbcTemplate.update("DELETE FROM game WHERE gameId = ?", gameId);
	}
	
	public int delete(Game game) {
		return jdbcTemplate.update("DELETE FROM game WHERE gameId = ?", game.getGameId());
	}
	
	public Integer deleteAllRelation() {
		return jdbcTemplate.update("DELETE FROM  games_rounds");
	}
	
}
