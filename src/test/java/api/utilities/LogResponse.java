package api.utilities;

import io.restassured.response.Response;
import org.apache.logging.log4j.*;

public class LogResponse {
    Logger logger;
    private static ThreadLocal<Response> lastResponse = new ThreadLocal<>();

    public LogResponse(Logger logger){
        this.logger = logger;
    }
    public void logBody(Response res){
        logger.info("Response Body: {}", res.getBody().asString());
    }
    public void logHeaders(Response res){
        logger.info("Response Headers: {}", res.getHeaders().toString());
    }
    public void logStatus(Response res) {
        logger.info("Response Status: {}", res.getStatusLine());
    }
    public void logAll(Response res){
        logBody(res);
        logStatus(res);
        logHeaders(res);
        lastResponse.set(res);
    }

    public static Response getLastResponse() {
        return lastResponse.get();
    }

    public static void clearLastResponse() {
        lastResponse.remove();
    }
}
