package com.naniek.restassured.BelajarAutomation.API.scenario;

import com.naniek.restassured.BelajarAutomation.API.apiengine.ApiCollection;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateBook {
    @Test
    public void CreateBook() {
        String requestBody = "{\n" +
                "  \"firstname\" : \"Jim\",\n" +
                "  \"lastname\" : \"Brown\",\n" +
                "  \"totalprice\" : 111,\n" +
                "  \"depositpaid\" : true,\n" +
                "  \"bookingdates\" : {\n" +
                "    \"checkin\" : \"2018-01-01\",\n" +
                "    \"checkout\" : \"2019-01-01\"\n" +
                "  },\n" +
                "  \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        Response response = new ApiCollection().createBookAPI(requestBody);
       String idBook = response.jsonPath().getString("bookingid");
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println(response.asPrettyString());
    }
}
