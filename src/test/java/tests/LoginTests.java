package tests;

import api.LoginApi;
import io.qameta.allure.Owner;
import models.RequestModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("reqres.in")
@DisplayName("Тесты на логин")
public class LoginTests extends TestBase {

    LoginApi loginApi = new LoginApi();

    @Test
    @Owner("bakanovpb")
    @DisplayName("Проверка успешного входа в учетную запись")
    void successfulLoginTest() {
        RequestModel loginData = RequestModel.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka").build();

        LoginResponseModel response = loginApi.doLoginPostRequest(loginData);

        loginApi.checkToken(response);
    }
}
