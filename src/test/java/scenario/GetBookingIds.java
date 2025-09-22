package scenario;

import com.naniek.restassured.BelajarAutomation.API.apiengine.ApiCollection;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetBookingIds {
    @Test
    public void GetAllBook() {
        Response response = new ApiCollection().getAllBookAPI();
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println(response.asPrettyString());
    }
}
