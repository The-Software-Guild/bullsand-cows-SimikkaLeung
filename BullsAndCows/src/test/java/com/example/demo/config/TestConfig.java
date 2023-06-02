package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.dao.GameRepository;
import com.example.demo.dao.GameRepositoryImpl;
import com.example.demo.dao.RoundRepository;
import com.example.demo.dao.RoundRepositoryImpl;
import com.example.demo.dao.TestGameRepositoryImpl;
import com.example.demo.dao.TestRoundRepositoryImpl;

@Configuration
public class TestConfig {
	@Bean	
	public GameRepository testGameRepo() {
		return new TestGameRepositoryImpl();
	}
	@Bean	
	public RoundRepository testRoundRepo() {
		return new TestRoundRepositoryImpl();
	}
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate();
//		return jdbcTemplate;
//	}
	
}
