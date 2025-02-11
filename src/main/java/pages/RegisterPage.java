package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import user.UserModel;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    private final SelenideElement nameInputField = $(By.xpath("//label[text()='Имя']//following-sibling::input"));
    private final SelenideElement emailInputField = $(By.xpath("//label[text()='Email']//following-sibling::input"));
    private final SelenideElement passwordInputField = $(By.xpath("//input[@type='password']"));
    private final SelenideElement registerButton = $(By.xpath("//button[text()='Зарегистрироваться']"));
    private final SelenideElement incorrectPasswordWarning = $(By.xpath("//p[text()='Некорректный пароль']"));
    private final SelenideElement loginLink = $(By.xpath("//a[text()='Войти']"));

    @Step("Ввод имени")
    public void inputName(String name) {
        nameInputField.sendKeys(name);
    }

    @Step("Ввод почты")
    public void inputEmail(String email) {
        emailInputField.sendKeys(email);
    }

    @Step("Ввод пароля")
    public void inputPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    @Step("Клик по кнопке регистрация")
    public void clickRegister() {
        registerButton.click();
    }

    @Step("Регистрация юзера")
    public <T> T registerUser(UserModel userModel, Class<T> nextPageClass) {
        inputName(userModel.getName());
        inputEmail(userModel.getEmail());
        inputPassword(userModel.getPassword());
        clickRegister();
        return Selenide.page(nextPageClass);
    }

    @Step("Клик по кнопке Войти на странице регистрации")
    public LoginPage clickLogin() {
        loginLink.click();
        return Selenide.page(LoginPage.class);
    }

    public void incorrectPasswordIsShowed() {
        incorrectPasswordWarning.shouldBe(visible);
    }
}
