package apitests.Day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
public class jsonToJavaCollection {


    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }

    @Test
    public void SpartanToMap(){
        // Deserialization
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //we will convert json response to java map
        Map<String,Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);
        String name = (String) jsonDataMap.get("name");
        assertEquals(name,"Meta");

        BigDecimal phone = new BigDecimal(String.valueOf(jsonDataMap.get("phone")));

        System.out.println("phone = " + phone);    }
    // Day 6
    @Test
    public void allSpartansListOfMap(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);
        // we need to serialize JSON response to List of Maps

        List<Map<String,Object>> allSpartanList=response.body().as(List.class);
        System.out.println("allSpartanList = " + allSpartanList);
        // print second spartan firstname
        System.out.println("allSpartanList.get(1).get() = " + allSpartanList.get(1).get("name"));

        //Save spartan3 in a map
        Map<String,Object> spartan3=allSpartanList.get(2);
    }
    // Day 6
    @Test
    public void regionToMap(){
        Response response = when().get("http://3.92.52.96:1000/ords/hr/regions");
        assertEquals(response.statusCode(),200);
        // we de-serialize jsonn response to Map
        Map<String,Object> regionMap=response.body().as(Map.class);

        System.out.println("regionMap.get(\"count\") = " + regionMap.get("count"));
        System.out.println("regionMap.get(\"hasMore\") = " + regionMap.get("hasMore"));
        System.out.println("regionMap.get(\"items\") = " + regionMap.get("items"));
        // Casting important do it again by yourself
        List<Map<String,Object>> itemList=(List<Map<String,Object>> )regionMap.get("items");
        System.out.println("itemList = " + itemList);

        // print first region name

        System.out.println("itemList.get(0).get(\"region_name\") = " + itemList.get(0).get("region_name"));








    }




}
