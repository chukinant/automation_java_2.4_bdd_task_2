package ru.netology.bdd.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.bdd.pages.AccountPage;
import ru.netology.bdd.pages.AddToCardPage;
import ru.netology.bdd.pages.LoginPage;
import ru.netology.bdd.pages.VerificationPage;

public class Steps {
    private static LoginPage loginPage;
    private static VerificationPage verificationPage;
    private static AccountPage accountPage;
    private static AddToCardPage addToCardPage;

    @Пусть("открыта страница с формой авторизации {string}")
    public void openLoginPage(String url) {
        Selenide.open(url);
        loginPage = new LoginPage();
    }

    @Когда("пользователь пытается авторизоваться с именем {string} и паролем {string}")
    public void login(String login, String password) {
        verificationPage = loginPage.validLogin(login, password);
    }

    @И("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(String verificationCode) {
        accountPage = verificationPage.validVerification(verificationCode);
    }

    @Тогда("происходит успешная авторизация и пользователь попадает на страницу 'Личный кабинет'")
    public void verifyAccountPage() {
        accountPage.verifyIsAccountPage();
    }

    @Тогда("появляется ошибка о неверном коде проверки")
    public void verifyCodeIsInvalid() {
        verificationPage.verifyCodeIsInvalid();
    }

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void userIsLoggedIn(String username, String password) {
        openLoginPage("http://localhost:9999");
        verificationPage = loginPage.validLogin(username,password);
        accountPage = verificationPage.validVerification("12345");
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void moneyTransfer(int amount, String cardNumber, int cardToIndex) {
        addToCardPage = accountPage.initiateTransferToCard(cardToIndex - 1);
        addToCardPage.moneyTransfer(cardNumber, amount);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void moneyAreTransferred(int cardToIndex, int balance) {
        accountPage.checkCardBalance(cardToIndex - 1, balance);
    }
}
