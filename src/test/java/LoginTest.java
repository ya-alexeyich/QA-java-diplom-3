import base.ConfigurationWebDriver;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import steps.UserSteps;
import user.*;

import static base.ConfigurationWebDriver.setDriver;

@Epic("Вход в аккаунт")
public class LoginTest {
    private UserModel userModel;
    private UserSteps userSteps;
    private String accessToken;

    @Before
    public void setUp() {
        setDriver(ConfigurationWebDriver.BROWSER_NAME);
        userModel= UserGenerator.getRandom();
        userSteps = new UserSteps();
        accessToken = userSteps.register(userModel).extract().body().jsonPath().get("accessToken");
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            userSteps.delete(accessToken);
        }
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Тест Вход по кнопке Войти в аккаунт на главной")
    public void userCanBeLoginFromMainPageTest() {
        Selenide.open(MainPage.URL, MainPage.class)
                .clickLogin()
                .login(userModel)
                .orderButtonIsShowed();
    }

    @Test
    @DisplayName("Тест вход через кнопку Личный кабинет")
    public void userCanBeLoginFromProfileButtonTest() {
        Selenide.open(MainPage.URL, MainPage.class)
                .clickProfile()
                .login(userModel)
                .orderButtonIsShowed();
    }

    @Test
    @DisplayName("Тест Вход через кнопку в форме регистрации")
    public void userCanBeLoginFromRegisterPageTest() {
        Selenide.open(RegisterPage.URL, RegisterPage.class)
                .clickLogin()
                .login(userModel)
                .orderButtonIsShowed();
    }

    @Test
    @DisplayName("Тест Вход через кнопку в форме восстановления пароля")
    public void userCanBeLoginFromPasswordRecoveryPageTest() {
        Selenide.open(PasswordRecoveryPage.URL, PasswordRecoveryPage.class)
                .clickLogin()
                .login(userModel)
                .orderButtonIsShowed();
    }
}
