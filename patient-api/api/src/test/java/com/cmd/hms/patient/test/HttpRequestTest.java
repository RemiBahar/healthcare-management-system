package com.cmd.hms.patient.test;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

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

	public String postObject(String data, String url) throws Exception{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(data, headers);
		String postResponse = restTemplate.postForObject(url, request, String.class);
		JSONObject postJson = new JSONObject(postResponse).getJSONObject("d").getJSONObject("__metadata");
		return postJson.get("id").toString();
	}

    

   public String postPatientStatusType(String Status) throws Exception {
	String data = String.format("{\n  \"Status\": \"%s\" \n}", Status);
	String url = BaseUrl + "/PatientStatusTypes";
	String getUrl = postObject(data, url);

	return getUrl;

   }

    


	
	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject(BaseUrl + "/$metadata",
				String.class)).contains("Schema");
	}

	
}