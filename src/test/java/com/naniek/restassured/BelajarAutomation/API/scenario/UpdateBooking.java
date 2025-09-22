package com.naniek.restassured.BelajarAutomation.API.scenario;

import com.naniek.restassured.BelajarAutomation.API.apiengine.ApiCollection;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateBooking {
    @Test
    public void UpdateBooking() {
        Scenario_Implementation s = new Scenario_Implementation();
        s.generate_token();
        String token =s.token;
        String idBook = s.sendObjectReturnId();
        if (idBook == null) {
            idBook = s.sendObjectReturnId();
        }
        System.out.println("baseUri: https://restful-booker.herokuapp.com/booking/" + idBook);
        System.out.println("Update Booking");
        String requestBody = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = new ApiCollection().updateBookingAPI(idBook, token, requestBody);
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println("response" + response.asPrettyString());

    }
}
