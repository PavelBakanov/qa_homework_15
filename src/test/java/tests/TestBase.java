package tests;

import Config.WebConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

        RestAssured.baseURI = webConfig.getBaseUri();
        RestAssured.basePath = webConfig.getBasePath();
    }
}
