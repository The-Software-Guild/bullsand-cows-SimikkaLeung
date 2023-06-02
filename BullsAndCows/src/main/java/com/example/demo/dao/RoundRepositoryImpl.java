package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Round;
@Repository
public class RoundRepositoryImpl implements RoundRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(Round round) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("INSERT INTO round VALUES(?,?,?,?,?)",round.getRoundId(),round.getGuess() , round.getExactMatch(),round.getPartialMatch(),round.getTime());
	}

	@Override
	public int update(Round round) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("UPDATE round SET exactMatch = ?, partialMatch =? WHERE roundId = ?",round.getExactMatch(), round.getPartialMatch(), round.getRoundId());		
	}

	@Override
	public Round findById(Integer roundId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("SELECT * FROM round WHERE roundId = ?", BeanPropertyRowMapper.newInstance(Round.class), roundId);
	}

	@Override
	public List<Round> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM round", BeanPropertyRowMapper.newInstance(Round.class));
	}
	
	//The return value of maxroundid + 1 will be the round id of a new round.
	public Integer findMaxRoundId() {
		return jdbcTemplate.queryForObject("SELECT MAX(roundId) FROM round", Integer.class);
		 
	}
	
	public List<Round> findRoundsByGameId(Integer gameId) {
		return  jdbcTemplate.query("SELECT * FROM round r WHERE r.roundId IN ( SELECT gr.roundId FROM games_rounds gr WHERE gr.gameId = ?)", BeanPropertyRowMapper.newInstance(Round.class),gameId);

	}

	// The delete methods are used only in testing
	@Override
	public int deleteById(Integer roundId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM round WHERE roundId = ?", roundId);
	}

	@Override
	public int delete(Round round) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update("DELETE FROM round WHERE roundId = ?", round.getRoundId());
	}
}
