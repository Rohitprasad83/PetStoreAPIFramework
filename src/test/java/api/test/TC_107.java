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

import java.util.ArrayList;
import java.util.List;

public class TC_107 extends BaseTest{
    Pet petPayload;
    Faker faker;
    @BeforeClass
    public void tc_107Setup(){
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

    @Test
    public void updateAPetWithoutAdminRights(){
        logger.info("update a pet without admin rights started");

        Response res = PetEndPoints.updatePet(petPayload);
        logResponse.logAll(res);

        Assert.assertEquals(res.getStatusCode(), 401);
    }
}
