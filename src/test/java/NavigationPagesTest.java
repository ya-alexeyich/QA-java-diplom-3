import base.ConfigurationWebDriver;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import steps.UserSteps;
import user.*;

import static base.ConfigurationWebDriver.setDriver;

@Epic("Переход в личный кабинет из личного кабинета")
public class NavigationPagesTest {
    private UserModel userModel;
    private UserSteps userSteps;
    private String accessToken;

    @Before
    public void setUp() {
        setDriver(ConfigurationWebDriver.BROWSER_NAME);
        Selenide.open("\\login");
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
    @DisplayName("Тест Переход по клику на Личный кабинет")
    public void userCanBeCrossingToProfilePageTest() {
        new LoginPage()
                .login(userModel)
                .clickProfileAfterLogin()
                .profileTextIsShowed();
    }

    @Test
    @DisplayName("Тест Переход из личного кабинета в конструктор по клику на Stellar Burgers")
    public void userCanBeCrossingToMainPageLogoButtonTest() {
        new LoginPage()
                .login(userModel)
                .clickProfileAfterLogin()
                .clickBurgerLogo()
                .createBurgerTextIsShowed();
    }

    @Test
    @DisplayName("Тест Переход из личного кабинета в конструктор по клику на Конструктор")
    public void userCanBeCrossingToMainPageConstructorLinkTest() {
        new LoginPage()
                .login(userModel)
                .clickProfileAfterLogin()
                .clickConstructorLink()
                .createBurgerTextIsShowed();
    }
}
