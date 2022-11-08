package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.GMIBankBaseUrl;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GetRequest08 extends GMIBankBaseUrl {

    /*
    http://www.gmibank.com/api/tp-customers/43703
          “firstName”: “Alda”,
          “lastName”: “Monahan”,
          “middleInitial”: “Nichelle Hermann Kohler”,
          “email”: “com.github.javafaker.Name@7c011174@gmail.com”,
          “mobilePhoneNumber”: “909-162-8114”,
          “city”: “St Louis”,
          “ssn”: “108-53-6655"
          1) MATCHERS CLASS
          2) JSON PATH
          3) De-Serialization
     */
    @Test
    public void get08(){

        // set the Url
        spec01.pathParams("first","tp-customers","second",43703);

        // set expected Data
        Map<String,Object> expectedData = new HashMap<String,Object>();

        expectedData.put("firstName","Alda");
        expectedData.put("lastName","Monahan");
        expectedData.put("middleInitial","Nichelle Hermann Kohler");
        expectedData.put("email","com.github.javafaker.Name@7c011174@gmail.com");
        expectedData.put("mobilePhoneNumber","909-162-8114");
        expectedData.put("city","St Louis");
        expectedData.put("ssn","108-53-6655");

        System.out.println("expectedData = " + expectedData);

        // send the request and get the response
        Response response =given().spec(spec01).headers("Authorization","Bearer " +generateToken()).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion - 1) MATCHERS CLASS
        response.then().assertThat().body("firstName",equalTo("Alda"),
                "lastName", equalTo("Monahan"),
                "middleInitial", equalTo("Nichelle Hermann Kohler"),
                "email",equalTo("com.github.javafaker.Name@7c011174@gmail.com"),
                "mobilePhoneNumber",equalTo("909-162-8114"),
                "city",equalTo("St Louis"),
                "ssn",equalTo("108-53-6655"));

        // Do Assertion - 2) JSonPath
        JsonPath json =response.jsonPath();

        assertEquals("Alda",json.getString("firstName"));
        assertEquals("Monahan",json.getString("lastName"));
        assertEquals("Nichelle Hermann Kohler",json.getString("middleInitial"));
        assertEquals("com.github.javafaker.Name@7c011174@gmail.com",json.getString("email"));
        assertEquals("909-162-8114",json.getString("mobilePhoneNumber"));
        assertEquals("St Louis",json.getString("city"));
        assertEquals("108-53-6655",json.getString("ssn"));

        // Do Assertion - 3) De-Serialization
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("firstName"), actualData.get("firstName"));
        assertEquals(expectedData.get("lastName"), actualData.get("lastName"));
        assertEquals(expectedData.get("middleInitial"), actualData.get("middleInitial"));
        assertEquals(expectedData.get("email"), actualData.get("email"));
        assertEquals(expectedData.get("mobilePhoneNumber"), actualData.get("mobilePhoneNumber"));
        assertEquals(expectedData.get("city"), actualData.get("city"));
        assertEquals(expectedData.get("ssn"), actualData.get("ssn"));


    }

}
