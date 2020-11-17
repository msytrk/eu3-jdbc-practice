package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class simpleGetRequest {

    String hrUrl = "http://107.21.153.23:1000/ords/hr/regions";

    @Test
    public void test1() {

        Response response = RestAssured.get(hrUrl);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        //same with
        System.out.println("response.statusCode() = " + response.statusCode());

        response.prettyPrint();
    }
    @Test
    public void test2() {
        /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
        */

        Response response = RestAssured.given().accept(ContentType.JSON)
                                       .when().get(hrUrl);
        //Verify status 200 or not
        Assert.assertEquals(response.statusCode(),200);
        //Verify content type is application/json
        System.out.println("response.contentType() = " + response.contentType());
        Assert.assertEquals(response.contentType(),"application/json");

    }

    @Test
    public void test3() {
        /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
        */

        RestAssured.given().accept(ContentType.JSON)
                    .when().get(hrUrl)
                    .then()
                    .assertThat().statusCode(200)
                    .and().contentType("application/json");



    }
    @Test
    public void test4() {


        Response response = RestAssured.given().accept(ContentType.JSON).get(hrUrl + "/2");

        //Verify status 200 or not
        Assert.assertEquals(response.statusCode(),200);

        //Verify content type is application/json
        System.out.println("response.contentType() = " + response.contentType());
        Assert.assertEquals(response.contentType(),"application/json");

        // Verify body contains America
        Assert.assertTrue(response.body().asString().contains("Americas"));


    }
    }

