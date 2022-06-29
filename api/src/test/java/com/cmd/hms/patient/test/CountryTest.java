package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
         String requestBody = "{\n  \"CountryCode\": \"DE\",  \"Name\": \"Germany\" \n}";
         String endpoint = "/Countrys";
         addObject(requestBody, endpoint);
     }


     @Test
     public void updateCountry() throws Exception {
         String requestBody = "{\"Name\": \"Great Britain\" \n}";
         String endpoint = "/Countrys('UK')";
         updateObject(requestBody, endpoint);
     }

     @Test
     public void invalidUpdateCountry() throws Exception {
         String requestBody = "{\n  \"CountryCode\": \"GB\",\"Name\": \"Great Britain\" \n}"; // Shoudn't be able to set PK in update
         String endpoint = "/Countrys('UK')";
         invalidUpdateObject(requestBody, endpoint);
     }

     @Test
     public void deleteCountry() throws Exception {
         String endpoint = "/Countrys('US')";
         deleteObject(endpoint);
     }
    
}