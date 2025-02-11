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
import steps.UserSteps;
import user.*;

import static base.ConfigurationWebDriver.setDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertEquals;

@Epic("Переход в личный кабинет из личного кабинета")
public class NavigationPagesTest {
    private UserModel userModel;
    private UserSteps userSteps;
    private String accessToken;

    @Before
    public void setUp() {
        setDriver(ConfigurationWebDriver.BROWSER_NAME);
        Selenide.open(URL.LOGIN_PAGE);
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
    @Description("Проверка открылась страница личного кабинета после авторизации и клика на Личный кабинет")
    public void userCanBeCrossingToProfilePageTest() {
        new LoginPage()
                .login(userModel)
                .clickProfileAfterLogin()
                .profileTextIsShowed();
        assertEquals(URL.PROFILE_PAGE, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Тест Переход из личного кабинета в конструктор по клику Stellar Burgers")
    @Description("Проверка открылась главная страница с конструктором после после перехода в личный кабинет и клика на Stellar Burgers")
    public void userCanBeCrossingToMainPageLogoButtonTest() {
        new LoginPage()
                .login(userModel)
                .clickProfileAfterLogin()
                .clickBurgerLogo()
                .createBurgerTextIsShowed();
        assertEquals(URL.MAIN_PAGE, getWebDriver().getCurrentUrl());
    }

    @Test
    @DisplayName("Тест Переход из личного кабинета в конструктор по клику Конструктор")
    @Description("Проверка открылась главная страница с конструктором после после перехода в личный кабинет и клика на Конструктор")
    public void userCanBeCrossingToMainPageConstructorLinkTest() {
        new LoginPage()
                .login(userModel)
                .clickProfileAfterLogin()
                .clickConstructorLink()
                .createBurgerTextIsShowed();
        assertEquals(URL.MAIN_PAGE, getWebDriver().getCurrentUrl());
    }
}
