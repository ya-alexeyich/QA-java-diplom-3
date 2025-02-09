package steps;

import base.StellarBurgersHttpClient;
import base.URL;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import user.*;
import static io.restassured.RestAssured.given;


public class UserSteps extends StellarBurgersHttpClient {

    @Step("Создание пользователя API")
    public ValidatableResponse register(UserModel userModel) {
        return given()
                .spec(getBaseRequestSpec())
                .body(userModel)
                .when()
                .post(URL.CREATE_USER)
                .then();
    }

    @Step("Авторизация пользователя API")
    public ValidatableResponse login(UserCredentials userCredentials) {
        return given()
                .spec(getBaseRequestSpec())
                .body(userCredentials)
                .when()
                .post(URL.LOGIN_USER)
                .then();
    }

    @Step("Удаление пользователя API")
    public void delete(String accessToken) {
        given()
                .spec(getBaseRequestSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(URL.DELETE_USER)
                .then();
    }
}
