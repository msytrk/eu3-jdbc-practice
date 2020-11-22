package apitests.homework1;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class hw1 {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }

    /*

    ORDS API:
Q1:
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is

    */
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("country_id", "US")
                .when().get("/countries/{country_id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        // Verify all country_id equals US
        assertEquals(response.path("country_id"),"US");
        // Verify all Country name same with result
        assertEquals(response.path("country_name"),"United States of America");
        // Verify all region_id  equals 2
        int id=response.path("region_id");
        assertEquals(id,2);


    }
    /*

Q2:
- Given accept type is Json
- Query param value - q={"department_id":80}
- When users sends request to /employees
- Then status code is 200
- And Content - Type is Json
- And all job_ids start with 'SA'
- And all department_ids are 80
- Count is 25

 */
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        // check all department_id equal 80 with jsonPath

        JsonPath jsonPath=response.jsonPath();
        List<Integer> depIDDs = jsonPath.getList("items.department_id");
        for (Integer depIDD : depIDDs) {
            System.out.println(depIDD);
            assertEquals(depIDD,(Integer)80);
        }
        // And all job_ids start with 'SA'
        List<String> job_IDs = response.path("items.job_id");
        for (String job_ID : job_IDs) { System.out.println(job_ID);
            assertTrue(job_ID.startsWith("SA"));
        }
        //Count is 25
        System.out.println("job_IDs.size() = " + job_IDs.size());
        assertEquals(job_IDs.size(),25);

    }



    /*
Q3:
- Given accept type is Json
-Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
     */
    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        // check all region_id equal 3 with jsonPath

        JsonPath jsonPath=response.jsonPath();
        List<Integer> regIDs = jsonPath.getList("items.region_id");
        for (Integer regID : regIDs) {
            System.out.println(regID);
            assertEquals(regID,(Integer)3);
        }
        //Count is 6
        System.out.println("regIDs.size() = " + regIDs.size());
        assertEquals(regIDs.size(),6);
        // And hasMore is false
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore").toString());
        assertFalse(response.path("hasMore"));
        // And Country_name are;
        List<String> expectedCountries= Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore");
        System.out.println("expectedCountries = " + expectedCountries);

        List<String> actualCountries=response.path("items.country_name");
        System.out.println("actualCountries = " + actualCountries);

        assertEquals(actualCountries,expectedCountries);


    }
}
