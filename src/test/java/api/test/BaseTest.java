package api.test;

import api.utilities.LogResponse;
import org.apache.logging.log4j.*;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public static final Logger logger = LogManager.getLogger(BaseTest.class);
    public static LogResponse logResponse;
    @BeforeClass
    public void setup() {
        logger.info("Starting test execution");
        logResponse = new LogResponse(logger);
    }
}
