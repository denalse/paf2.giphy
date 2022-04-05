package nus.iss.paf.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nus.iss.paf.day12.service.GiphyService;

@SpringBootTest
class Day12ApplicationTests {


	@Autowired
	private GiphyService giphySvc;

	@Test	
	void shouldLoad10Images() {
		List<String> q = giphySvc.getGiphs("dog");
		assertEquals(10, q.size(), "Default number of Gifs : ");
	}

}
