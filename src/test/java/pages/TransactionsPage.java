package pages;

import dto.models.csvModels.TransactionsCsvModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CsvHelper;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.AllureAttachment.attachFile;
import static utils.Constants.TIME_PATTERN_TIME_ON_PAGE;
import static utils.Constants.TIME_PATTERN_TIME_REPORT;

public class TransactionsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static CsvHelper<TransactionsCsvModel> csvHelper = new CsvHelper();

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    By transactionBy = By.cssSelector("tr.ng-scope");
    By dateInTransactionBy = By.xpath("//tr[@class='ng-scope']/td[@class='ng-binding'][1]");
    By amountTransactionBy = By.xpath("//tr[@class='ng-scope']/td[@class='ng-binding'][2]");
    By typeTransactionBy = By.xpath("//tr[@class='ng-scope']/td[@class='ng-binding'][3]");

    public TransactionsPage checkTransactions(int expectedSize) {
        List<WebElement> list = driver.findElements(transactionBy);
        assertEquals(expectedSize, list.size());
        return this;
    }

    public void createCsvReportFromPage() {
        var patternPage = DateTimeFormatter.ofPattern(TIME_PATTERN_TIME_ON_PAGE, Locale.ENGLISH);
        var patternReport = DateTimeFormatter.ofPattern(TIME_PATTERN_TIME_REPORT, new Locale("ru"));

        List<TransactionsCsvModel> csvList = new ArrayList<>();
        List<WebElement> list = driver.findElements(transactionBy);
        List<WebElement> elementsDate = driver.findElements(dateInTransactionBy);
        List<WebElement> elementsAmount = driver.findElements(amountTransactionBy);
        List<WebElement> elementsType = driver.findElements(typeTransactionBy);
        for (int i = 0; i < list.size(); i++) {
            LocalDateTime dateTime = LocalDateTime.parse(elementsDate.get(i).getText(), patternPage);

            csvList.add(new TransactionsCsvModel(dateTime.format(patternReport),
                    Long.parseLong(elementsAmount.get(i).getText()), elementsType.get(i).getText()));
        }
        File file = csvHelper.createFileFromListObjects(csvList, "pageReport");
        attachFile(file.getName(), "csv", "csv", file);
    }
}
