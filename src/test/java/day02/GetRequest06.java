package day02;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class GetRequest06 extends Authentication {

    @Test
    public void test06(){

        // Set the Url
        String url = "https://www.gmibank.com/api/tp-customers/114351";

        // Set the expected Data

        // Send the request and get the response
        Response response = given().headers("Authorization","Bearer "+generateToken()).when().get(url);
        response.prettyPrint();

        // Do Assertion

        // Matcher Class ile Doğrula
        response.then().assertThat().body(
                "firstName",equalTo("Della"),
                "lastName",equalTo("Heaney"),
                "email",equalTo("ricardo.larkin@yahoo.com"),
                "mobilePhoneNumber",equalTo("123-456-7893"),
                "country.name",equalTo("USA"),
                "state",equalTo("New York43"),
                "accounts[0].balance",equalTo(11190),
                "accounts[0].accountType",equalTo("CHECKING"),
                "accounts[1].balance",equalTo(69700),
                "accounts[1].accountType",equalTo("CREDIT_CARD"));

        // JsonPath ile Doğrula
        JsonPath json =response.jsonPath();

        assertEquals("Della",json.getString("firstName"));
        assertEquals("Heaney",json.getString("lastName"));
        assertEquals("ricardo.larkin@yahoo.com",json.getString("email"));
        assertEquals("123-456-7893",json.getString("mobilePhoneNumber"));
        assertEquals("USA",json.getString("country.name"));
        assertEquals("New York43",json.getString("state"));
        assertEquals(11190,json.getInt("accounts[0].balance"));
        assertEquals("CHECKING",json.getString("accounts[0].accountType"));
        assertEquals(69700,json.getInt("accounts[1].balance"));
        assertEquals("CREDIT_CARD",json.getString("accounts[1].accountType"));
    }
}
