package com.naniek.restassured.BelajarAutomation.API.apiengine;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class ApiCollection {
    public ApiCollection (){

    };
    public Response getAllBookAPI (){
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .header("Content-Type", "application/json")
                .when()
                .get();
        return response;
    }

    public Response getBookByIdAPI (String idBook){
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + idBook)
                .header("Accept", "application/json")
                .when()
                .get();
        return response;
    }

    public Response createBookAPI (String requestBody){
        Response response = given()
                .log().all()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();
        return response;
    }
    public  Response updateBookingAPI (String idBook, String token, String requestBody){
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + idBook)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .body(requestBody)
                .when()
                .put();
        return response;
    }

    public Response partialUpdateBookingAPI (String idBook, String token, String requestBody){
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + idBook)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .log().all()
                .body(requestBody)
                .when()
                .patch();
        return response;
    }

    public Response deleteBookingAPI (String idBook, String token){
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + idBook)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .log().all()
                .when()
                .delete();
        return response;
    }

}
