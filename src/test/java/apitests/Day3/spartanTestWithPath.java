package apitests.Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class spartanTestWithPath {

    @BeforeClass
    public void beforeclass() {
        baseURI = "http://3.80.189.73:8000";
    }

    /*
         Given accept type is json
         And path param id is 10
         When user sends a get request to "api/spartans/{id}"
         Then status code is 200
         And content-type is "application/json;charset=UTF-8"
         And response payload values match the following:
                 id is 10,
                 name is "Lorenza",
                 gender is "Female",
                 phone is 3312820936
   */
    @Test
    public void test() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");
        // printing each key valuee in the jsonBody/Payload
        // response.prettyPrint();
        // Using path first time
        // it returns T value so we should cast it
        System.out.println("response.path(\"id\").toString() = " + response.path("id").toString());
        //same with upper one
        System.out.println("response.body().path(\"id\").toString() = " + response.body().path("id").toString());
        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());
        System.out.println("response.path(\"phone\").toString() = " + response.path("phone").toString());

        // save json key values

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phoneNumber = response.path("phone");
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("phoneNumber = " + phoneNumber);
        System.out.println("gender = " + gender);

        //Assert
        assertEquals(id, 10);
        assertEquals(name, "Lorenza");
        assertEquals(phoneNumber, 3312820936l);
        assertEquals(gender, "Female");


    }

    @Test
    public void getAllSpartanWithPath(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);
        //verify content type
        assertEquals(response.getHeader("Content-Type"),"application/json;charset=UTF-8");

        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        String lastFirstName = response.path("name[-2]");
        System.out.println("lastFirstName = " + lastFirstName);

        int lastId = response.path("id[-1]");
        System.out.println("lastId = " + lastId);

        //print all names of spartans
        List<String> names = response.path("name");
        System.out.println("names = " + names);

        List<Object> phones = response.path("phone");
        for (Object phone : phones) {
            System.out.println(phone);
        } }
        @Test
                public void allSame(){

            Response response = given().accept(ContentType.JSON)
                    .when().get("/api/spartans");
            List<String> ids=response.path("region_id");

            assertEquals(ids,2);


        }}
