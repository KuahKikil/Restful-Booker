package scenario;

import com.naniek.restassured.BelajarAutomation.API.apiengine.ApiCollection;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;

public class Scenario_Implementation {
    public String token;
    public String idBook;
    public ApiCollection apiCollection;

    // === GENERATE TOKEN ====
    @BeforeClass
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
        apiCollection = new ApiCollection();
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
}