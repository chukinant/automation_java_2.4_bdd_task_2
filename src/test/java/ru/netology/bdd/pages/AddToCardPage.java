package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class AddToCardPage {
    private final SelenideElement addToCardHeader = $x("//*[contains(text(),'Пополнение карты')]");
    private final SelenideElement cardFromField = $x("//*[@data-test-id='from']//input");
    private final SelenideElement amountField = $x("//*[@data-test-id='amount']//input");
    private final SelenideElement submitButton = $x("//button[@data-test-id='action-transfer']");

    public AddToCardPage() {
        addToCardHeader.shouldBe(Condition.visible);
    }

    public AccountPage moneyTransfer(String cardFrom, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardFromField.setValue(cardFrom);
        submitButton.click();
        return new AccountPage();
    }

    public void emptyAmountFieldTransfer(String cardFrom, int amount) {
        amountField.setValue(String.valueOf(amount));
        cardFromField.setValue(cardFrom);
        amountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        submitButton.click();
    }

    public void findErrorMsg() {
        boolean isErrorMessageVisible =
                $x("//*[contains(text(),'error')]").shouldBe(Condition.visible).exists() ||
                        $x("//*[contains(text(),'шибка')]").shouldBe(Condition.visible).exists();
    }
}
