package file;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class StaticJson {
    public static void main(String[] args) throws IOException {
        RestAssured.baseURI="http://216.10.245.166";
      String resp =  given().header("Content-Type","application/json")
                .body(grnerateStringFromResource("//Users//sonalpri//Downloads//My_First_Framework//src//main//resources//dataStaticJson.json"))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
      JsonPath js = ReusableMethod.rawToJson(resp);
      String id = js.get("ID");
        System.out.println("ID is : "+id);

    }

    public static String grnerateStringFromResource(String path) throws IOException {
      return  new String(Files.readAllBytes(Paths.get(path)));

    }
}
