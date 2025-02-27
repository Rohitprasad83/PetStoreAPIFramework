package api.test;

import api.endpoints.PetEndPoints;
import api.endpoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;
import java.util.List;
import java.util.Random;

public class TC_105 extends BaseTest{
    Store storePayload;
    Faker faker;
    List<Object> petList;
    List<Long> petIds;
    @BeforeClass
    public void tc_105Setup(){
        faker = new Faker();
        storePayload = new Store();
        storePayload.setId(faker.number().randomDigitNotZero());
        storePayload.setQuantity(faker.number().randomDigitNotZero());
        storePayload.setShipDate(Instant.now().toString());
        storePayload.setStatus("placed");
        storePayload.setComplete(faker.bool().bool());
    }

    @Test(priority = 1)
    public void checkMoreThan10PetsAreAvailable(){
        logger.info("check more than 10 pets are available started");
        Response res = PetEndPoints.getPetByStatus("available");
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        petList = resJsonPath.getList("$");
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertTrue(petList.size() > 10);
        petIds = res.jsonPath().getList("id", Long.class);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkMoreThan10PetsAreAvailable"},invocationCount = 20)
    public void createPetOrderRequest(){
        logger.info("create Pet Order Request method started");
        storePayload.setPetId(getRandomPetId(petIds).intValue());
        Response res = StoreEndPoints.postCreateOrder(storePayload);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        logger.info("Reponse time is {}", res.time());
        softAssert.assertTrue(res.time() < 2000, "Response time exceeded 2000 ms");
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getString("status"), "placed");
        softAssert.assertAll();
    }

    private Long getRandomPetId(List<Long> petIds) {
        Long randomPetId = 0L;
        if (!petIds.isEmpty()) {
            Random random = new Random();
            randomPetId = petIds.get(random.nextInt(petIds.size()));
            return randomPetId;
        }
        return randomPetId;
    }
}
