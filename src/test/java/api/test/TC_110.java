package api.test;

import api.endpoints.StoreEndPoints;
import api.payload.Store;
import com.github.javafaker.Faker;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Instant;

public class TC_110 extends BaseTest{
    Store storePayload;
    Faker faker;
    @BeforeClass
    public void tc_110Setup(){
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
    public void retrieveOrderDetails() {
        logger.info("retrieve order details method started");

        Response res = StoreEndPoints.getOrder(String.valueOf(storePayload.getId()));
        logResponse.logAll(res);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(res.getStatusCode(), 200);

        // Validate JSON schema
        try {
            File schemaFile = new File("src/test/resources/TC110OrderResponseJSON.json");
            res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
        } catch (Exception e) {
            logger.error("JSON schema file is not present: " + e.getMessage());
            softAssert.fail("JSON Schema validation failed due to missing schema file.");
        }

        softAssert.assertAll();
    }

}
