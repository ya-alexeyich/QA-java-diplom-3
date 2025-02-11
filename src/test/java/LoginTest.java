import base.ConfigurationWebDriver;
import base.URL;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.WebDriverContainer;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import steps.UserSteps;
import user.*;

import static base.ConfigurationWebDriver.setDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;

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
    @DisplayName("Тест Вход через кнопку Войти в аккаунт")
    @Description("Проверка открылась главная страница после входа в аккаунт по кнопке Войти в аккаунт на главной")
    public void userCanBeLoginFromMainPageTest() {
        Selenide.open(URL.MAIN_PAGE, MainPage.class)
                .clickLogin()
                .login(userModel)
                .orderButtonIsShowed();
        assertEquals(URL.MAIN_PAGE, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Тест вход через кнопку Личный кабинет")
    @Description("Проверка открылась главная страница после входа в аккаунт по кнопке Личный кабинет на главной")
    public void userCanBeLoginFromProfileButtonTest() {
        Selenide.open(URL.MAIN_PAGE, MainPage.class)
                .clickProfile()
                .login(userModel)
                .orderButtonIsShowed();
        assertEquals(URL.MAIN_PAGE, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Тест Вход через кнопку в форме регистрации")
    @Description("Проверка открылась главная страница после входа в аккаунт по кнопке Войти в форме регистрации")
    public void userCanBeLoginFromRegisterPageTest() {
        Selenide.open(URL.REGISTER_PAGE, RegisterPage.class)
                .clickLogin()
                .login(userModel)
                .orderButtonIsShowed();
        assertEquals(URL.MAIN_PAGE, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Тест Вход через кнопку в форме восстановления пароля")
    @Description("Проверка открылась главная страница после входа в аккаунт по кнопке Войти в форме восстановления пароля")
    public void userCanBeLoginFromPasswordRecoveryPageTest() {
        Selenide.open(URL.PASSWORD_RECOVERY_PAGE, PasswordRecoveryPage.class)
                .clickLogin()
                .login(userModel)
                .orderButtonIsShowed();
        assertEquals(URL.MAIN_PAGE, getWebDriver().getCurrentUrl());
    }
}
