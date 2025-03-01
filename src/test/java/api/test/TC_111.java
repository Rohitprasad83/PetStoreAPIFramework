package api.test;

import api.endpoints.PetEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_111 extends BaseTest{

    @Test
    public void deleteANonExistentPet(){
        logger.info("test delete a non-existent pet method started");
        Response res = PetEndPoints.deletePet(-1);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 404);
    }
}
