package tests;

import api.RegisterApi;
import io.qameta.allure.Owner;
import models.RegisterRequestModel;
import models.RegisterResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("reqres.in")
@DisplayName("Тесты на регистрацию")
public class RegisterTests extends TestBase{

    RegisterApi registerApi = new RegisterApi();

    @Test
    @Owner("bakanovpb")
    @DisplayName("Проверка успешной регистрации")
    void successfulRegistrationTest() {
        RegisterRequestModel request = new RegisterRequestModel();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");

        RegisterResponseModel response = registerApi.doRegisterPostRequest(request);

        registerApi.checkTokenAndId(response);
    }
}
