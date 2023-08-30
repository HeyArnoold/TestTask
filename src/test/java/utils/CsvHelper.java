package utils;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dto.models.TransactionsCsvModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvHelper <T>{

    public File createFileFromListObjects(List<T> object, String fileName) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(TransactionsCsvModel.class)
                .withColumnSeparator(';')
                .withoutQuoteChar()
                .withHeader();
        ObjectWriter writer = mapper.writer(schema);
        File file = new File(String.format("%s.csv", fileName));
        try {
            writer.writeValue(new FileWriter(file, StandardCharsets.UTF_8), object);
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
