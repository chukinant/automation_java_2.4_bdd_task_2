package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class AccountPage {
    private final SelenideElement header = $x("//*[@data-test-id='dashboard']");
    private ElementsCollection cards = $$x("//*[@class='list__item']");

    public void verifyIsAccountPage() {
        header.shouldBe(Condition.visible).shouldHave(Condition.text("Личный кабинет"));
    }

    public AddToCardPage initiateTransferToCard(int index) {
        SelenideElement card = cards.get(index);
        SelenideElement button = card.$x(".//button[@data-test-id='action-deposit']");
        button.shouldBe(Condition.visible);
        button.shouldBe(Condition.enabled);
        button.click();
        return new AddToCardPage();
    }

    public void checkCardBalance(int cardToIndex, int expectedBalance) {
        SelenideElement card = cards.get(cardToIndex);
        card.shouldBe(Condition.visible).should(Condition.text("баланс: " + expectedBalance + " р."));
    }
}