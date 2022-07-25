package com.gabit.ob_api.controllers;

import com.gabit.ob_api.entities.LaptopEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {

        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);

        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("CREATE A NEW LAPTOP")
    @Test
    void createTest() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "model": "Hp G140",
                    "price": 45000.99
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<LaptopEntity> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, LaptopEntity.class);

        LaptopEntity result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Hp G140", result.getModel());
        assertEquals(45000.99, result.getPrice());
    }

    @DisplayName("FIND ALL LAPTOPS IN DATABASE")
    @Test
    void findAllTest() {

        ResponseEntity<LaptopEntity[]> response =  testRestTemplate.getForEntity("/api/laptops", LaptopEntity[].class);

        List<LaptopEntity> laptops = Arrays.asList(response.getBody());

        assertNotNull(laptops);

        assertEquals(laptops.size(), 0);
    }

    @DisplayName("CHECK A LAPTOP WITH ID 1 WHICH DOESN'T EXIST")
    @Test
    void findOneByIdTest() {

        ResponseEntity<LaptopEntity> response = testRestTemplate.getForEntity("/api/laptops/1", LaptopEntity.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("TRY TO UPDATE AN LAPTOP WHICH DOESN'T EXIST")
    @Test
    void updateTest() {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": 1,
                    "model": "Hp G140",
                    "price": 45000.99
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<LaptopEntity> response = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, request, LaptopEntity.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("TRY TO DELETE A LAPTOP WITH ID 1 WHICH DOESN'T EXIST")
    @Test
    void deleteByIdTest() {

        ResponseEntity<LaptopEntity> response = testRestTemplate.exchange("/api/laptops/1", HttpMethod.DELETE, null, LaptopEntity.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @DisplayName("DELETE ALL LAPTOPS IN DATABASE")
    @Test
    void deleteAllTest() {

        ResponseEntity<LaptopEntity> response = testRestTemplate.exchange("/api/laptops", HttpMethod.DELETE, null, LaptopEntity.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}