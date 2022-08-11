package com.cmd.hms.patient.test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(
  webEnvironment = WebEnvironment.DEFINED_PORT,
  properties = {
    "server.port=8090"
  })
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties") // Uses postgres database in memory deleted after testing
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest {
    
}
