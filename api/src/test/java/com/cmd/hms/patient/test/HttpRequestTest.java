package com.cmd.hms.patient.test;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Iterator;

@SpringBootTest(
  webEnvironment = WebEnvironment.DEFINED_PORT,
  properties = {
    "server.port=8079"
  })
@TestPropertySource(locations="classpath:application-test.properties") // Uses postgres database in memory deleted after testing
public class HttpRequestTest{

	@Autowired
    protected TestRestTemplate restTemplate;
	
	String BaseUrl ="http://localhost:8079/odata";

	public void addObject(String requestBody, String endpoint) throws Exception{
		String url = BaseUrl + endpoint;
		
		// Add object
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
		String postResponse = restTemplate.postForObject(url, request, String.class);
		JSONObject postJson = new JSONObject(postResponse).getJSONObject("d").getJSONObject("__metadata");
		String getUrl = postJson.get("id").toString();

		//Get response
		String getResponse = restTemplate.getForObject(getUrl,String.class);
		JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");

		// Compare created object with request
		JSONObject dataJson = new JSONObject(requestBody);
		Iterator<String> keys = dataJson.keys();

		while(keys.hasNext()) {
			String key = keys.next();
			if (dataJson.get(key) instanceof JSONObject) {
				assertEquals(dataJson.get(key), getJson.get(key));
			}
		}
		
	}

	public void invalidUpdateObject(String requestBody, String endpoint) throws Exception {
		String url = BaseUrl + endpoint;

		// Get initial address
        String beforeResponse = restTemplate.getForObject(url,String.class);
		JSONObject beforeJson = new JSONObject(beforeResponse).getJSONObject("d");

		// Try invalid update
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
        restTemplate.patchForObject(url, request, String.class);

		// Get entity
		String afterResponse = restTemplate.getForObject(url,String.class);
		JSONObject afterJson = new JSONObject(afterResponse).getJSONObject("d");

		// Compare object before and after invalid object
		// Object should be unchanged
		JSONObject dataJson = new JSONObject(requestBody);
		Iterator<String> keys = dataJson.keys();

		while(keys.hasNext()) {
			String key = keys.next();
			if (beforeJson.get(key) instanceof JSONObject) {
				assertEquals(beforeJson.get(key), afterJson.get(key));
			}
		}
	}

	public void updateObject(String requestBody, String endpoint) throws Exception {
		// Initialize
		String url = BaseUrl + endpoint;
		JSONObject requestJson = new JSONObject(requestBody);

		//Get initial object
		String beforeResponse = restTemplate.getForObject(url,String.class);
		JSONObject beforeJson = new JSONObject(beforeResponse).getJSONObject("d");
		
		// Add object
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(requestBody, headers);
        restTemplate.patchForObject(url, request, String.class);
		
		//Get updated object
		String getResponse = restTemplate.getForObject(url,String.class);
		JSONObject afterJson = new JSONObject(getResponse).getJSONObject("d");
		
		// Loop through update object json
		Iterator<String> keys = afterJson.keys();

		
		while(keys.hasNext()) {
			String key = keys.next();

			if(!key.endsWith("Details") && !key.startsWith("__")){ // Ignore metadata or joined object
				// Fields in update should be changed
				if(requestJson.has(key)){
					
						if(afterJson.get(key).toString().startsWith("/")){ // Comparing dates
							Long initialDate = Instant.parse(requestJson.get(key) + ".000Z").toEpochMilli(); // Convert request date to unix timestamp
							String s = afterJson.get(key).toString();
							Long updatedDate = Long.parseLong(s.substring(s.indexOf("(") + 1, s.indexOf(")"))); // Get unix timestamp of get date
							assertTrue((initialDate - updatedDate) == 0);
						} 
						else {
							assertEquals(requestJson.get(key), afterJson.get(key));
						}	
					
				} 
				// Fields not in update should remain unchanged
				else {
					assertEquals(beforeJson.get(key), afterJson.get(key));

				}
			}	
		}

	}

	public void deleteObject(String endpoint) throws JSONException{
		// Delete object
		String url = BaseUrl + endpoint;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		restTemplate.delete(url, String.class);

		//Check if object deleted
		String getResponse = restTemplate.getForObject(url,String.class);
		JSONObject getJson = new JSONObject(getResponse);

		assertTrue(getJson.has("error"));
	}
	
}