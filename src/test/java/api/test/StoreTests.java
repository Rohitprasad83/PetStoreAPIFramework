package api.test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Instant;

public class StoreTests extends BaseTest {
    Store storePayload;
    Faker faker;
    @BeforeClass()
    public void initializeStorePayload(){
        faker = new Faker();
        storePayload = new Store();
        storePayload.setId(faker.number().randomDigitNotZero());
        storePayload.setId(faker.number().randomDigitNotZero());
        storePayload.setQuantity(faker.number().randomDigitNotZero());
        storePayload.setShipDate(Instant.now().toString());
        storePayload.setStatus("placed");
        storePayload.setComplete(faker.bool().bool());
    }
    @Test
    public void testGetInventory(){
        logger.info("test get inventory method started");
        Response res = StoreEndPoints.getInventory();
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test
    public void testPostOrder(){
        logger.info("test post order method started");
        Response res = StoreEndPoints.postCreateOrder(storePayload);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(dependsOnMethods = {"testPostOrder"})
    public void testGetOrder(){
        logger.info("test get order method started");
        String orderId = String.valueOf(storePayload.getId());
        Response res = StoreEndPoints.getOrder(orderId);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(dependsOnMethods = {"testGetOrder"})
    public void testDeleteOrder(){
        logger.info("test delete order method started");
        String orderId = String.valueOf(storePayload.getId());
        Response res = StoreEndPoints.deleteOrder(orderId);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
