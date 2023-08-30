import org.junit.jupiter.api.Test;
import steps.WebSteps;

import java.time.LocalDate;

import static utils.Helper.getFibonacciValueByIndex;

public class TestsForTask extends TestBase {

    @Test
    public void testWithSteps() throws InterruptedException {
        WebSteps webSteps = new WebSteps(driver);

        long amount = getFibonacciValueByIndex( LocalDate.now().getDayOfMonth() + 1);

        webSteps.openLoginPage();
        webSteps.loginAsCustomer("Harry Potter");
        webSteps.makeDeposit(amount);
        webSteps.makeWithdrawal(amount);
        webSteps.checkBalance(0);
        Thread.sleep(2000);
        webSteps.openTransactionsAndCheck(2);
        webSteps.createCsvReport();
    }
}
