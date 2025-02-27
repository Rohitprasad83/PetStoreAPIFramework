package api.test;

import api.endpoints.PetEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class PetTests extends BaseTest {
    Pet petPayload;
    Faker faker;

    @BeforeClass()
    public void initializePetPayload(){
        faker = new Faker();
        petPayload = new Pet();
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
    }

    @Test(priority = 1)
    public void testCreatePet(){
        logger.info("test post create pet method started");
        Response res = PetEndPoints.postCreatePet(petPayload);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);

    }

    @Test(priority = 2)
    public void testGetPet(){
        logger.info("test get pet method started");
        Response res = PetEndPoints.getPet(petPayload.getId());
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testUpdatePet(){
        logger.info("test update pet method started");
        petPayload.setStatus("pending");
        Response res = PetEndPoints.updatePet(petPayload);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);

    }
    @Test(priority = 4)
    public void testUploadImageToPet(){
        logger.info("test upload image to pet method started");
        File file = new File("src/test/resources/GXFG84ybEAEfak-.jpg");
        Response res = PetEndPoints.uploadImageToPet(petPayload.getId(), file);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(priority = 6)
    public void testDeletePet(){
        logger.info("test delete pet method started");
        Response res = PetEndPoints.deletePet(petPayload.getId());
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 7)
    public void testGetPetByStatus(){
        logger.info("test get pet by status method started");
        Response res = PetEndPoints.getPetByStatus("available");
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }


}
