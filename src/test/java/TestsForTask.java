import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.LoginPage;
import utils.Helper;

import java.time.LocalDate;

import static dto.executors.TransactionExecutor.assertTransactionsAdded;

public class TestsForTask extends TestBase {

    @Test
    public void test1() {
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        long amount = Helper.getFibonacciValueByIndex( dayOfMonth+ 1);

        LoginPage loginPage = new LoginPage(driver).openPage();

        AccountPage accountPage = loginPage
                .logInAsCustomer("Harry Potter")
                .makeDeposit(amount)
                .checkBalance(amount)
                .makeWithdrawal(amount)
                .checkBalance(0);

        assertTransactionsAdded(driver);

        accountPage
                .clickTransactions()
                .checkTransactions(2);
    }
}
