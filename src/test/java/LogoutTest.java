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

@Epic("Выход из аккаунта")
public class LogoutTest {
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
    @DisplayName("Тест Выход по кнопке Выйти в личном кабинете")
    public void userCanBeLogoutFromProfilePageTest() {
        Selenide.open(MainPage.URL, MainPage.class)
                .clickLogin()
                .login(userModel)
                .clickProfileAfterLogin()
                .clickLogoutButton()
                .loginTextIsShowed();
    }
}
