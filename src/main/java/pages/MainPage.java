package pages;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement createBurgerText = $(By.xpath("//h1[text()='Соберите бургер']"));
    private final SelenideElement bunsTab = $(By.xpath(".//span[text()='Булки']/.."));
    private final SelenideElement saucesTab = $(By.xpath("//span[text()='Соусы']/.."));
    private final SelenideElement fillingsTab = $(By.xpath("//span[text()='Начинки']/.."));
    private final By activeSection = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    private final SelenideElement loginButton = $(By.xpath("//button[text()='Войти в аккаунт']"));
    private final SelenideElement profileLink = $(By.xpath("//p[text()='Личный Кабинет']"));
    private final SelenideElement orderButton = $(By.xpath("//button[text()='Оформить заказ']"));

    @Step("Клик по кнопке Войти в аккаунт")
    public LoginPage clickLogin() {
        loginButton.click();
        return Selenide.page(LoginPage.class);
    }

    @Step("Клик по кнопке Личный Кабинет на главной странице")
    public LoginPage clickProfile() {
        profileLink.click();
        return Selenide.page(LoginPage.class);
    }

    @Step("Клик по кнопке Личный Кабинет после регистрации")
    public ProfilePage clickProfileAfterLogin() {
        profileLink.click();
        return Selenide.page(ProfilePage.class);
    }

    public void createBurgerTextIsShowed() {
        createBurgerText.shouldBe(visible);
    }

    public String getBunsTabText() {
        return bunsTab.getText();
    }

    public String getSaucesTabText() {
        return saucesTab.getText();
    }

    public String getFillingsTabText() {
        return fillingsTab.getText();
    }

    public void orderButtonIsShowed() {
        orderButton.shouldBe(visible);
    }

    @Step("Клик по разделу булки")
    public void showAvailableBuns() {
        bunsTab.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(activeSection, "Булки"));
    }

    @Step("Клик по разделу соусы")
    public void showAvailableSauces() {
        saucesTab.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(activeSection, "Соусы"));
    }

    @Step("Клик по разделу начинки")
    public void showAvailableFillings() {
        fillingsTab.click();
        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(activeSection, "Начинки"));
    }
}
