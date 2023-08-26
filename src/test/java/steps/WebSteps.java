package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.LoginPage;
import pages.TransactionsPage;

public class WebSteps {
    private WebDriver driver;

    public WebSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открываем страницу авторизации")
    public void openLoginPage() {
        new LoginPage(driver).openPage();
    }

    @Step("Авторизуемся как покупатель пользователем {accountName}")
    public void loginAsCustomer(String accountName) {
        new LoginPage(driver).logInAsCustomer(accountName);
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
        new AccountPage(driver)
                .checkBalance(balance);
    }

    @Step("Открываем страницу транзакций и проверяем кол-во транзакций равное {transactionsSize}")
    public void openTransactionsAndCheck(int transactionsSize) {
        new AccountPage(driver)
                .clickTransactions()
                .checkTransactions(transactionsSize);
    }

    @Step("Формируем csv файл о транзакциях на странице и прикрепляем к отчету")
    public void createCsvReport() {
        new TransactionsPage(driver)
                .createCsvReportFromPage();
    }
}
