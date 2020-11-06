package com.cgr.files.fileman;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FileManApplicationTests {

	@LocalServerPort
	private int randomServerPort;

	@Test
	void contextLoads() {
		RestTemplate t = new RestTemplate();
		String name = "Mario";
		ResponseEntity<?> entity = t.getForEntity("http://localhost:" + randomServerPort + "/?name={name}", Map.class,
				name);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertTrue(entity.getBody().toString().contains("Hello Mario"));
	}

}
