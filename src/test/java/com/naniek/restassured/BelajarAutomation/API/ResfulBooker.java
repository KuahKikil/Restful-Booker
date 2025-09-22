package com.naniek.restassured.BelajarAutomation.API;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class ResfulBooker {
    private static final Logger log = LoggerFactory.getLogger(ResfulBooker.class);
    /* A. Test Tanpa Auth
     * 1. Get Booking IDs = GET
     * 2. Get Booking = GET
     * 3. Create Booking = POST
     *
     * B. Test Dengan Auth
     * 1. Update Booking = PUT
     * 2. Partial Update Booking = PATCH
     * 3. Delete Booking = DELETE
     */

    String token;
    String idBook;

    @BeforeClass
    // === CREATE TOKEN ===
    public void generate_token() {
        String requestBody = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/auth")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();

        token = response.jsonPath().getString("token");
        System.out.println(response.asPrettyString());
    }

    // A. Test Tanpa Auth
    // 1. Get Booking IDs = GET
    @Test
    public void GetAllBook() {
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .header("Content-Type", "application/json")
                .when()
                .get();
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println(response.asPrettyString());
    }

    // 2. Get Booking = GET
    @Test
    public void GetBookById() {
        if (idBook == null) {
            idBook = sendObjectReturnId();
        }
        System.out.println("baseUri : https://restful-booker.herokuapp.com/booking/" + idBook);

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + idBook)
                .header("Accept", "application/json")
                .when()
                .get();
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println(response.asPrettyString());
    }

    // 3. Create Booking = POST
    @Test
    public void CreateBook() {
        Response response = given()
                .log().all()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"firstname\" : \"Jim\",\n" +
                        "    \"lastname\" : \"Brown\",\n" +
                        "    \"totalprice\" : 111,\n" +
                        "    \"depositpaid\" : true,\n" +
                        "    \"bookingdates\" : {\n" +
                        "        \"checkin\" : \"2018-01-01\",\n" +
                        "        \"checkout\" : \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\" : \"Breakfast\"\n" +
                        "}")
                .when()
                .post();
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println(response.asPrettyString());
    }

    // === SEND OBJECT RETURN ID ===
    public String sendObjectReturnId() {
        String requestBody = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();

        idBook = response.jsonPath().getString("bookingid");
        System.out.println("Booking ID: " + idBook);
        return idBook;
    }


    // B. Test Dengan Auth
    //  1. Update Booking = PUT
    @Test
    public void UpdateBooking() {
        if (idBook == null) {
            idBook = sendObjectReturnId();
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

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + idBook)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .body(requestBody)
                .when()
                .put();
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println("response" + response.asPrettyString());

    }

    // 2. Partial Update Booking = PATCH
    @Test
    public void PartialUpdateBooking() {
        if (idBook == null) {
            idBook = sendObjectReturnId();
        }
        System.out.println("baseUri: https://restful-booker.herokuapp.com/booking/" + idBook);
        System.out.println("Update Booking Partial");
        String requestBody = "{\n" +
                "    \"firstname\" : \"Indra\",\n" +
                "    \"lastname\" : \"Jegel\"\n" +
                "}";

        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + idBook)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .log().all()
                .body(requestBody)
                .when()
                .patch();
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.jsonPath().getString("firstname").equals("Indra"), "Firstname should be Indra");
        System.out.println("response" + response.asPrettyString());
    }

    // 3. Delete Booking = DELETE
    @Test(dependsOnMethods = {"CreateBook"})
    public void DeleteBooking() {
        if (idBook == null) {
            idBook = sendObjectReturnId();
        }
        System.out.println("Delete Booking");
        System.out.println("baseUri: https://restful-booker.herokuapp.com/booking/" + idBook);
        given()
                .baseUri("https://restful-booker.herokuapp.com/booking/" + idBook)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .when()
                .delete()
                .then()
                .statusCode(anyOf(equalTo(200), equalTo(201)))
                .log().all();
    }
}