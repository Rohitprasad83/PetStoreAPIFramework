package api.test;

import api.endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_120 extends BaseTest{

    @Test(groups = "healthcheck")
    public void LoginWithValidCredentials(){
        logger.info("staring Login with valid credentials method started");
        String username = "Wellington.Keebler";
        String password = "HeoMa2CYlY6ov_7";
        Response res = UserEndPoints.loginUser(username, password);
        logResponse.logAll(res);
        Assert.assertEquals(res.getStatusCode(), 503);
    }
}
