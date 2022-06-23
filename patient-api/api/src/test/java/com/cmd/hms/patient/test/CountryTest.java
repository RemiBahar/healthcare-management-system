package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;


import com.cmd.hms.patient.model.Country;

public class CountryTest extends HttpRequestTest{

     // Instanstiate object
     Country Country = new Country();

     // Tests
     @Test
     public void CountryCodeTest(){
         String CountryCode = "UK";
         Country.setCountryCode(CountryCode);
             
         assertTrue(Country.getCountryCode().equals(CountryCode), "CountryCode");
     }

     @Test
     public void NameTest(){
         String Name = "United Kingdom of Great Britain and Northern Ireland";
         Country.setName(Name);
             
         assertTrue(Country.getName().equals(Name), "Name");
     }

     @Test
     public void addCountry() throws Exception {
         // Add object 
         String CountryCode = "DE";
         String Name = "Germany";
 
         String data = String.format("{\n  \"CountryCode\": \"%s\",  \"Name\": \"%s\" \n}", CountryCode, Name);
         String url = BaseUrl + "/Countrys";
         
         String getUrl = postObject(data, url);
     
         // Get added object
         String getResponse = restTemplate.getForObject(getUrl,String.class);
         JSONObject getJson = new JSONObject(getResponse).getJSONObject("d");
 
         // Compare added object with request
         assertEquals(CountryCode, getJson.get("CountryCode"));
         assertEquals(Name, getJson.get("Name"));
     }
 
    
}