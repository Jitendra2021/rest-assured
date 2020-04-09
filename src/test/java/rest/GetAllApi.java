package rest;

import utils.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class GetAllApi {

    String path;
    @BeforeClass
    public void setup() {

        RestAssured.baseURI = Config.baseUrl;
        path = "v1/employees";
    }

    @Test
    public void getEmployees (){
        Response res = null;

        try{
            res = RestAssured.given()
                    .when()
                    .get(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(res.getBody().prettyPrint());
        System.out.println("status code = " + res.getStatusCode());
        System.out.println("time passed = " + res.getTimeIn(TimeUnit.MILLISECONDS));
        }

}
