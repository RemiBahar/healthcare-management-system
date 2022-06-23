package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application-test.properties") // Uses H2 database deleted after testing
public class HttpRequestTest {


	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/odata/$metadata",
				String.class)).contains("Schema");
	}
	
	
	
	@Test
	public void addGender() throws Exception {
		// Add gender 19.802, 18.817, 18.597
		String data = "{\n  \"Title\": \"Male\" \n}";
		String url ="http://localhost:" + port + "/odata/Genders";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(data, headers);
		restTemplate.postForObject(url, request, String.class);

		// Get added Gender
		String getResponse = restTemplate.getForObject("http://localhost:" + port + "/odata/Genders(1)",String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare added gender with request
		assertEquals("1", getJson.get("GenderId"));
		assertEquals("Male", getJson.get("Title"));
		assertEquals(false, getJson.get("IsDeleted"));
	}
	
}