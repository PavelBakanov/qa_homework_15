package api;

import models.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ReqresSpecifications.*;

public class UsersApi {

    public UserListResponseModel getUserList() {
        UserListResponseModel response = step("Сделать запрос полного списка пользователей", () ->
                given(commonRequest)
                        .queryParam("page","2")
                        .get("/users")
                        .then()
                        .spec(response200)
                        .extract().as(UserListResponseModel.class));
        return response;
    }

    public void checkCorrespondence(String email, int id, UserListResponseModel response) {
        step("Проверить, что в ответе у определенного пользователя емэйл соответсвует запрашиваему", () ->
                assertEquals(response.getData().get(id).getEmail(), email));
    }

    public void checkCorrespondence(String name, String job, PatchResponseModel response) {
        step("Проверить в ответе соответствие name и job", () -> {
            assertEquals(response.getName(), name);
            assertEquals(response.getJob(), job);
        });
    }

    public void checkCorrespondence(String name, String job, PutResponseModel response) {
        step("Проверить в ответе соответствие name и job", () -> {
            assertEquals(response.getName(), name);
            assertEquals(response.getJob(), job);
        });
    }

    public PatchResponseModel patchUserData(RequestModel request) {
        PatchResponseModel response = step("Сделать запрос корректировки данных", () ->

                given(commonRequest)
                        .body(request)

                        .when()
                        .patch("/users/2")

                        .then()
                        .spec(response200)

                        .extract().as(PatchResponseModel.class));
        return response;
    }

    public void deleteData() {
        step("Удалить данные. В ответе будет код ошибки 204", () ->
                given(commonRequest)
                        .delete("/users/2")
                        .then()
                        .spec(response204));
    }

    public PutResponseModel putNewData(RequestModel request) {
        PutResponseModel response = step("Сделать запрос занесения новых данных", () ->
                given(commonRequest)
                        .body(request)

                        .when()
                        .put("/users/2")

                        .then()
                        .spec(response200)
                        .extract().as(PutResponseModel.class));
        return response;
    }

}
