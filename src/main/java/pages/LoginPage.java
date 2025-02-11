package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import user.UserModel;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginText = $(By.xpath("//h2[text()='Вход']"));
    private final SelenideElement emailInputField = $(By.xpath("//label[@class='input__placeholder text noselect text_type_main-default'][text()='Email']/parent::div/input"));
    private final SelenideElement passwordInputField = $(By.xpath("//label[@class='input__placeholder text noselect text_type_main-default'][text()='Пароль']/parent::div/input"));
    private final SelenideElement loginButton = $(By.xpath("//button[text()='Войти']"));
    private final SelenideElement registerLink = $(By.xpath("//a[text()='Зарегистрироваться']"));

    public void loginTextIsShowed() {
        loginText.shouldBe(visible);
    }

    @Step("Клик по кнопке Войти на странице входа")
    public void clickLogin() {
        loginButton.click();
    }

    @Step("Ввод логина")
    public void inputEmail(String email) {
        emailInputField.sendKeys(email);
    }

    @Step("Ввод пароля")
    public void inputPassword(String password) {
        passwordInputField.sendKeys(password);
    }

    @Step("Вход в личный кабинет")
    public MainPage login(UserModel userModel) {
        inputEmail(userModel.getEmail());
        inputPassword(userModel.getPassword());
        clickLogin();
        return Selenide.page(MainPage.class);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public RegisterPage clickRegister() {
        registerLink.shouldBe(visible).click();
        return Selenide.page(RegisterPage.class);
    }
}
