package file;

import java.util.Random;

public class Payload {

    public static String addPlace(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Rahul Shetty Academy\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://rahulshettyacademy.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }
    public static String CoursePrice(){
        return "{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 1820,\n" +
                "\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\n" +
                "\"price\": 50,\n" +
                "\n" +
                "\"copies\": 6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\": 40,\n" +
                "\n" +
                "\"copies\": 4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\": 45,\n" +
                "\n" +
                "\"copies\": 10\n" +
                "\n" +
                "},\n" +
                "{\n" +
                "\n" +
                "\"title\": \"MBA\",\n" +
                "\n" +
                "\"price\": 70,\n" +
                "\n" +
                "\"copies\": 13\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}";
    }

    public static String addBook(String abcd1, String isbn1){
        Random random=new Random();
       // int a = 10+random.nextInt(90);
        return "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+abcd1+"\",\n" +
                "\"aisle\":\""+isbn1+"\",\n" +
                "\"author\":\"John foe2\"\n" +
                "}";
    }
    public static String createSession(){
        return "{\n" +
                "\"username\":\"sonalpriya172\",\n" +
                "\"password\":\"Sonal_17\"\n" +
                "}";
    }
}
