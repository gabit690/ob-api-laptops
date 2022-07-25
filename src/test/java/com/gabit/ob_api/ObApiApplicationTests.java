package com.gabit.ob_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ObApiApplicationTests {

	@Test
	void contextLoads() {

		System.getenv().forEach(
				(key, value) -> System.out.println(key + " " + value)
		);
	}

}
