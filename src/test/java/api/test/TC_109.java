package api.test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;

public class TC_109 extends BaseTest {
    Store storePayload;
    Faker faker;
    @BeforeClass
    public void tc_109Setup(){
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
    public void placeOrder() {
        logger.info("place order method started");
        Response res = StoreEndPoints.postCreateOrder(storePayload);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getString("status"), "placed");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "placeOrder")
    public void deleteAPlacedOrder(){
        logger.info("delete a placed order method started");
        Response res = StoreEndPoints.deleteOrder(String.valueOf(storePayload.getId()));
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getInt("code"), 200);
        softAssert.assertAll();
    }
}
