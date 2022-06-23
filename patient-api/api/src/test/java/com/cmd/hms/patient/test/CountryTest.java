package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.cmd.hms.patient.model.Country;

public class CountryTest{

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

    
    
}