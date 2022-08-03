package utilities;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;


public class APIUtils {
    public static Properties api_config;
    public static RequestSpecification request;
    public static Response response;
    
      
    public static void readAPIConfigs() throws IOException {
        api_config = new Properties();
        FileInputStream objfile = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/api_config.properties");
        api_config.load(objfile);
    }
    public static void setBaseURL() throws IOException {
        RestAssured.baseURI =api_config.getProperty("base_url");
        
    }
    public static Response getRequest(String endpoint){
        request.header("Content-Type", "application/json");
        response =request.get(endpoint);
        return response;
    }
    public static Response postRequest(String endpoint, String formdata){
        request.header("Content-Type", "application/json");
        request.body(formdata);
        response =request.post(endpoint);
        return response;
    }


}
