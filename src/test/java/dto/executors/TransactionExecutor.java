package dto.executors;

import com.google.gson.Gson;
import dto.common.LocalStorage;
import dto.models.TransactionModel;
import dto.models.TransactionsModel;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionExecutor {
    private static LocalStorage localStorage;

    public static void assertTransactionsAdded(WebDriver driver) {
        localStorage = new LocalStorage(driver);
        assertTrue(localStorage.isItemPresentInLocalStorage("Transaction"));
    }

    public static List<TransactionModel> getTransactionsFromLS(WebDriver driver) {
        localStorage = new LocalStorage(driver);
        return new Gson().fromJson(localStorage.getItemFromLocalStorage("Transaction"), TransactionsModel.class)
                .getSectionModel1().getAccount1004();
    }
}
