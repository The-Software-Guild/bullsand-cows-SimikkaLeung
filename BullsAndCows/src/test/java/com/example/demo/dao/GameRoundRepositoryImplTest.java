package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Game;
import com.example.demo.models.Round;
@SpringBootTest
class GameRoundRepositoryImplTest {
	
	@Autowired
	TestGameRepositoryImpl testGameRepo;
	
	@Autowired
	TestRoundRepositoryImpl testRoundRepo;
	
	@BeforeEach
	void setUp() {
		testGameRepo.save(new Game(8, "3456"));
		testGameRepo.save(new Game(9, "9403"));
		testGameRepo.save(new Game(10, "0358"));
		testRoundRepo.save(new Round(22, "4389", 0, 1));
		testRoundRepo.save(new Round(23, "9403", 2, 2));
		testRoundRepo.save(new Round(24, "0234", 0, 0));
	}
	
	@AfterEach
	void cleanUp() {
		testGameRepo.deleteAllRelation();
		testGameRepo.deleteById(8);
		testGameRepo.deleteById(9);
		testGameRepo.deleteById(10);
		testRoundRepo.deleteById(22);
		testRoundRepo.deleteById(23);
		testRoundRepo.deleteById(24);
	}

	@Test
	void testSaveRelationFindRelation() {		
		assertEquals(testGameRepo.saveRelation(8,22),1);
		assertEquals(testGameRepo.deleteAllRelation(),1);
	}
}
