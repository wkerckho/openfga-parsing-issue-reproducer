package org.example.openfga.test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

@QuarkusTest
@TestHTTPEndpoint(TripResource.class)
public class TestTripResource {

    @Test
    @TestSecurity(user = "test-user")
    public void testAccessTrip() {
        // Should result in 403 as no tuple exist that links the resource to the test user
        when()
                .get("some-trip-id")
                .then()
                .statusCode(403);
    }

}
