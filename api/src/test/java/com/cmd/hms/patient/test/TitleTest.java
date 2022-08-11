package com.cmd.hms.patient.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import com.cmd.hms.patient.model.Title;

public class TitleTest extends IntegrationTest{

    // Instanstiate object
    Title title = new Title();

    // Tests
    @Test
    public void IdTest(){
        Long Id = 1L;
        title.setTitleId(Id);
            
        assertTrue(title.getTitleId().equals(Id), "Id");
    }

    @Test
    public void TitleMethodTest(){
        String Title = "Mr";
        title.setTitle(Title);
            
        assertTrue(title.getTitle().equals(Title), "Title");
    }

    // Integration tests
    @Test
    public void addTitle() throws Exception {
        String requestBody = "{\n  \"Title\": \"Add Title\" \n}";
        String endPoint = "/Titles";

        assertTrue(add(endPoint, requestBody, this.adminToken));
    }

    @Test
    public void updateTitle() throws Exception {
        String requestBody = "{\n  \"Title\": \"Update Title\" \n}";
        String endPoint = "/Titles(1)";

        update(endPoint, requestBody, this.adminToken);
    }

    @Test
    public void invalidUpdateGender() throws Exception {
        String requestBody = "{\n \"TitleId\": \"10\", \"Title\": \"Invalid title\" \n}";
        String endPoint = "/Titles(1)";
        assertTrue(invalidUpdateObject(endPoint, requestBody, this.adminToken));
    }

    @Test
    public void deleteTitle() throws Exception {
        String endPoint = "/Titles(2)";
        String url = this.baseUrl + endPoint;

        assertFalse(delete(url, this.adminToken));
    }

    @Test
    // Assistance should not be able to READ Title
    public void assistanceGetTitle() throws Exception {
        Boolean request = invalidGet("/Titles", this.assistanceToken);
        assertTrue(request);
    }

    @Test
    // User should not be able to READ Title
    public void userGetTitle() throws Exception {
        Boolean request = invalidGet("/Titles", this.userToken);
        assertTrue(request);
    }
}