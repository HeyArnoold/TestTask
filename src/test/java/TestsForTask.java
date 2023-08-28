import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoginPage;
import pages.TransactionsPage;
import steps.LocalStorageSteps;
import steps.WebSteps;

import java.time.LocalDate;

import static dto.executors.TransactionExecutor.createReportCsvLocalStorage;
import static utils.Helper.getFibonacciValueByIndex;

@Tags({@Tag("Web"), @Tag("UI")})
public class TestsForTask extends TestBase {

    @Test
    @DisplayName("Тест с PageObject")
    @Tags({@Tag("Major"), @Tag("Medium")})
    @Feature("Аккаунт клиента")
    @Story("Аккаунт клиента. Пополнение, снятие, история транзакций")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "Страница авторизации", url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login")
    public void testWithPageObject() throws InterruptedException {
        long amount = getFibonacciValueByIndex(LocalDate.now().getDayOfMonth()  + 1);

        LoginPage loginPage = new LoginPage(driver).openPage();

        AccountPage accountPage = loginPage
                .logInAsCustomer("Harry Potter")
                .makeDeposit(amount)
                .checkBalance(amount)
                .makeWithdrawal(amount)
                .checkBalance(0);

        Thread.sleep(2000);

        TransactionsPage transactionsPage = accountPage
                .clickTransactions()
                .checkTransactions(2);

        transactionsPage.createCsvReportFromPage();
        createReportCsvLocalStorage(driver);
    }

    @DisplayName("Тест со степами")
    @Test
    public void testWithSteps() throws InterruptedException {
        WebSteps webSteps = new WebSteps(driver);
        LocalStorageSteps localStorageSteps = new LocalStorageSteps(driver);

        long amount = getFibonacciValueByIndex( LocalDate.now().getDayOfMonth() + 1);

        webSteps.openLoginPage();
        webSteps.loginAsCustomer("Harry Potter");
        webSteps.makeDeposit(amount);
        webSteps.makeWithdrawal(amount);
        webSteps.checkBalance(0);
        Thread.sleep(2000);
        webSteps.openTransactionsAndCheck(2);
        webSteps.createCsvReport();
        localStorageSteps.createCsvReport();
    }
}
