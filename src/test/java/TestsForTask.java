import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoginPage;
import pages.TransactionsPage;
import steps.LocalStorageSteps;
import steps.WebSteps;
import utils.Helper;

import java.time.LocalDate;

import static dto.executors.TransactionExecutor.createReportCsvLocalStorage;

public class TestsForTask extends TestBase {

    @Test
    public void testWithPageObject() throws InterruptedException {
        long amount = Helper.getFibonacciValueByIndex(LocalDate.now().getDayOfMonth()  + 1);

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

    @Test
    public void testWithSteps() throws InterruptedException {
        WebSteps webSteps = new WebSteps(driver);
        LocalStorageSteps localStorageSteps = new LocalStorageSteps(driver);

        long amount = Helper.getFibonacciValueByIndex( LocalDate.now().getDayOfMonth() + 1);

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
