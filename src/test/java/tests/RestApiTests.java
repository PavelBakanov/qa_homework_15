package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class RestApiTests extends TestBase {

    @Test
    void successfulLoginTest() {
        String loginData = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"cityslicka\"}";
        given()
                .body(loginData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/login")

                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    void checkUserEmailTest() {
        get("/users?page=2")
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .body("data.email", hasItem("michael.lawson@reqres.in"));
    }

    @Test
    void successfulRegistrationTest() {
        String registrationData = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\"}";

        given()
                .body(registrationData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", notNullValue());
    }

    @Test
    void patchRequestTest() {
        String patchData = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        given()
                .body(patchData)
                .contentType(JSON)
                .log().uri()

                .when()
                .patch("/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void deleteRequestTest() {
        delete("/users/2")
                .then()
                .log().body()
                .log().status()
                .statusCode(204);
    }

    @Test
    void putRequestTest() {
        String putData = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        given()
                .body(putData)
                .contentType(JSON)
                .log().uri()

                .when()
                .put("/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

}
