package dto.models.csvModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"Дата-времяТранзакции", "Сумма", "ТипТранзакции"})
public class TransactionsCsvModel {

    @JsonProperty("Дата-времяТранзакции")
    private String dateTime;
    @JsonProperty("Сумма")
    private long sum;
    @JsonProperty("ТипТранзакции")
    private String type;

    public TransactionsCsvModel(String dateTime, long sum, String type) {
        this.dateTime = dateTime;
        this.sum = sum;
        this.type = type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public long getSum() {
        return sum;
    }

    public String getType() {
        return type;
    }
}
