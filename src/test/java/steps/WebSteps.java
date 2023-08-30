package steps;

import dto.models.TransactionsCsvModel;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.LoginPage;
import pages.TransactionsPage;
import utils.CsvHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Constants.TIME_PATTERN_ON_PAGE;
import static utils.Constants.TIME_PATTERN_REPORT;

public class WebSteps {
    private WebDriver driver;

    public WebSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем страницу авторизации")
    public void openLoginPage() {
        new LoginPage(driver)
                .openPage();
    }

    @Step("Авторизуемся как покупатель пользователем {accountName}")
    public void loginAsCustomer(String accountName) {
        new LoginPage(driver)
                .logInAsCustomer(accountName);
    }

    @Step("Пополняем счет на сумму {amount}")
    public void makeDeposit(long amount) {
        new AccountPage(driver)
                .makeDeposit(amount);
    }

    @Step("Списываем со счета сумму {amount}")
    public void makeWithdrawal(long amount) {
        new AccountPage(driver)
                .makeWithdrawal(amount);
    }

    @Step("Проверяем что баланс равен {balance}")
    public void checkBalance(long balance) {
        assertEquals(String.valueOf(balance), new AccountPage(driver).getBalance());
    }

    @Step("Открываем страницу транзакций и проверяем кол-во транзакций равное {transactionsSize}")
    public void openTransactionsAndCheck(int transactionsSize) {
        new AccountPage(driver).clickTransactions();
        assertEquals(transactionsSize, new TransactionsPage(driver).getCountTransactionsOnPage());
    }

    @Attachment(type = "csv", fileExtension = "csv")
    @Step("Формируем csv файл о транзакциях на странице и прикрепляем к отчету")
    public byte[] createCsvReport() {
        List<TransactionsCsvModel> csvList = new ArrayList<>();

        TransactionsPage transactionsPage = new TransactionsPage(driver);
        List<String> listDates = transactionsPage.getTransactionDates();
        List<String> listAmounts = transactionsPage.getTransactionAmounts();
        List<String> listTypes = transactionsPage.getTransactionTypes();

        DateTimeFormatter patternPage = DateTimeFormatter.ofPattern(TIME_PATTERN_ON_PAGE, Locale.ENGLISH);
        DateTimeFormatter patternReport = DateTimeFormatter.ofPattern(TIME_PATTERN_REPORT, new Locale("ru"));

        for (int i = 0; i < listDates.size(); i++) {
            String dateTimeForReport = LocalDateTime.parse(listDates.get(i), patternPage).format(patternReport);
            long amount = Long.parseLong(listAmounts.get(i));
            csvList.add(new TransactionsCsvModel(dateTimeForReport, amount, listTypes.get(i)));
        }
        return new CsvHelper<TransactionsCsvModel>().createBytesFromListObjects(csvList);
    }
}
