package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PasswordRecoveryPage {
    private final SelenideElement loginLink = $(By.xpath("//a[@class='Auth_link__1fOlj']"));

    @Step("Клик по кнопке Войти на странице восстановить пароль")
    public LoginPage clickLogin() {
        loginLink.click();
        return Selenide.page(LoginPage.class);
    }
}