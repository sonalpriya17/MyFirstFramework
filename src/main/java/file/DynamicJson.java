package file;

import com.google.common.net.UrlEscapers;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String inputString = "/Library/Addbook.php";
        String encodedString = UrlEscapers.urlFragmentEscaper().escape(inputString);

        String response = given().log().all().header("Content-Type", "application/json")
                .body(Payload.addBook(isbn, aisle))
                .when().post(encodedString)
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = ReusableMethod.rawToJson(response);
        String id = js.get("ID");
        System.out.println("ID : " + id);
 /*       JsonPath responseFromJsonPath = ReusableMethod.rawToJson(response);
      String idFromJsonPath= (String)responseFromJsonPath.get("ID");
        System.out.println("ID :"+ idFromJsonPath);*/

    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        return new Object[][]{{"son1", "pri1"}, {"son2", "pri2"}, {"son3", "pri3"}};
    }
}

