package demo;

import file.Payload;
import file.ReusableMethod;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfCourses(){
        JsonPath js = new JsonPath(Payload.CoursePrice());
       int countOfCourse=  js.getInt("courses.size()");

        int totalActual=0;
        for (int i = 0; i<countOfCourse; i++){
            int title = js.getInt("courses["+i+"].price");
            int price = js.getInt("courses["+i+"].copies");
            totalActual= totalActual+title*price;

        }
        System.out.println("total Actual : " + totalActual);
       int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(totalActual,purchaseAmount," Purchase amount is not total amount");
    }
}
