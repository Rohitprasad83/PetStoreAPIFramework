package api.test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;

public class TC_116 extends BaseTest{
    Store storePayload;
    Faker faker;
    @BeforeClass
    public void tc_116Setup(){
        faker = new Faker();
        storePayload = new Store();
        storePayload.setId(faker.number().randomDigitNotZero());
        storePayload.setPetId(-1);
        storePayload.setQuantity(faker.number().randomDigitNotZero());
        storePayload.setShipDate(Instant.now().toString());
        storePayload.setStatus("placed");
        storePayload.setComplete(faker.bool().bool());
    }
    @Test
    public void placeOrderWithInvalidPetId(){
        logger.info("place order with invalid pet id method started");
        Response res = StoreEndPoints.postCreateOrder(storePayload);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 400);
    }
}
