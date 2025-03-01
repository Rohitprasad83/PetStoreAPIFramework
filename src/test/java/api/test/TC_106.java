package api.test;

import api.endpoints.PetEndPoints;
import api.endpoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;

public class TC_106 extends BaseTest{
    Store storePayload;
    Faker faker;
    @BeforeClass
    public void tc106_Setup(){
        faker = new Faker();
        storePayload = new Store();
        storePayload.setId(faker.number().randomDigitNotZero());
        storePayload.setPetId(faker.number().randomDigitNotZero());
        storePayload.setQuantity(faker.number().randomDigitNotZero());
        storePayload.setShipDate(Instant.now().toString());
        storePayload.setStatus("placed");
        storePayload.setComplete(faker.bool().bool());
    }

    @Test
    public void placeAnOrder() {
        logger.info("place an order method started");
        Response res = StoreEndPoints.postCreateOrder(storePayload);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertTrue(res.time() < 2000);
        softAssert.assertEquals(resJsonPath.getString("status"), "placed");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"placeAnOrder"})
    public void deleteAOrderedPet(){
        logger.info("delete an ordered pet method started");
        Response res = PetEndPoints.deletePet(storePayload.getPetId());
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 404);
    }
}

