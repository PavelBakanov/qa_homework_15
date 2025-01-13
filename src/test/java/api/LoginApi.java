package api;

import models.RequestModel;
import models.LoginResponseModel;
import org.assertj.core.api.Assertions;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.ReqresSpecifications.commonRequest;
import static specs.ReqresSpecifications.response200;

public class LoginApi {

    public LoginResponseModel doLoginPostRequest(RequestModel loginData) {
        LoginResponseModel response = step("Сделать запрос логина", () ->
                given(commonRequest)
                        .body(loginData)

                        .when()
                        .post("/login")

                        .then()
                        .spec(response200)
                        .extract().as(LoginResponseModel.class));

        return response;
    }

    public void checkToken(LoginResponseModel response) {
        step("Проверить, что в ответе есть токен", () ->
                Assertions.assertThat(response.getToken()).isAlphanumeric());
    }

}
