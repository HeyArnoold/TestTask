package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TransactionsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    public void getTransactions() {
        List<WebElement> list = driver.findElements(By.cssSelector("tr.ng-scope"));
        System.out.println(list.size());
    }
}
