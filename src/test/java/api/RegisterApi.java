package api;

import models.RegisterResponseModel;
import models.RequestModel;
import org.assertj.core.api.Assertions;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.ReqresSpecifications.commonRequest;
import static specs.ReqresSpecifications.response200;

public class RegisterApi {
    public RegisterResponseModel doRegisterPostRequest (RequestModel request)
    {
        RegisterResponseModel response = step("Сделать запрос регистрации", () ->
                given(commonRequest)
                        .body(request)

                        .when()
                        .post("/register")

                        .then()
                        .spec(response200)
                        .extract().as(RegisterResponseModel.class));
        return response;
    }

    public void checkTokenAndId(RegisterResponseModel response) {
        step("Проверить, что id и token не пустые", () -> {
            Assertions.assertThat(response.getId()).asInt();
            Assertions.assertThat(response.getToken()).isAlphanumeric();
        });
    }
}
