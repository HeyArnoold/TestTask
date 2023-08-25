import org.junit.jupiter.api.Test;
import pages.LoginPage;
import utils.Helper;

import java.time.LocalDate;

public class TestsForTask extends TestBase{

    @Test
    public void test1() {
        LoginPage loginPage = new LoginPage(driver);
        long amount = Helper.getFibonacciValueByIndex(LocalDate.now().getDayOfMonth() + 1);

        loginPage.openPage()
                .logInAsCustomer("Harry Potter")
                .makeDeposit(amount)
                .checkBalance(amount)
                .makeWithdrawal(amount)
                .checkBalance(0)
                .clickTransactions()
                .getTransactions();
    }
}
