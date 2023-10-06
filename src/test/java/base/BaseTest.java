package base;

import Constants.APIHttpStatus;
import Constants.Environments;
import configuration.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

import static Constants.Environments.URL_TO_USE;

public class BaseTest {
    public RequestSpecBuilder ReqSpecBuilder;
    public ResponseSpecBuilder responseSpecBuilder;
    protected ConfigurationManager config;
    protected Properties prop;
    @BeforeSuite
    public void setup() {
        RestAssured.filters(new AllureRestAssured());
        config = new ConfigurationManager();
//        prop = config.setupConfigs();
        initializeRequestSpecBuilder();
        initializeResponseSpecBuilder();
        RestAssured.baseURI = URL_TO_USE;
    }

    public void initializeRequestSpecBuilder() {
        ReqSpecBuilder = new RequestSpecBuilder();
        ReqSpecBuilder.addParam("Param1", "testingValue");
        ReqSpecBuilder.addHeader("Content-type", ContentType.JSON.toString());
        ReqSpecBuilder.addHeader("auth", "bearer 13r533452525");
        ReqSpecBuilder.addCookie("my custom cookie", "123456");
        ReqSpecBuilder.addCookie("my custom cookie");
        ReqSpecBuilder.setBaseUri(URL_TO_USE);

        // we can initialize auth only once here, and we can re-use it.
    }

    public void initializeResponseSpecBuilder() {
        responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectContentType(ContentType.JSON);
    }

    // we can add multiple builder of based on test own needs

}
