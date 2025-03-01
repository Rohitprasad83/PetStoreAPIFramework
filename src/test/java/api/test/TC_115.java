package api.test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;

public class TC_115 extends BaseTest{
    Store storePayload;
    Faker faker;
    @BeforeClass
    public void tc_115Setup(){
        faker = new Faker();
        storePayload = new Store();
        storePayload.setId(faker.number().randomDigitNotZero());
        storePayload.setPetId(faker.number().randomDigitNotZero());
        storePayload.setQuantity(-5);
        storePayload.setShipDate(Instant.now().toString());
        storePayload.setStatus("placed");
        storePayload.setComplete(faker.bool().bool());
    }

    @Test
    public void placeOrderWithNegativeQuantity(){
        logger.info("place order with negative quantity started");

        Response res = StoreEndPoints.postCreateOrder(storePayload);
        logResponse.logAll(res);

        Assert.assertEquals(res.getStatusCode(), 400);
    }
}
