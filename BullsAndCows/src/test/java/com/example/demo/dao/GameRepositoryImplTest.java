package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.config.TestConfig;
import com.example.demo.models.Game;
import com.example.demo.models.Round;


@SpringBootTest
class GameRepositoryImplTest {

	@Autowired
	TestGameRepositoryImpl testGameRepo;

	
	@BeforeEach
	void setUp() {
		testGameRepo.save(new Game(8, "3456"));
		testGameRepo.save(new Game(9, "9403"));
		testGameRepo.save(new Game(10, "0358"));
	}
	
	@AfterEach
	void cleanUp() {
		testGameRepo.deleteById(8);
		testGameRepo.deleteById(9);
		testGameRepo.deleteById(10);
	}
	
	@Test
	void testSaveAndDelete() {
		
		Game game = new Game(11,"5386");
		assertEquals(1,testGameRepo.save(game));
		assertEquals(1,testGameRepo.delete(game));
		
		assertEquals(1,testGameRepo.save(game));
		assertEquals(1,testGameRepo.deleteById(11));
	}
	
	@Test
	void testFindById() {		
		assertEquals(10,testGameRepo.findById(10).getGameId());
	}
	
	@Test
	void testFindAll() {		
		assertEquals(3,testGameRepo.findAll().size());
	}
	
	@Test
	void testUpdate() {		
		Game game10 = testGameRepo.findById(10);
		game10.setIsFinished(true);
		testGameRepo.update(game10);
		assertTrue(testGameRepo.findById(10).getIsFinished());
	}
	
	@Test
	void testFindMaxGameId() {		
		assertEquals(testGameRepo.findMaxGameId(),10);
	}
	
//	@Test
//	void testSaveRelationFindRelation() {		
//		Round round = new Round(11,"3948",0,0);
//		assertEquals(testGameRepo.saveRelation(8,round.roundId),1);
//	}
	
	
}
