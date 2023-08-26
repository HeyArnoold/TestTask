package dto.executors;

import com.google.gson.Gson;
import dto.models.csvModels.TransactionsCsvModel;
import dto.models.jsonModels.TransactionModel;
import dto.models.jsonModels.TransactionsModel;
import org.openqa.selenium.WebDriver;
import utils.CsvHelper;
import utils.LocalStorage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static utils.Constants.TIME_PATTERN_TIME_LOCAL_STORAGE;
import static utils.Constants.TIME_PATTERN_TIME_REPORT;

public class TransactionExecutor {
    private static LocalStorage localStorage;
    private static CsvHelper<TransactionsCsvModel> csvHelper = new CsvHelper();

    public static void createReportCsvLocalStorage(WebDriver driver) {
        localStorage = new LocalStorage(driver);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(TIME_PATTERN_TIME_LOCAL_STORAGE);
        DateTimeFormatter newDtf = DateTimeFormatter.ofPattern(TIME_PATTERN_TIME_REPORT, new Locale("ru"));

        List<TransactionsCsvModel> csvModels = new ArrayList<>();
        List<TransactionModel> models = new Gson()
                .fromJson(localStorage.getItemFromLocalStorage("Transaction"), TransactionsModel.class)
                .getSectionModel2().getAccount1004();

        for (TransactionModel model : models) {
            LocalDateTime dateTime = LocalDateTime.parse(model.getDate(), dtf);
            csvModels.add(new TransactionsCsvModel(dateTime.format(newDtf), model.getAmount(), model.getType()));
        }
        csvHelper.createFileFromListObjects(csvModels, "localStorage");
    }
}
