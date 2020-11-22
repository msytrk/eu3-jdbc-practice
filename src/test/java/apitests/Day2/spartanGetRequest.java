package apitests.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

//static import for RestAssured
import static io.restassured.RestAssured.*;

public class spartanGetRequest {


    String spartanUrl = "http://107.21.153.23:8000";

    @Test
    public void test1() {
        Response response = when().get(spartanUrl + "/api/spartans");
        response.prettyPrint();
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertTrue(response.body().asString().contains("Terence"));

    }

    @Test
    public void test2() {

        /* TASK
            When users sends a get request to /api/spartans/3
            Then status code should be 200
            And content type should be application/json;charset=UFT-8
            and json body should contain Fidole
        */

        RestAssured.when().get(spartanUrl + "/spartans/3")
                .then()
                .assertThat().statusCode(200)
                .and().assertThat().contentType("text/html;charset=UTF-8")
                .toString().contains("Fidole");


        Response response = when().get(spartanUrl + "/api/spartans/3");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json;charset=UTF-8");
        Assert.assertTrue(response.body().asString().contains("Fidole"));

    }

    @Test
    public void test3() {

        /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
                */
        Response response = given().accept(ContentType.TEXT)
                .when().get(spartanUrl + "/api/hello");
        //Then response status code should be 200
        Assert.assertEquals(response.statusCode(), 200);
        //Content type header should be "text/plain;charset=UTF-8"
        Assert.assertEquals(response.contentType(), "text/plain;charset=UTF-8");
        //header should contain date
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        //Content-Length should be 17
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        Assert.assertEquals(response.header("Content-Length"), "17");
        //body should be "Hello from Sparta

        System.out.println("response.getBody().asString() = " + response.body().asString());
        Assert.assertEquals(response.getBody().asString(), "Hello from Sparta");

    }
}
