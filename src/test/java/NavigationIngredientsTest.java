import base.ConfigurationWebDriver;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static base.ConfigurationWebDriver.setDriver;
import static org.junit.Assert.assertEquals;

@Epic("Раздел  Конструктор переходы к разделам")
public class NavigationIngredientsTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
        setDriver(ConfigurationWebDriver.BROWSER_NAME);
        Selenide.open("/");
        mainPage = new MainPage();
    }

    @After
    public void cleanUp() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Тест Переход к разделу Булки")
    public void crossingSectionBunsIsCorrectTest() {
        mainPage.showAvailableFillings();
        mainPage.showAvailableSauces();
        mainPage.showAvailableBuns();
        assertEquals("Булки", mainPage.getBunsTabText());
    }

    @Test
    @DisplayName("Тест Переход к разделу Соусы")
    public void crossingSectionSaucesIsCorrectTest() {
        mainPage.showAvailableFillings();
        mainPage.showAvailableBuns();
        mainPage.showAvailableSauces();
        assertEquals("Соусы", mainPage.getSaucesTabText());
    }

    @Test
    @DisplayName("Тест Переход к разделу Начинки")
    public void crossingSectionFillingsIsCorrectTest() {
        mainPage.showAvailableSauces();
        mainPage.showAvailableBuns();
        mainPage.showAvailableFillings();
        assertEquals("Начинки", mainPage.getFillingsTabText());
    }
}
