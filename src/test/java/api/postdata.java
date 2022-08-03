package api;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.APIUtils;

public class postdata {
    @BeforeClass
    public void startUp() throws IOException {
        APIUtils.readAPIConfigs();
        APIUtils.setBaseURL();
    }

    @Feature("API Test")
    @Story("API-001: api test for create user functionality")
    @Description("Validate new user is created when create user api is called")
    @Test(description = "Validate new user is created when create user api is called")
    public void test_user_created_successfully_using_post_api_method1_body_from_json_file() throws Exception {
        String end_point = APIUtils.api_config.getProperty("create_user_end_point");
        String body = Files.readString(Path.of(System.getProperty("user.dir") + "/src/test/java/resources/api_test_data_create_userJSONBody.json"));


         String baseURI = "base_url";

        Response response= given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(body).
                when().
                post(baseURI+"/users");

               response.then()
                .statusCode(201).log().all();

    }
}
