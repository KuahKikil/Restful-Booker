package scenario;

import com.naniek.restassured.BelajarAutomation.API.apiengine.ApiCollection;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetBookingById {
    @Test
    public void GetBookById() {
        Scenario_Implementation s = new Scenario_Implementation();
        String idBook = s.sendObjectReturnId();
        if (idBook == null) {
            idBook = s.sendObjectReturnId();
        }
        System.out.println("baseUri : https://restful-booker.herokuapp.com/booking/" + idBook);
        Response response = new ApiCollection().getBookByIdAPI(idBook);
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println(response.asPrettyString());
    }
}
