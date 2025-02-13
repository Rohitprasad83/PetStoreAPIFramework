package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {
    Faker faker;
    User userPayload;
    @BeforeClass
    public void setupData(){
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

    @Test(priority = 1)
    public void testCreateUser(){
        logger.info("test create user name started");
        logger.info("username used for creating user is {}", userPayload.getUsername());
        Response res = UserEndPoints.createUser(userPayload);
        logResponse.logBody(res);
        logResponse.logHeaders(res);
        logResponse.logStatus(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(priority = 2)
    public void testGetUser(){
        logger.info("test get user name started");
        Response res = UserEndPoints.getUser(userPayload.getUsername());

        logResponse.logBody(res);
        logResponse.logHeaders(res);
        logResponse.logStatus(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(priority = 3)
    public void testUpdateUser(){
        logger.info("test update user name started");
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        Response res = UserEndPoints.updateUser(userPayload,userPayload.getUsername());

        logResponse.logBody(res);
        logResponse.logHeaders(res);
        logResponse.logStatus(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(priority = 4)
    public void testUserPhoneUpdated(){
        logger.info("test user phone updated started");
        Response res = UserEndPoints.getUser(userPayload.getUsername());

        logResponse.logBody(res);
        logResponse.logHeaders(res);
        logResponse.logStatus(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(priority = 5)
    public void testDeleteUser(){
        logger.info("test delete user started");
        Response res = UserEndPoints.deleteUser(userPayload.getUsername());

        logResponse.logBody(res);
        logResponse.logHeaders(res);
        logResponse.logStatus(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 6)
    public void testCreateUserWithArray(){
        logger.info("test create user with array started");

        Response res = UserEndPoints.createUserWithArray(userPayload);
        logResponse.logBody(res);
        logResponse.logHeaders(res);
        logResponse.logStatus(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(priority = 7)
    public void testDeleteUser2(){
        logger.info("test delete user started");
        Response res = UserEndPoints.deleteUser(userPayload.getUsername());

        logResponse.logBody(res);
        logResponse.logHeaders(res);
        logResponse.logStatus(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 8)
    public void testLoginUser(){
        logger.info("login user method started");
        String username = "Wellington.Keebler";
        String password = "HeoMa2CYlY6ov_7";
        Response res = UserEndPoints.loginUser(username,password);
        logResponse.logBody(res);
        logResponse.logHeaders(res);
        logResponse.logStatus(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test(priority = 8)
    public void testLogoutUser(){
        logger.info("logout user method started");
        Response res = UserEndPoints.logoutUser();
        logResponse.logBody(res);
        logResponse.logHeaders(res);
        logResponse.logStatus(res);
        Assert.assertEquals(res.getStatusCode(), 200);
    }
}
