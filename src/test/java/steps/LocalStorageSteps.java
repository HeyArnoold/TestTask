package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static dto.executors.TransactionExecutor.createReportCsvLocalStorage;

public class LocalStorageSteps {
    WebDriver webDriver;

    public LocalStorageSteps(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Формируем csv файл о транзакциях и прикрепляем к отчету")
    public void createCsvReport() {
        createReportCsvLocalStorage(webDriver);
    }
}
