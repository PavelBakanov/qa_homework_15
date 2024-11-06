package tests;

import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.DeleteUsersSpec.deleteResponseSpec;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;
import static specs.PatchUsersSpec.patchUsersRequestSpec;
import static specs.PatchUsersSpec.patchUsersResponseSpec;
import static specs.PutUsersSpec.putUsersRequestSpec;
import static specs.PutUsersSpec.putUsersResponseSpec;
import static specs.RegisterSpec.registerRequestSpec;
import static specs.RegisterSpec.registerResponseSpec;
import static specs.CheckEmailSpec.usersResponseSpec;

public class RestApiTests extends TestBase {

    @Test
    @DisplayName("Проверка успешной входа в учетную запись")
    void successfulLoginTest() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail("eve.holt@reqres.in");
        loginData.setPassword("cityslicka");
        LoginResponseModel response = step("Сделать запрос логина", () ->
                given(loginRequestSpec)
                        .body(loginData)

                        .when()
                        .post()

                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Проверить, что в ответе есть токен", () -> assertNotNull(response.getToken()));


    }

    @Test
    @DisplayName("Проверка определенного емэйла")
    void checkUserEmailTest() {
        UserListResponseModel response = step("Сделать запрос полного списка пользователей", () ->
                get("api/users?page=2")
                        .then()
                        .spec(usersResponseSpec)
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
                given(registerRequestSpec)
                        .body(request)

                        .when()
                        .post()

                        .then()
                        .spec(registerResponseSpec)
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

                given(patchUsersRequestSpec)
                        .body(request)

                        .when()
                        .patch()

                        .then()
                        .spec(patchUsersResponseSpec)
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
                delete("api/users/2")
                        .then()
                        .spec(deleteResponseSpec));
    }

    @Test
    @DisplayName("Проверка занесения новых данных")
    void putRequestTest() {
        PutRequestModel request = new PutRequestModel();
        request.setName("morpheus");
        request.setJob("zion resident");

        PutResponseModel response = step("Сделать запрос занесения новых данных", () ->
                given(putUsersRequestSpec)
                        .body(request)

                        .when()
                        .put("api/users/2")

                        .then()
                        .spec(putUsersResponseSpec)
                        .extract().as(PutResponseModel.class));

        step("Проверить в ответе соответствие name и job", () -> {
            assertEquals(response.getName(), "morpheus");
            assertEquals(response.getJob(), "zion resident");
        });
    }

}
