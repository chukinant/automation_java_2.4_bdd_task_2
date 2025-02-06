//package ru.netology.bdd.test;
//
//import com.codeborne.selenide.Selenide;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.netology.page_objects.data.DataHelper;
//import ru.netology.page_objects.pages.AccountPage;
//import ru.netology.page_objects.pages.LoginPage;
//
//import static ru.netology.page_objects.data.DataHelper.*;
//
//public class MoneyTransferTest {
//    AccountPage accountPage;
//    CardInfo firstCardInfo;
//    CardInfo secondCardInfo;
//    int firstCardBalance;
//    int secondCardBalance;
//
//    @BeforeEach
//    void setUp() {
//        Selenide.open("http://localhost:9999");
//        var validUser = getAuthInfo();
//        var verificationCode = getVerificationCode();
//        var loginPage = new LoginPage();
//        var verificationPage = loginPage.validLogin(validUser);
//        accountPage = verificationPage.validVerification(verificationCode);
//        firstCardInfo = DataHelper.getFistCardInfo();
//        secondCardInfo = DataHelper.getSecondCardInfo();
//        firstCardBalance = accountPage.getCardBalance(firstCardInfo.getId());
//        secondCardBalance = accountPage.getCardBalance(secondCardInfo.getId());
//    }
//
//    @Test
//    void shouldTransferFromFirstToSecondCard() {
//        int amount = generateAmount(firstCardBalance);
//        int expectedFirstCardBalance = firstCardBalance - amount;
//        int expectedSecondCardBalance = secondCardBalance + amount;
//
//        var addToCardPage = accountPage.initiateTransferToCard(secondCardInfo.getId());
//        var accountPage = addToCardPage.moneyTransfer(firstCardInfo.getNumber(), amount);
//
//        accountPage.checkCardBalance(firstCardInfo.getId(), expectedFirstCardBalance);
//        accountPage.checkCardBalance(secondCardInfo.getId(), expectedSecondCardBalance);
//    }
//
//    @Test
//    void shouldTransferFromSecondToFirstCard() {
//        int amount = generateAmount(secondCardBalance);
//        int expectedFirstCardBalance = firstCardBalance + amount;
//        int expectedSecondCardBalance = secondCardBalance - amount;
//
//        var addToCardPage = accountPage.initiateTransferToCard(firstCardInfo.getId());
//        var accountPage = addToCardPage.moneyTransfer(secondCardInfo.getNumber(), amount);
//
//        accountPage.checkCardBalance(firstCardInfo.getId(), expectedFirstCardBalance);
//        accountPage.checkCardBalance(secondCardInfo.getId(), expectedSecondCardBalance);
//    }
//
//    @Test
//    void shouldNotTransferFromFirstToFirstCard() {
//        int amount = generateAmount(firstCardBalance);
//
//        var addToCardPage = accountPage.initiateTransferToCard(firstCardInfo.getId());
//        addToCardPage.moneyTransfer(firstCardInfo.getNumber(), amount);
//
//        addToCardPage.findErrorMsg();
//    }
//
//    @Test
//    void shouldNotTransferFromSecondToSecondCard() {
//        int amount = generateAmount(secondCardBalance);
//
//        var addToCardPage = accountPage.initiateTransferToCard(secondCardInfo.getId());
//        addToCardPage.moneyTransfer(secondCardInfo.getNumber(), amount);
//
//        addToCardPage.findErrorMsg();
//    }
//
//    @Test
//    void shouldNotTransferNotAvailableAmount() {
//        int amount = secondCardBalance + 1;
//
//        var addToCardPage = accountPage.initiateTransferToCard(firstCardInfo.getId());
//        addToCardPage.moneyTransfer(secondCardInfo.getNumber(), amount);
//
//        addToCardPage.findErrorMsg();
//    }
//
//    @Test
//    void shouldNotTransferEmptyAmountField() {
//        int amount = 1;
//        var addToCardPage = accountPage.initiateTransferToCard(firstCardInfo.getId());
//        addToCardPage.emptyAmountFieldTransfer(secondCardInfo.getNumber(), amount);
//
//        addToCardPage.findErrorMsg();
//    }
//}
