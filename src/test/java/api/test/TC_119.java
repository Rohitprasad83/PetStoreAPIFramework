package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_119 extends BaseTest{
    Faker faker;
    User userPayload;
    String existingUsername = "Wellington.Keebler";
    @BeforeClass
    public void tc_119Setup(){
        faker = new Faker();
        logger.info("User Payload initialization has been started");
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(existingUsername);
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        userPayload.setUserStatus(faker.idNumber().hashCode());
        logger.info("User Payload initialization is completed");
    }

    @Test
    public void createNewUserWithExistingUsername(){
        logger.info("create new user with existing username method started");

        Response res = UserEndPoints.createUser(userPayload);

        logResponse.logAll(res);

        Assert.assertEquals(res.getStatusCode(), 400);
    }

}
