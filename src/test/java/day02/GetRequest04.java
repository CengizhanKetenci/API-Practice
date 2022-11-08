package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest04 {

    /*
    http://dummy.restapiexample.com/api/v1/employees  url’ine
    GET request’i yolladigimda gelen response’un
    1- status kodunun 200
    2- content type’inin “application/json”
    3- employees sayisinin 24
    4- employee’lerden birinin “Ashton Cox”
    5- gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
     */
    @Test
    public void test04(){

        // Set the url
        String url = "http://dummy.restapiexample.com/api/v1/employees";

        // set the expected Data

        // Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

        // Do Assertion
        // 1- status kodunun 200 ve content type’inin “application/json” olduğunu doğrula
        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);

        // 3-   employees sayisinin 24 olduğunu, employee’lerden birinin “Ashton Cox” olduğunu ve
        //      gelen yaslar icinde 21, 61, ve 23 degerlerinin her üçününde oldugunu test edin
        response.then().assertThat().body("data", hasSize(24),
                "data.employee_name",hasItem("Ashton Cox"),
                "data.employee_age",hasItems(21,61,23));

    }
}
