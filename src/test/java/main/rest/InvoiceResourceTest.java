package main.rest;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static main.rest.RestTestSetup.setUpRestResource;
import static org.hamcrest.CoreMatchers.hasItems;

/**
 * @author Thom van de Pas on 23-5-2018
 */
public class InvoiceResourceTest {

    @BeforeClass
    public static void setUp() {
        setUpRestResource();
    }

    @Ignore
    @Test public void
    invoice_resource_returns_200_with_expected_invoiceNr() {
                when().
                        get("/invoices/{citizenServiceNumber}", 1234567890).
                then().
                        statusCode(200).
                        body("invoiceNr", hasItems(20181, 20182));
    }
    @Ignore
    @Test public void
    invoice_resource_returns_404() {
        when().
                get("/invoices/{citizenServiceNumber}", 1234567891).
        then().
                statusCode(404);
    }
}
