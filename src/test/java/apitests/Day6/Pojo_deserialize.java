package apitests.Day6;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("http://3.92.52.96:8000/api/spartans/{id}");
        assertEquals(response.statusCode(),200);

        //Json to POJO
        // Json to our Spartan Class

        Spartan spartan15=response.body().as(Spartan.class);
        System.out.println(spartan15);
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getId() = " + spartan15.getId());

        // asssertion

        assertEquals(spartan15.getId(),15);
    }
}
