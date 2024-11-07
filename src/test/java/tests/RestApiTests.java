package tests;

import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.ReqresSpecifications.*;

public class RestApiTests extends TestBase {

    @Test
    @DisplayName("Проверка успешной входа в учетную запись")
    void successfulLoginTest() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail("eve.holt@reqres.in");
        loginData.setPassword("cityslicka");
        LoginResponseModel response = step("Сделать запрос логина", () ->
                given(normalRequestSpec)
                        .body(loginData)

                        .when()
                        .post("/login")

                        .then()
                        .spec(response200)
                        .extract().as(LoginResponseModel.class));

        step("Проверить, что в ответе есть токен", () -> assertNotNull(response.getToken()));


    }

    @Test
    @DisplayName("Проверка определенного емэйла")
    void checkUserEmailTest() {
        UserListResponseModel response = step("Сделать запрос полного списка пользователей", () ->
                get("/users?page=2")
                        .then()
                        .spec(response200)
                        .extract().as(UserListResponseModel.class));

        step("Проверить, что в ответе у определенного пользователя емэйл соответсвует запрашиваему", () ->
                assertEquals(response.getData().get(0).getEmail(), "michael.lawson@reqres.in"));
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    void successfulRegistrationTest() {
        RegisterRequestModel request = new RegisterRequestModel();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");


        RegisterResponseModel response = step("Сделать запрос регистрации", () ->
                given(normalRequestSpec)
                        .body(request)

                        .when()
                        .post("/register")

                        .then()
                        .spec(response200)
                        .extract().as(RegisterResponseModel.class));

        step("Проверить, что id и token не пустые", () -> {
            assertNotNull(response.getId());
            assertNotNull(response.getToken());
        });

    }

    @Test
    @DisplayName("Проверка корректировки данных")
    void patchRequestTest() {
        PatchRequestModel request = new PatchRequestModel();
        request.setName("morpheus");
        request.setJob("zion resident");

        PatchResponseModel response = step("Сделать запрос корректировки данных", () ->

                given(normalRequestSpec)
                        .body(request)

                        .when()
                        .patch("/users/2")

                        .then()
                        .spec(response200)
                        .extract().as(PatchResponseModel.class));

        step("Проверить в ответе соответствие name и job", () -> {
            assertEquals(response.getName(), "morpheus");
            assertEquals(response.getJob(), "zion resident");
        });

    }

    @Test
    @DisplayName("Проверка удаления данных")
    void deleteRequestTest() {
        step("Удалить данные. В ответе будет код ошибки 204", () ->
                delete("/users/2")
                        .then()
                        .spec(response204));
    }

    @Test
    @DisplayName("Проверка занесения новых данных")
    void putRequestTest() {
        PutRequestModel request = new PutRequestModel();
        request.setName("morpheus");
        request.setJob("zion resident");

        PutResponseModel response = step("Сделать запрос занесения новых данных", () ->
                given(normalRequestSpec)
                        .body(request)

                        .when()
                        .put("/users/2")

                        .then()
                        .spec(response200)
                        .extract().as(PutResponseModel.class));

        step("Проверить в ответе соответствие name и job", () -> {
            assertEquals(response.getName(), "morpheus");
            assertEquals(response.getJob(), "zion resident");
        });
    }

}
