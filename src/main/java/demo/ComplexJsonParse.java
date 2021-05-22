package demo;

import file.Payload;
import file.ReusableMethod;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.CoursePrice());
        int totalActual =0;
       int purchaseAmount = js.getInt("dashboard.purchaseAmount");
       int countOfCourse = js.getInt("courses.size()");
       String titleOfFirstCourse = js.getString("courses[0].title");
        String priceOfRPACourse = js.getString("courses[0].price");
        System.out.println(countOfCourse+","+purchaseAmount+" , "+titleOfFirstCourse+","+priceOfRPACourse);

        //print all the course with their respective prices
        for (int i=0;i<countOfCourse;i++){
           String title = js.getString("courses["+i+"].title");
           int price = js.getInt("courses["+i+"].price");
           int a= i+1;
            System.out.println("Course :"+a);
            System.out.println("Title: "+title+ "  price : "+price);

        }
        for (int i=0;i<countOfCourse;i++){
            int title = js.getInt("courses["+i+"].price");
            int price = js.getInt("courses["+i+"].copies");
            totalActual= totalActual+title*price;

        }
        System.out.println("total Actual : " + totalActual);


    }
}
