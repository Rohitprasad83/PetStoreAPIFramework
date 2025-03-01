package api.test;

import api.endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_118 extends BaseTest{

    @Test
    public void loginWithValidCredentials(){
        logger.info("login with valid credentials method started");
        String username = "Wellington.Keebler";
        String password = "HeoMa2CYlY6ov_7";
        Response res = UserEndPoints.loginUser(username, password);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(),200);
    }
}
