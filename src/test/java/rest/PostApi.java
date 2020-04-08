package rest;

import utils.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PostApi {
    String path;
    @BeforeClass
    public void setup() {

        RestAssured.baseURI = Config.baseUrl;
        path = "v1/create";
    }

    @Test
    public void getEmployees (){
        Response res = null;
        String payload = "{\n" +
                "  \"name\": \"test jit\",\n" +
                "  \"salary\": 21345,\n" +
                "  \"age\": 27\n" +
                "}";

        try{
            res = RestAssured.given()
                    .body(payload)
                    .when()
                    .post(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(res.getBody().prettyPrint());
        System.out.println("status code = " + res.getStatusCode());
        System.out.println("time passed = " + res.getTimeIn(TimeUnit.MILLISECONDS));
    }

}
