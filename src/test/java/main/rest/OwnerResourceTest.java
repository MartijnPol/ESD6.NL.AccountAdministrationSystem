package main.rest;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static main.rest.RestTestSetup.setUpRestResource;
import static org.hamcrest.CoreMatchers.equalTo;

public class OwnerResourceTest {
    @BeforeClass
    public static void setUp() {
        setUpRestResource();
    }

    @Test
    public void getOwner() {
        JSONObject jsonObject = new JSONObject()
                .put("firstName", "Henk")
                .put("lastName", "van der Pol")
                .put("citizenServiceNumber", 1234567890);

        given().
                contentType(ContentType.JSON).
                body(jsonObject.toString()).
                when().
                post("owners").
                then().
                statusCode(200).
                body("fullName", equalTo("Henk van der Pol"),
                        "citizenServiceNumber", equalTo(1234567890),
                        "address.street", equalTo("Nijverheidsweg"),
                        "ownerships[0].car.carTrackerId", equalTo("NLD1"),
                        "ownerships[0].car.licensePlate", equalTo("08-SK-PX"),
                        "ownerships[0].car.owner.fullName", equalTo("Henk van der Pol"));
    }
}
