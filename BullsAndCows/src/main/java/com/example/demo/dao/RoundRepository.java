package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.example.demo.models.Round;

public interface RoundRepository {
	
	public int save (Round round);
	
	public int update (Round round);
	
	public Round findById (Integer roundId);
	
	public List<Round> findAll ();
	
	Integer findMaxRoundId();
	
	List<Round> findRoundsByGameId(Integer gameId);
	
	public int deleteById(Integer roundId);
	public int delete(Round round);

}
