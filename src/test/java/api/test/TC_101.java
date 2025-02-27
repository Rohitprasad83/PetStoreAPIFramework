package api.test;

import api.endpoints.PetEndPoints;
import api.endpoints.StoreEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Store;
import api.payload.Tag;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TC_101 extends BaseTest{
    Pet petPayload;
    Faker faker;
    Store storePayload;
    @BeforeClass
    public void payloadSetups(){
        faker = new Faker();
        petPayload = new Pet();
        
//      initializing pet payload

        petPayload.setId(faker.number().randomDigitNotZero());
        Category category = new Category();
        category.setId(faker.number().randomDigitNotZero());
        category.setName(faker.dog().breed());
        petPayload.setCategory(category);
        petPayload.setName(faker.dog().name());
        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.lorem().sentence());
        petPayload.setPhotoUrls(photoUrls);
        List<Tag> tags = new ArrayList<>();
        Tag tag = new Tag();
        tag.setId(faker.number().randomDigitNotZero());
        tag.setName(faker.name().fullName());
        tags.add(tag);
        petPayload.setStatus("available");

//        --- initializing order payload
        storePayload = new Store();
        storePayload.setId(faker.number().randomDigitNotZero());
        storePayload.setPetId(petPayload.getId());
        storePayload.setQuantity(faker.number().randomDigitNotZero());
        storePayload.setShipDate(Instant.now().toString());
        storePayload.setStatus("placed");
        storePayload.setComplete(faker.bool().bool());
    }

    @Test(priority = 1)
    public void testCreatePet(){
        logger.info("test post create pet method started");
        Response res = PetEndPoints.postCreatePet(petPayload);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getString("status"), "available");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testGetPet(){
        logger.info("test get pet method started");
        Response res = PetEndPoints.getPet(petPayload.getId());
        JsonPath resJsonPath = res.jsonPath();
        logResponse.logAll(res);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getInt("id"), petPayload.getId());
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void testPostOrder(){
        logger.info("test post order method started");
        Response res = StoreEndPoints.postCreateOrder(storePayload);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getInt("id"), storePayload.getId());
        softAssert.assertEquals(resJsonPath.getInt("petId"), petPayload.getId());
        softAssert.assertEquals(resJsonPath.getString("status"), "placed");
        softAssert.assertAll();
    }
    @Test(priority = 4)
    public void testGetOrder(){
        logger.info("test get order method started");
        String orderId = String.valueOf(storePayload.getId());
        Response res = StoreEndPoints.getOrder(orderId);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getInt("id"), storePayload.getId());
        softAssert.assertEquals(resJsonPath.getInt("petId"), petPayload.getId());
        softAssert.assertEquals(resJsonPath.getString("status"), "placed");
        softAssert.assertAll();
    }
    @Test(priority = 5)
    public void testUpdatePet(){
        logger.info("test update pet method started");
        petPayload.setStatus("sold");
        Response res = PetEndPoints.updatePet(petPayload);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getString("status"), "sold");
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void testGetPetAfterSold(){
        logger.info("test get pet method after sold started");
        Response res = PetEndPoints.getPet(petPayload.getId());
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getString("status"), "sold");
        softAssert.assertAll();
    }
}
