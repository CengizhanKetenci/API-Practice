package day04;

import io.restassured.response.Response;
import org.junit.Test;
import pojos.Accounts;
import pojos.Country;
import pojos.Customer;
import pojos.User;
import utilities.GMIBankBaseURL;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest09 extends GMIBankBaseURL {
    /*
    http://www.gmibank.com/api/tp-customers/110452
    Yukarıdaki endpoint'e gidin ve response ettiğiniz data aşağıdaki (expected) gibi mi assert edin.

    {
    "id": 110452,
    "firstName": "Jasmine",
    "lastName": "Stehr",
    "middleInitial": "V",
    "email": "marni.zboncak@yahoo.com",
    "mobilePhoneNumber": "463-609-2097",
    "phoneNumber": "1-112-497-0270",
    "zipCode": "16525",
    "address": "14387 Al Ridge5343 Bert Burgs",
    "city": "Waltermouth",
    "ssn": "761-59-2911",
    "createDate": "2021-11-28T21:00:00Z",
    "zelleEnrolled": false,
    "country": {
        "id": 3,
        "name": "USA",
        "states": null
    },
    "state": "California",
    "user": {
        "id": 110016,
        "login": "leopoldo.reinger",
        "firstName": "Jasmine",
        "lastName": "Stehr",
        "email": "marni.zboncak@yahoo.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
     },
    "accounts": [
    ]
}
    */
    @Test
    public void test09(){

        // Set the Url
        spec01.pathParams("first","tp-customers","second",110452);

        Accounts[] accounts; // bu kullanıcının herhangi bir accounts bilgisi olmadığından AccountsPojo oluşturmayacağız.

        // Set the expected Data -- 1) en iç innerpojo olan "user"'dan başlıyoruz.
        User user = new User(110016,"leopoldo.reinger","Jasmine","Stehr",
                "marni.zboncak@yahoo.com",true,"en",null,null);
        System.out.println("user = " + user);

        // Set the expected Data -- 2) diğer innerpojo olan "country"  ile devam ediyoruz.
        Country country = new Country(3,"USA",null);
        System.out.println("country = " + country);

        // Set the expected Data -- 3) outherpojo olan "customer" ile tamamlıyoruz. OutherPojo aynı zamanda expectedData'mızdır.
        Customer expectedData = new Customer(110452,"Jasmine","Stehr","V","marni.zboncak@yahoo.com",
                "463-609-2097","1-112-497-0270","16525","14387 Al Ridge5343 Bert Burgs",
                "Waltermouth","761-59-2911","2021-11-28T21:00:00Z",false,country,"California",user);
        System.out.println("expectedData = " + expectedData);

        // send the request and get the response
        Response response = given().spec(spec01).headers("Authorization","Bearer "+generateToken()).when().get("/{first}/{second}");
        response.prettyPrint();

        Customer actualData = response.as(Customer.class);
        System.out.println("actualData = " + actualData);

        // Do Assertion - Pojo ile

        assertEquals(expectedData.getId(), actualData.getId());

        assertEquals(user.getLogin(), actualData.getUser().getLogin());

        assertEquals(country.getName(), actualData.getCountry().getName());

        // Do Assertion - ObjectMapper ile
        Customer actualData2=ObjectMapperUtils.convertJsonToJava(response.asString(),Customer.class);
        System.out.println("actualData2 = " + actualData2);

        assertEquals(expectedData.getId(), actualData2.getId());
        assertEquals(user.getLogin(), actualData2.getUser().getLogin());
        assertEquals(country.getName(), actualData2.getCountry().getName());


    }
}
