package tests;

import api.RegisterApi;
import io.qameta.allure.Owner;
import models.RegisterResponseModel;
import models.RequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("bakanovpb")
@Tag("reqres.in")
@DisplayName("Тесты на регистрацию")
public class RegisterTests extends TestBase{

    RegisterApi registerApi = new RegisterApi();

    @Test
    @DisplayName("Проверка успешной регистрации")
    void successfulRegistrationTest() {
        RequestModel request = new RequestModel();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");

        RegisterResponseModel response = registerApi.doRegisterPostRequest(request);

        registerApi.checkTokenAndId(response);
    }
}
