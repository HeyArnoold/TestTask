package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    By transactionBy = By.cssSelector("tr.ng-scope");
    By dateInTransactionBy = By.xpath("//tr[@class='ng-scope']/td[@class='ng-binding'][1]");
    By amountTransactionBy = By.xpath("//tr[@class='ng-scope']/td[@class='ng-binding'][2]");
    By typeTransactionBy = By.xpath("//tr[@class='ng-scope']/td[@class='ng-binding'][3]");

    public int getCountTransactionsOnPage() {
        return driver.findElements(transactionBy).size();
    }

    public List<String> getTransactionDates() {
        wait.until(ExpectedConditions.numberOfElementsToBe(dateInTransactionBy, getCountTransactionsOnPage()));
        List<WebElement> elementsDate = driver.findElements(dateInTransactionBy);
        return elementsDate.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getTransactionAmounts() {
        wait.until(ExpectedConditions.numberOfElementsToBe(amountTransactionBy, getCountTransactionsOnPage()));
        List<WebElement> elementsAmounts = driver.findElements(amountTransactionBy);
        return elementsAmounts.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getTransactionTypes() {
        wait.until(ExpectedConditions.numberOfElementsToBe(typeTransactionBy, getCountTransactionsOnPage()));
        List<WebElement> elementsType = driver.findElements(typeTransactionBy);
        return elementsType.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
