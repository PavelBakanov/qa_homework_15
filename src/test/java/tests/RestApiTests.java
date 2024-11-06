package tests;

import models.LoginRequestModel;
import models.LoginResponseModel;
import models.UserListResponseModel;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;

public class RestApiTests extends TestBase {

    @Test
    void successfulLoginTest() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail("eve.holt@reqres.in");
        loginData.setPassword("cityslicka");
        LoginResponseModel response = step("Сделать запрос", () ->
                given(loginRequestSpec)
                        .body(loginData)

                        .when()
                        .post()

                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Проверить ответ", () -> assertNotNull(response.getToken()));


    }

    @Test
    void checkUserEmailTest() {
        UserListResponseModel response = get("api/users?page=2")
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .extract().as(UserListResponseModel.class);
        assertEquals(response.getData().get(0).getEmail(), "michael.lawson@reqres.in");
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
