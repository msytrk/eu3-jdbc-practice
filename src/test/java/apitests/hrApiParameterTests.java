package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class hrApiParameterTests {
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("hr_api_url");
    }

    @Test
    public void test(){

        get("/regions");

    }
}
