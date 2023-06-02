package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Round;
@SpringBootTest
class RoundRepositoryImplTest {

	
	@Autowired
	TestRoundRepositoryImpl testRoundRepo;
	
	@BeforeEach
	void setUp() {
		testRoundRepo.save(new Round(22, "4389", 0, 1));
		testRoundRepo.save(new Round(23, "9403", 2, 2));
		testRoundRepo.save(new Round(24, "0234", 0, 0));
	}
	
	@AfterEach
	void cleanUp() {
		testRoundRepo.deleteById(22);
		testRoundRepo.deleteById(23);
		testRoundRepo.deleteById(24);
	}
	
	@Test
	void testSaveAndDelete() {
		
		Round round = new Round(28,"7028",2,2);
		assertEquals(1,testRoundRepo.save(round));
		assertEquals(1,testRoundRepo.delete(round));
		
		assertEquals(1,testRoundRepo.save(round));
		assertEquals(1,testRoundRepo.deleteById(28));
	}
	
	@Test
	void testFindById() {		
		System.out.println(testRoundRepo.findById(23).getRoundId());
		assertEquals(testRoundRepo.findById(23).getRoundId(),23);
	}
	
	@Test
	void testFindAll() {		
		assertEquals(testRoundRepo.findAll().size(),3);
	}
	
	@Test
	void testUpdate() {		
		Round round23 = testRoundRepo.findById(23);
		round23.setExactMatch(1);
		testRoundRepo.update(round23);
		assertEquals(testRoundRepo.findById(23).getExactMatch(),1);
	}
	
	@Test
	void testFindMaxRoundId() {		
		assertEquals(testRoundRepo.findMaxRoundId(),24);
	}
}
