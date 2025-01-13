package tests;

import api.RegisterApi;
import io.qameta.allure.Owner;
import models.RegisterResponseModel;
import models.RequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("reqres.in")
@DisplayName("Тесты на регистрацию")
public class RegisterTests extends TestBase {

    RegisterApi registerApi = new RegisterApi();

    @Test
    @Owner("bakanovpb")
    @DisplayName("Проверка успешной регистрации")
    void successfulRegistrationTest() {
        RequestModel request = RequestModel.builder()
                .email("eve.holt@reqres.in")
                .password("pistol").build();

        RegisterResponseModel response = registerApi.doRegisterPostRequest(request);

        registerApi.checkTokenAndId(response);
    }
}
