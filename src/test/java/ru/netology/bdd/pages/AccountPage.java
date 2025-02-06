package ru.netology.bdd.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class AccountPage {
    private final SelenideElement header = $x("//*[@data-test-id='dashboard'][contains(text(),'Личный кабинет')]");
    private ElementsCollection cards = $$(".list__item div");

    public void verifyIsAccountPage() {
        header.shouldBe(Condition.visible);
    }

    public AddToCardPage initiateTransferToCard(int index) {
        $(".list__item div").shouldBe(Condition.visible, Duration.ofSeconds(10));
        SelenideElement card = cards.get(index);
        card.$x(".//button").click();
        return new AddToCardPage();
    }

    public void checkCardBalance(int cardToIndex, int expectedBalance) {
        SelenideElement card = cards.get(cardToIndex);
        card.shouldBe(Condition.visible).should(Condition.text("баланс: " + expectedBalance + " р."));
    }
}
