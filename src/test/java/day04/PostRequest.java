package day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.CountryPost;
import utilities.GMIBankBaseURL;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequest extends GMIBankBaseURL {
    /*
    https://www.gmibank.com/api/tp-countries adresine post işlemi yaparak yeni bir ülke ekleyin
    */

    @Test
    public void test10(){

        // Set the Url
        spec01.pathParam("first", "tp-countries");

        // Set the expectedData - CountryPost pojo bizim tek (outher) pojo'muz ve aynı zamanda expectedData'mızdır
        CountryPost expectedData = new CountryPost("Batch81");
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given().contentType(ContentType.JSON).headers("Authorization", "Bearer " + generateToken())
                .spec(spec01).when().body(expectedData).post("/{first}");

        response.prettyPrint();

        //Do Assertion
        // expectedData'mız CountryPost data tipinde olduğundan assert yapabilmek için actualData'mız olan response'umuzuda CountryPost data tipine ceviriyoruz.
        CountryPost actualData = response.as(CountryPost.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getName(), actualData.getName());
    }
}