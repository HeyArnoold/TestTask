import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoginPage;
import pages.TransactionsPage;
import utils.Helper;

import java.time.LocalDate;

import static dto.executors.TransactionExecutor.createReportCsvLocalStorage;

public class TestsForTask extends TestBase {

    @Test
    public void test1() throws InterruptedException {
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        long amount = Helper.getFibonacciValueByIndex( dayOfMonth + 1);

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
}
