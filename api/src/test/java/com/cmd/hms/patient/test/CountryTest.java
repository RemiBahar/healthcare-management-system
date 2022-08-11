package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.cmd.hms.patient.model.Country;

public class CountryTest extends IntegrationTest{

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
     @Order(Ordered.LOWEST_PRECEDENCE)
     public void addCountry() throws Exception {
         String requestBody = "{\n  \"CountryCode\": \"DE\",  \"Name\": \"Germany\" \n}";
         String endPoint = "/Countrys";
         
         assertTrue(add(endPoint, requestBody, this.adminToken));
     }


     @Test
     @Order(Ordered.LOWEST_PRECEDENCE)
     public void updateCountry() throws Exception {
        String requestBody = "{\"Name\": \"Great Britain\" \n}";
        String endPoint = "/Countrys('UK')";
        
        assertTrue(update(endPoint, requestBody, this.adminToken));
     }

     @Test
     @Order(Ordered.LOWEST_PRECEDENCE)
     public void invalidUpdateCountry() throws Exception {
        String requestBody = "{\n  \"CountryCode\": \"GB\"}"; // PATCH not ATOMIC can still update certain fields
        String endPoint = "/Countrys('UK')";

        assertTrue(invalidUpdateObject(requestBody, endPoint, this.adminToken));
     }

     @Test
     @Order(Ordered.LOWEST_PRECEDENCE)
     public void deleteCountry() throws Exception {
         String endPoint = "/Countrys('US')";
         String url = this.baseUrl + endPoint;
         
         assertFalse(delete(url, this.adminToken));
     }

     @Test
     @Order(Ordered.LOWEST_PRECEDENCE)
     // Assistance should not be able to READ Country
     public void assistanceGetCountry() throws Exception {
         Boolean request = invalidGet("/Countrys", this.assistanceToken);
         assertTrue(request);
     }
 
     @Test
     @Order(Ordered.LOWEST_PRECEDENCE)
     // User should not be able to READ Country
     public void userGetCountry() throws Exception {
         Boolean request = invalidGet("/Countrys", this.userToken);
         assertTrue(request);
     }
    
}