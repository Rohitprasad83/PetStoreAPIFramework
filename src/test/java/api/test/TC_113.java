package api.test;

import api.endpoints.PetEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TC_113 extends BaseTest{

    @Test
    public void checkPetStatusIsOnlyAvailablePresent(){
        logger.info("check pet status is only available present method started");

        Response res = PetEndPoints.getPetByStatus("available");

        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        List<String> petStatus = resJsonPath.getList("status");

        SoftAssert softAssert = new SoftAssert();
        for (String pet : petStatus) {
            softAssert.assertTrue(pet.equalsIgnoreCase("available"), "Non-available pet found: " + pet);
        }
        softAssert.assertAll();
    }
}
