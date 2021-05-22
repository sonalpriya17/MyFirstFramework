import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SlackIncomingWebhook {
    @Test
    public static void slackWebhookPostRequest(String error){

        RestAssured.baseURI="https://hooks.slack.com/services/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";
        given().log().all().header("Content-Type","application/json")
                .body("{\n" +
                        "    \"text\": \"Hello, world Sonal\"\n" +
                        "}").when()
                .post().then().statusCode(200);
    }
}
