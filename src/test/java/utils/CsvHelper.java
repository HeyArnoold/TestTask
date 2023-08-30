package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dto.models.TransactionsCsvModel;

import java.util.List;

public class CsvHelper<T> {

    public byte[] createBytesFromListObjects(List<T> object) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(TransactionsCsvModel.class)
                .withColumnSeparator(';')
                .withoutQuoteChar()
                .withHeader();
        ObjectWriter writer = mapper.writer(schema);
        try {
            return writer.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
