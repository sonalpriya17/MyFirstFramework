package demo;

import file.Payload;
import file.ReusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class basics {
    public static void main(String[] args) {
        //validate if addPlace Api is working properly
        //given- all inputs
        //when -Submit Api - Post and endpoint
        //then - validate response
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.addPlace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String placeId = js.getString("place_id");
        System.out.println(placeId);

        // Update an existing place
        String newAddress = "Sonal2,70 Summer walk, USA";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\" " + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
        //get place
        String getPlace = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .header("Content-Type", "application/json")
                .when().get("maps/api/place/get/json")
                .then().log().all().statusCode(200).extract().response().asString();

        System.out.println("get place is : " + getPlace);
        JsonPath js1 = ReusableMethod.rawToJson(getPlace);
        String actualAddress = js1.getString("address");
        System.out.println("actualAddress : " + actualAddress);
        Assert.assertEquals(actualAddress.trim(), newAddress, "Address NOT updated");

    }


}
