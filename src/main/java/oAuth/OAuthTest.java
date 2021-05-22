package oAuth;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.*;

public class OAuthTest {
    public static void main(String[] args) throws InterruptedException {
        //get code through website
        System.setProperty("webdriver.chrome.driver","/Users/sonalpri/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://www.googleapis.com/oauth2/v4/token&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=anyRandomString");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("sonalpriya172@gmail.com");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("9431276215");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);
        String url = driver.getCurrentUrl();
        System.out.println("url is: "+ url);

        //get access token
        String accessTokenResponse = given().queryParams("code","")
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
        .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath js = new JsonPath(accessTokenResponse);
      String accessToken=  js.getString("access_token");


        //hit url
    String response=  given().queryParam("access_token",accessToken)
                .when().get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println("response: "+response);
    }

}
