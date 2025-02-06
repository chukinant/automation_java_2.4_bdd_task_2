package ru.netology.bdd.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import io.restassured.http.ContentType;
import ru.netology.bdd.data.DataHelper;
import ru.netology.bdd.pages.AccountPage;
import ru.netology.bdd.pages.AddToCardPage;
import ru.netology.bdd.pages.LoginPage;
import ru.netology.bdd.pages.VerificationPage;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

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

    @Given("пользователь залогинен с именем {string} и паролем {string}")
    public void userIsLoggedIn(String username, String password) {
        openLoginPage("http://localhost:9999");
        verificationPage = loginPage.validLogin(username,password);
        accountPage = verificationPage.validVerification("12345");
    }

    @When("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void moneyTransfer(int amount, String cardNumber, int cardToIndex) {
        addToCardPage = accountPage.initiateTransferToCard(cardToIndex - 1);
        addToCardPage.moneyTransfer(cardNumber, amount);
    }

    @Then("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void moneyAreTransferred(int cardToIndex, int balance) {
        accountPage.checkCardBalance(cardToIndex - 1, balance);
    }

//    @Given("на картах по {int} рублей")
//    public void resetCardBalances(int balance) {
//            given()
//                    .baseUri("http://localhost:9999")
//                    .header("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6InZhc3lhIn0.JmhHh8NXwfqktXSFbzkPohUb90gnc3yZ9tiXa0uUpRY")
//                    .contentType("application/json")
//                    .body("{ \"balance\": " + balance + " }")
//                    .when()
//                    .put("/api/cards/" + "92df3f1c-a033-48e6-8390-206f6b1f56c0" + "/balance")
//                    .then()
//                    .statusCode(200);
//            given()
//                    .baseUri("http://localhost:9999")
//                    .header("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6InZhc3lhIn0.JmhHh8NXwfqktXSFbzkPohUb90gnc3yZ9tiXa0uUpRY")
//                    .contentType("application/json")
//                    .body("{ \"balance\": " + balance + " }")
//                    .when()
//                    .put("/api/cards/" + "0f3f5c2a-249e-4c3d-8287-09f7a039391d" + "/balance")
//                    .then()
//                    .statusCode(200);
//        }
}
