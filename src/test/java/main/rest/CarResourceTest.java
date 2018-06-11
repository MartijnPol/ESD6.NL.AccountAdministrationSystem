package main.rest;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static main.rest.RestTestSetup.setUpRestResource;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

/**
 * @author Thom van de Pas on 28-5-2018
 */
public class CarResourceTest {

    @BeforeClass
    public static void setup() {
        setUpRestResource();
    }

    @Test
    @Ignore
    public void
    car_resource_returns_200_with_expected_cars() {
        when().
                get("/cars/{citizenServiceNumber}", 1234567890).
                then().
                statusCode(200).
                body("licensePlate", hasItems("08-SK-PX", "00-01-ES", "GB-399-J"));
    }

    @Test
    @Ignore
    public void
    car_resource_find_by_license_plate_returns_200_with_expected_car() {
        when().
                get("/cars/find/{licencePlate}", "08-SK-PX")
                .then()
                .statusCode(200)
                .body("carTrackerId", equalTo("NLD1"));
    }

    @Test
    @Ignore
    public void
    car_resource_find_by_license_plate_returns_404() {
        when().
                get("/cars/find/{licensePlate}", "00-00-00")
                .then()
                .statusCode(404);
    }
}
