import base.ConfigurationWebDriver;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;
import steps.UserSteps;
import user.*;

import static base.ConfigurationWebDriver.setDriver;

@Epic("Регистрация")
public class RegistrationUserTest {
    private UserModel userModel;
    private UserSteps userSteps;

    @Before
    public void setUp() {
        setDriver(ConfigurationWebDriver.BROWSER_NAME);
        Selenide.open("/");
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
    public void userCanBeRegisterWithValidDataTest() {
        new MainPage()
                .clickLogin()
                .clickRegister()
                .registerUser(userModel)
                .loginTextIsShowed();
    }

    @Test
    @DisplayName("Тест Ошибка для некорректного пароля")
    public void userCanNotBeRegisterWithIncorrectPasswordTest() {
        userModel.setPassword("Фa1se");
        new MainPage()
                .clickLogin()
                .clickRegister()
                .registerUserWithIncorrectPassword(userModel)
                .incorrectPasswordIsShowed();
    }
}
