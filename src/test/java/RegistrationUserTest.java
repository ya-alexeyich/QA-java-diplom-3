import base.ConfigurationWebDriver;
import base.URL;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import steps.UserSteps;
import user.*;

import static base.ConfigurationWebDriver.setDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;

@Epic("Регистрация")
public class RegistrationUserTest {
    private UserModel userModel;
    private UserSteps userSteps;

    @Before
    public void setUp() {
        setDriver(ConfigurationWebDriver.BROWSER_NAME);
        Selenide.open(URL.MAIN_PAGE);
        userModel= UserGenerator.getRandom();
        userSteps = new UserSteps();

    }

    @After
    public void cleanUp() {
        String accessToken = userSteps.login(UserCredentials.from(userModel)).extract().body().jsonPath().get("accessToken");
        if (accessToken != null) {
            userSteps.delete(accessToken);
        }
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Тест Успешная регистрация")
    @Description("Проверка открылась страница логина после успешной регистрации")
    public void userCanBeRegisterWithValidDataTest() {
        new MainPage()
                .clickLogin()
                .clickRegister()
                .registerUser(userModel, LoginPage.class)
                .loginTextIsShowed();
        assertEquals(URL.LOGIN_PAGE, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Тест Ошибка для некорректного пароля")
    @Description("Проверка открылась страница регистрации после регистрации с некорректным паролем")
    public void userCanNotBeRegisterWithIncorrectPasswordTest() {
        userModel.setPassword("Фa1se");
        new MainPage()
                .clickLogin()
                .clickRegister()
                .registerUser(userModel, RegisterPage.class)
                .incorrectPasswordIsShowed();
        assertEquals(URL.REGISTER_PAGE, getWebDriver().getCurrentUrl());
    }
}
