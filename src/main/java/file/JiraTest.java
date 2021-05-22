package file;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JiraTest {
    public static String getLoginSessionId(){
        RestAssured.baseURI = "http://localhost:8080";
      String createSessionResponse =  given().log().all().header("Content-Type","application/json").body(Payload.createSession())
                .when().post("/rest/auth/1/session")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
      JsonPath jsonPath =ReusableMethod.rawToJson(createSessionResponse);
    String sessionId=  jsonPath.get("session.value");
        System.out.println("sessionId : "+sessionId);
        return sessionId;
    }

    public static void main(String[] args){
        baseURI = "http://localhost:8080";
        String expectedComment ="Hello how are you today";
        SessionFilter session = new SessionFilter();
        // Get session ID
        String sessionResponse =  given().log().all().header("Content-Type","application/json").body(Payload.createSession())
                .filter(session)
                .when().post("/rest/auth/1/session")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        // Add Commnet
       String responseComment= given().log().all().header("Content-Type","application/json")
                .pathParams("key","10005")
                .body("{\n" +
                        "  \"visibility\": {\n" +
                        "    \"type\": \"role\",\n" +
                        "    \"value\": \"Administrators\"\n" +
                        "  },\n" +
                        "  \"body\": \""+expectedComment+"\"\n" +
                        "}")
                .filter(session)
                .when().post("/rest/api/2/issue/{key}/comment")
                .then().log().all().assertThat().statusCode(201).extract().response().asString();
       JsonPath js = new JsonPath(responseComment);
       String commentId= js.get("id");

        //Add attachement
        given().log().all().header("X-Atlassian-Token","no-check")
                .header("Content-Type","multipart/form-data")
                .pathParams("key","10005")
                .multiPart("file",new File("//Users//sonalpri//Downloads//My_First_Framework//testAttachmentFile.txt"))
                .filter(session)
                .when().post("/rest/api/2/issue/{key}/attachments")
                .then().log().all().assertThat().statusCode(200);

        //Get issue details
 String issueDetail=given().log().all().pathParams("key", "10005")
                .queryParam("fields", "comment")
                .filter(session).header("Content-Type", "application/json")
                .when().get("/rest/api/2/issue/{key}")
                .then().assertThat().statusCode(200).extract().response().asString();
        System.out.println("issue detail is: "+issueDetail);
        JsonPath js2 = new JsonPath(issueDetail);
       int commentscount= js2.getInt("fields.comment.comments.size()");
       for (int i=0;i<commentscount-1;i++){
        String commentIdIssue = js2.get("fields.comment.comments["+i+"].id");
           if(commentIdIssue.equalsIgnoreCase(commentId)){
              String message= js2.get("fields.comment.comments["+i+"].body").toString();
               System.out.println("************************** message: "+message);
               Assert.assertEquals(message,expectedComment,"Comment doesnot match");
           }

       }
    }
}
