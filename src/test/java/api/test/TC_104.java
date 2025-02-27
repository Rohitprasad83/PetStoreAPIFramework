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

public class TC_104 extends BaseTest{
    Store storePayload;
    Faker faker;
    int soldPetId = 893;
    @BeforeClass
    public void tc104Setup(){
        faker = new Faker();
        storePayload = new Store();
        storePayload.setId(faker.number().randomDigitNotZero());
        storePayload.setPetId(soldPetId);
        storePayload.setQuantity(faker.number().randomDigitNotZero());
        storePayload.setShipDate(Instant.now().toString());
        storePayload.setStatus("placed");
        storePayload.setComplete(faker.bool().bool());
    }

    @Test
    public void getSoldPet(){
        logger.info("get sold pet method started");

        Response res = PetEndPoints.getPet(soldPetId);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getString("status"), "sold");
        softAssert.assertAll();
    }

    @Test
    public void postOrderToASoldPet(){
        logger.info("post order to a sold pet method started");
        Response res = StoreEndPoints.postCreateOrder(storePayload);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 400);
    }
}
