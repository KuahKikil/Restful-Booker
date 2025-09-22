package com.naniek.restassured.BelajarAutomation.API.scenario;

import com.naniek.restassured.BelajarAutomation.API.apiengine.ApiCollection;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteBook {
    @Test(dependsOnMethods = {"CreateBook"})
    public void DeleteBooking() {
        Scenario_Implementation s = new Scenario_Implementation();
        s.generate_token();
        String token =s.token;
        String idBook = s.sendObjectReturnId();
        if (idBook == null) {
            idBook = s.sendObjectReturnId();
        }
        System.out.println("Delete Booking");
        System.out.println("baseUri: https://restful-booker.herokuapp.com/booking/" + idBook);
        Response response = new ApiCollection().deleteBookingAPI(idBook, token);
        System.out.println("response" + response.asPrettyString());
    }
}
