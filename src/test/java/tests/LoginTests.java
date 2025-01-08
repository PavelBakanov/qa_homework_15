package tests;

import api.LoginApi;
import io.qameta.allure.Owner;
import models.LoginRequestModel;
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
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setEmail("eve.holt@reqres.in");
        loginData.setPassword("cityslicka");

        LoginResponseModel response = loginApi.doLoginPostRequest(loginData);

        loginApi.checkToken(response);
    }
}
