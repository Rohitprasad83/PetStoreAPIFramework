package api.test;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_114 extends BaseTest{
    Pet petPayload;
    @BeforeClass
    public void tc_114Setup(){
        petPayload = new Pet();
    }

    @Test
    public void createAPetWithIncompleteBody(){
        logger.info("create a pet with incomplete body started");

        Response res = PetEndPoints.postCreatePet(petPayload);
        logResponse.logAll(res);

        Assert.assertEquals(res.getStatusCode(), 400);
    }
}

