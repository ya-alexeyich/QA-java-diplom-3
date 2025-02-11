import base.ConfigurationWebDriver;
import base.URL;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static base.ConfigurationWebDriver.setDriver;
import static org.junit.Assert.assertEquals;

@Epic("Раздел Конструктор")
public class NavigationIngredientsTest {
    private MainPage mainPage;

    @Before
    public void setUp() {
        setDriver(ConfigurationWebDriver.BROWSER_NAME);
        Selenide.open(URL.MAIN_PAGE);
        mainPage = new MainPage();
    }

    @After
    public void cleanUp() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Тест Переход к разделу Булки")
    @Description("Проверка перехода к разделу Булки в разделе Конструктор")
    public void crossingSectionBunsIsCorrectTest() {
        mainPage.showAvailableFillings();
        mainPage.showAvailableSauces();
        mainPage.showAvailableBuns();
        assertEquals("Булки", mainPage.getBunsTabText());
    }

    @Test
    @DisplayName("Тест Переход к разделу Соусы")
    @Description("Проверка перехода к разделу Соусы в разделе Конструктор")
    public void crossingSectionSaucesIsCorrectTest() {
        mainPage.showAvailableFillings();
        mainPage.showAvailableBuns();
        mainPage.showAvailableSauces();
        assertEquals("Соусы", mainPage.getSaucesTabText());
    }

    @Test
    @DisplayName("Тест Переход к разделу Начинки")
    @Description("Проверка перехода к разделу Начинки в разделе Конструктор")
    public void crossingSectionFillingsIsCorrectTest() {
        mainPage.showAvailableSauces();
        mainPage.showAvailableBuns();
        mainPage.showAvailableFillings();
        assertEquals("Начинки", mainPage.getFillingsTabText());
    }
}
