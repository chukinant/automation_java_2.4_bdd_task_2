package ru.netology.bdd.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement usernameField = $x("//*[@data-test-id='login']//input");
    private final SelenideElement passwordField = $x("//*[@data-test-id='password']//input");
    private final SelenideElement submitButton = $x("//button[@data-test-id='action-login']");

    public VerificationPage validLogin(String login, String password) {
        usernameField.setValue(login);
        passwordField.setValue(password);
        submitButton.click();
        return new VerificationPage();
    }
}
