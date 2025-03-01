package api.test;

import api.endpoints.StoreEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_112 extends BaseTest{

    @Test(invocationCount = 100, threadPoolSize = 10)
    public void retrieveOrderDetails_PerformanceTest(){
        logger.info("retrieve order details performance test started");

        Response res = StoreEndPoints.getOrder("1");
        logResponse.logAll(res);
        Assert.assertTrue(res.time()<2000, "Response time exceeded 2000ms");
    }
}
