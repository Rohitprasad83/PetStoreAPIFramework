package api.test;

import api.endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_117 extends BaseTest{

    @Test
    public void loginWithSQLInjection(){
        logger.info("login with sql injection method started");
        String username = "' OR '1'='1' -- ";
        String password = "xyz";
        Response res = UserEndPoints.loginUser(username, password);
        logResponse.logAll(res);
        Assert.assertTrue(res.getStatusCode() == 400 || res.getStatusCode() == 401);
    }
}
