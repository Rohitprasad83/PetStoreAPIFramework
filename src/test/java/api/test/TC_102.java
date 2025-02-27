package api.test;

import api.endpoints.UserEndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_102 extends BaseTest{

    @Test
    public void LoginWithInvalidCredentials(){
        logger.info("staring Login with invalid credentials method started");
        String username = "Wellington.Keebler";
        String password = "invalidPassword";
        Response res = UserEndPoints.loginUser(username, password);
        logResponse.logAll(res);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 400);
        softAssert.assertAll();
    }

    @Test
    public void LoginWithValidCredentials(){
        logger.info("staring Login with valid credentials method started");
        String username = "Wellington.Keebler";
        String password = "HeoMa2CYlY6ov_7";
        Response res = UserEndPoints.loginUser(username, password);
        logResponse.logAll(res);
        JsonPath resJsonPath = res.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getInt("code"), 200);
        softAssert.assertEquals(resJsonPath.getString("type"), "unknown");
        softAssert.assertTrue(resJsonPath.getString("message").contains("logged in user session"));
        softAssert.assertAll();
    }
}
