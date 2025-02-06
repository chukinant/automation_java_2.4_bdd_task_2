package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {
    private final SelenideElement codeInput = $x("//*[@data-test-id='code']//input");
    private final SelenideElement submitButton = $x("//button[@data-test-id='action-verify']");
    private SelenideElement errorPopup = $(".notification__content");

    public void verificationPage() {
        codeInput.shouldBe(Condition.visible);
    }

    public AccountPage validVerification(String verificationCode) {
        codeInput.setValue(verificationCode);
        submitButton.click();
        return new AccountPage();
    }

    public void verifyCodeIsInvalid() {
        errorPopup.shouldHave(text("Неверно указан код! Попробуйте ещё раз."));
    }
}
