package com.digital.Adams;

import com.ojectModel.login;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * This class contains the test scripts for login api
 */

public class RestApiTestCases {

    /**
     * This project assumes that API endpoint needs access token/key as part of authentication
     */
    static final String APIKEY = "12345jdfhkjdsf456";

    @BeforeClass
    public void init() {

        /**
         * As part of initialization, the base URI and base path is defined here. This will be common and
         * will be used for all test cases
         */

        RestAssured.baseURI = "https://r8alxaspi1.execute-api.ap-southeast2.amazonaws.com";
        RestAssured.basePath = "/api/ezypay";


    }


    /**
     * This test validates Successful login Status code and response data
     */

    @Test
    public void login_Successful() {

        login login = new login();
        login.setUsername("test-user");
        login.setPassword("abc123");

        Response resp =
                given().header("Authorization", "Bearer " + APIKEY)
                        .contentType(ContentType.JSON).when()
                        .body(login)
                        .post("/login");

        /**
         * Validating status code to be 201
         */
        int statusCode = resp.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        /**
         * Validating response data
         */
        String username = resp.jsonPath().get("username");
        Assert.assertEquals(username, login.getUsername());
        String password = resp.jsonPath().get("password");
        Assert.assertEquals(password, login.getPassword());
    }


    /**
     * This test validates Unsuccessful login Status code and response data
     */

    @Test
    public void login_Unsuccessful() {

        login login = new login();
        login.setUsername("test-user");
        login.setPassword("xyz123");

        Response resp =
                given().header("Authorization", "Bearer " + APIKEY)
                        .contentType(ContentType.JSON).when()
                        .body(login)
                        .post("/login");

        /**
         * Validating status code to be 401 - Unauthorized
         */
        int statusCode = resp.getStatusCode();
        Assert.assertEquals(statusCode, 401);
        /**
         * Validating response data
         */
        String Code = resp.jsonPath().get("Code");
        Assert.assertEquals(Code, "UnauthorizedError");
        String Message = resp.jsonPath().get("Message");
        Assert.assertEquals(Message, "UnauthorizedError: invalid username or password");
    }
}
