package demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import pojo.addPlace.AddPlace;
import pojo.addPlace.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class SerialiseTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddPlace p = new AddPlace();
        p.setAccuracy(50);
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress("29, side layout, cohen 09");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");
        List<String> typesList = new ArrayList<>();
        typesList.add("shoe park");
        typesList.add("shop");
        p.setTypes(typesList);
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        p.setLocation(location);
        Response response = given().log().all().queryParams("key", "qaclick123")
                .header("Content-Type","application/json")
                .body(p)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();
       String responseString = response.asString();
        System.out.println(responseString);

    }

}
