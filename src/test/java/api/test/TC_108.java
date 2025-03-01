package api.test;

import api.endpoints.PetEndPoints;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class TC_108 extends BaseTest{

    @Test
    public void retrieveInvalidPet(){
        logger.info("retrieve invalid pet method started");

        Response res = PetEndPoints.getPet(-1);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(),404);
        softAssert.assertEquals(resJsonPath.getString("type"), "error");
        softAssert.assertEquals(resJsonPath.getString("message"), "Pet not found");
        softAssert.assertAll();
    }
}
