package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class AccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    private By depositBy = By.cssSelector("[ng-click='deposit()']");
    private By withdrawlBy = By.cssSelector("[ng-click='withdrawl()']");
    private By transactionsBy = By.cssSelector("[ng-click='transactions()']");
    private By amountBy = By.cssSelector("[ng-model='amount']");
    private By submitAmountBy = By.cssSelector("[type='submit']");
    private By balanceBy = By.xpath("//div[contains(text(),'Account Number')]/strong[2]");
    private By accountNameBy = By.className("fontBig");
    private By amountDescribeBy = By.cssSelector(".form-group label");

    public AccountPage makeDeposit(long amount) {
        driver.findElement(depositBy).click();
        wait.until(textToBe(submitAmountBy, "Deposit"));
        driver.manage().timeouts();
        driver.findElement(amountBy).sendKeys(String.valueOf(amount));
        driver.findElement(submitAmountBy).click();
        return this;
    }

    public AccountPage makeWithdrawal(long amount) {
        driver.findElement(withdrawlBy).click();
        wait.until(textToBe(submitAmountBy, "Withdraw"));
        driver.findElement(amountBy).sendKeys(String.valueOf(amount));
        driver.findElement(submitAmountBy).click();
        return this;
    }

    public TransactionsPage clickTransactions() {
        driver.findElement(transactionsBy).click();
        return new TransactionsPage(driver);
    }

    public String getBalance() {
        return driver.findElement(balanceBy).getText();
    }
}
