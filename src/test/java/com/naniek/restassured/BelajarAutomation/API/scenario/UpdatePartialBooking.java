package com.naniek.restassured.BelajarAutomation.API.scenario;

import com.naniek.restassured.BelajarAutomation.API.apiengine.ApiCollection;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdatePartialBooking {
    @Test
    public void PartialUpdateBooking() {
        Scenario_Implementation s = new Scenario_Implementation();
        s.generate_token();
        String token = s.token;
        String idBook = s.sendObjectReturnId();
        if (idBook == null) {
            idBook = s.sendObjectReturnId();
        }
        System.out.println("baseUri: https://restful-booker.herokuapp.com/booking/" + idBook);
        System.out.println("Update Booking Partial");
        String requestBody = "{\n" +
                "    \"firstname\" : \"Indra\",\n" +
                "    \"lastname\" : \"Jegel\"\n" +
                "}";

        Response response = new ApiCollection().partialUpdateBookingAPI(idBook, token, requestBody);
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.jsonPath().getString("firstname").equals("Indra"), "Firstname should be Indra");
        System.out.println("response" + response.asPrettyString());
    }
}
