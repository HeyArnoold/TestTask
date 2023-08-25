package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    By transactionBy = By.cssSelector("tr.ng-scope");

    public TransactionsPage checkTransactions(int expectedSize) {
        List<WebElement> list = driver.findElements(transactionBy);
        assertEquals(expectedSize, list.size());
        return this;
    }
}
