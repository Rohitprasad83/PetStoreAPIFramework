package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC_103 extends BaseTest{
    Faker faker;
    User userPayload;
    @BeforeClass
    public void tcSetup(){
        logger.info("-----------------------------------");
        faker = new Faker();
        logger.info("User Payload initialization has been started");
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUserStatus(faker.idNumber().hashCode());
        logger.info("User Payload initialization is completed");
    }

    @Test
    public void registerNewUser(){
        logger.info("register new user method started");

        Response res = UserEndPoints.createUser(userPayload);
        logResponse.logAll(res);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"registerNewUser"})
    public void checkNewUserIsCreated(){
        logger.info("check new user method started");

        Response res = UserEndPoints.getUser(userPayload.getUsername());

        logResponse.logAll(res);
        SoftAssert softAssert = new SoftAssert();
        JsonPath resJsonPath = res.jsonPath();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getString("username"), userPayload.getUsername());
        softAssert.assertEquals(resJsonPath.getString("firstName"), userPayload.getFirstName());
        softAssert.assertEquals(resJsonPath.getString("lastName"), userPayload.getLastName());
        softAssert.assertEquals(resJsonPath.getString("email"), userPayload.getEmail());
        softAssert.assertEquals(resJsonPath.getString("password"), userPayload.getPassword());
        softAssert.assertEquals(resJsonPath.getString("password"), userPayload.getPassword());
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkNewUserIsCreated"})
    public void updateUserDetails(){
        logger.info("update user details method started");

        userPayload.setPhone(faker.phoneNumber().cellPhone());
        Response res = UserEndPoints.updateUser(userPayload, userPayload.getUsername());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertAll();
    }


    @Test(dependsOnMethods = {"updateUserDetails"})
    public void checkUserDetailsIsUpdated(){
        logger.info("check user details is updated method started");

        Response res = UserEndPoints.getUser(userPayload.getUsername());

        logResponse.logAll(res);
        SoftAssert softAssert = new SoftAssert();
        JsonPath resJsonPath = res.jsonPath();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertEquals(resJsonPath.getString("username"), userPayload.getUsername());
        softAssert.assertEquals(resJsonPath.getString("firstName"), userPayload.getFirstName());
        softAssert.assertEquals(resJsonPath.getString("lastName"), userPayload.getLastName());
        softAssert.assertEquals(resJsonPath.getString("email"), userPayload.getEmail());
        softAssert.assertEquals(resJsonPath.getString("password"), userPayload.getPassword());
        softAssert.assertEquals(resJsonPath.getString("password"), userPayload.getPassword());
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"checkUserDetailsIsUpdated"})
    public void deleteCreatedUser(){
        logger.info("delete user method started");
        Response res = UserEndPoints.deleteUser(userPayload.getUsername());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(res.getStatusCode(), 200);
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = {"deleteCreatedUser"})
    public void checkUserIsDeleted(){
        logger.info("check user is deleted method started");

        Response res = UserEndPoints.getUser(userPayload.getUsername());

        SoftAssert softAssert = new SoftAssert();
        JsonPath resJsonPath = res.jsonPath();
        softAssert.assertEquals(res.getStatusCode(), 404);
        softAssert.assertEquals(resJsonPath.getString("message"), "User not found");
        softAssert.assertAll();
    }
}
