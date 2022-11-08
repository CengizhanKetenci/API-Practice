package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequest03 {
    @Test
    public void test03(){

        // Set the Url
        String url = "https://reqres.in/api/users";

        // Set the expected Data

        // send the request and get the response
        Response response = given().when().get(url);
        response.prettyPeek();

        // Do Assertion
        response.then().assertThat().statusCode(200).statusLine("HTTP/1.1 200 OK").contentType(ContentType.JSON);

        /*
        Body Test - id'si 2 olan body'nin datalarının aşağıdaki gibi olup olmadığını test ediniz.
            {
            "id": 2,
            "email": "janet.weaver@reqres.in",
            "first_name": "Janet",
            "last_name": "Weaver",
            "avatar": "https://reqres.in/img/faces/2-image.jpg"
            }
         */
        response.then().assertThat().
                body("data[1].email", equalTo("janet.weaver@reqres.in"),
                "data[1].first_name", equalTo("Janet"),
                "data[1].last_name", equalTo("Weaver"),
                "data[1].avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
        /*
        Body Test - id'si 3 olan body'nin datalarının aşağıdaki gibi olup olmadığını test ediniz.
            {
            "id": 3,
            "email": "emma.wong@reqres.in",
            "first_name": "Emma",
            "last_name": "Wong",
            "avatar": "https://reqres.in/img/faces/3-image.jpg"
            }
         */
        response.then().assertThat().
                body("data[2].email", equalTo("emma.wong@reqres.in"),
                        "data[2].first_name", equalTo("Emma"),
                        "data[2].last_name", equalTo("Wong"),
                        "data[2].avatar", equalTo("https://reqres.in/img/faces/3-image.jpg"));
        /*
        Body Test - id'si 4 olan body'nin datalarının aşağıdaki gibi olup olmadığını test ediniz.
            {
            "id": 4,
            "email": "eve.holt@reqres.in",
            "first_name": "Eve",
            "last_name": "Holt",
            "avatar": "https://reqres.in/img/faces/4-image.jpg"
            }
         */
        response.then().assertThat().
                body("data[3].email", equalTo("eve.holt@reqres.in"),
                        "data[3].first_name", equalTo("Eve"),
                        "data[3].last_name", equalTo("Holt"),
                        "data[3].avatar", equalTo("https://reqres.in/img/faces/4-image.jpg"));
        /*
        Body Test - id'si 5 olan body'nin datalarının aşağıdaki gibi olup olmadığını test ediniz.
            {
            "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
            }
         */
        response.then().assertThat().
                body("data[4].email", equalTo("charles.morris@reqres.in"),
                        "data[4].first_name", equalTo("Charles"),
                        "data[4].last_name", equalTo("Morris"),
                        "data[4].avatar", equalTo("https://reqres.in/img/faces/5-image.jpg"));
        /*
        Body Test - id'si 6 olan body'nin datalarının aşağıdaki gibi olup olmadığını test ediniz.
            {
            "id": 6,
            "email": "tracey.ramos@reqres.in",
            "first_name": "Tracey",
            "last_name": "Ramos",
            "avatar": "https://reqres.in/img/faces/6-image.jpg"
            }
         */
        response.then().assertThat().
                body("data[5].email", equalTo("tracey.ramos@reqres.in"),
                        "data[5].first_name", equalTo("Tracey"),
                        "data[5].last_name", equalTo("Ramos"),
                        "data[5].avatar", equalTo("https://reqres.in/img/faces/6-image.jpg"));








    }

}
