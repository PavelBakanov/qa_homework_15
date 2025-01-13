package tests;

import api.UsersApi;
import io.qameta.allure.Owner;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("reqres.in")
@DisplayName("Тесты с базой пользователей")
public class UsersTests extends TestBase {

    UsersApi usersApi = new UsersApi();

    @Test
    @Owner("bakanovpb")
    @DisplayName("Проверка определенного емэйла")
    void checkUserEmailTest() {
        UserListResponseModel response = usersApi.getUserList();

        usersApi.checkCorrespondence("michael.lawson@reqres.in", 0, response);
    }

    @Test
    @Owner("bakanovpb")
    @DisplayName("Проверка корректировки данных")
    void patchRequestTest() {
        RequestModel request = RequestModel.builder()
                .name("morpheus")
                .job("zion resident").build();

        PatchResponseModel response = usersApi.patchUserData(request);

        usersApi.checkCorrespondence("morpheus", "zion resident", response);
    }

    @Test
    @Owner("bakanovpb")
    @DisplayName("Проверка удаления данных")
    void deleteRequestTest() {
        usersApi.deleteData();
    }

    @Test
    @Owner("bakanovpb")
    @DisplayName("Проверка занесения новых данных")
    void putRequestTest() {
        RequestModel request = RequestModel.builder()
                .name("morpheus")
                .job("zion resident").build();

        PutResponseModel response = usersApi.putNewData(request);

        usersApi.checkCorrespondence("morpheus", "zion resident", response);
    }
}