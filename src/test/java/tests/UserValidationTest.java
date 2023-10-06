package tests;

import base.BaseTest;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasItem;

public class UserValidationTest extends BaseTest {

    @Test(description = "Verify User ID exist in api response")
    public void VerifyUserIDInResponse() {
        RequestSpecification ReqSpec = ReqSpecBuilder
                .addParam("TestSPecific","Value")
                .build();
        ReqSpec.log().all();
//path
        given().spec(ReqSpec)
                .get("users?page=2")
                .then()
                .body("data.id", hasItem(8))
                .log().all();

    }

    @Test(description = "Verify User Schema is returned from API is valid")
    public void VerifyUserSchema() {
        RequestSpecification ReqSpec = ReqSpecBuilder.build();
        ReqSpec.log().all();

        ResponseSpecification responseSpecification = responseSpecBuilder
                                                        .expectBody("data.id", hasItem(8))
                                                        .build();
        given()
                .spec(ReqSpec)
                .get("users?page=2")
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("JsonSchemas/userSchema.json"))
                .log().all();

    }
}
