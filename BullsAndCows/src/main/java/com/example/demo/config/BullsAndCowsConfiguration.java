package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.dao.GameRepository;
import com.example.demo.dao.GameRepositoryImpl;
import com.example.demo.dao.RoundRepository;
import com.example.demo.dao.RoundRepositoryImpl;

@Configuration
public class BullsAndCowsConfiguration {
	@Bean	
	public GameRepository gameRepo() {
		return new GameRepositoryImpl();
	}
	@Bean	
	public RoundRepository roundRepo() {
		return new RoundRepositoryImpl();
	}

	
}
