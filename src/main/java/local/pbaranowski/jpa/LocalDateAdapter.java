package local.pbaranowski.jpa;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("year");
        jsonWriter.value(localDate.getYear());
        jsonWriter.name("month");
        jsonWriter.value(localDate.getMonthValue());
        jsonWriter.name("day");
        jsonWriter.value(localDate.getDayOfMonth());
        jsonWriter.endObject();
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        int year = 0;
        int month = 0;
        int day = 0;
        String fieldname = null;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            JsonToken jsonToken = jsonReader.peek();
            if (jsonToken.equals(JsonToken.NAME)) {
                fieldname = jsonReader.nextName();
            }
            if ("year".equals(fieldname)) {
                jsonToken = jsonReader.peek();
                year = jsonReader.nextInt();
            }
            if ("month".equals(fieldname)) {
                jsonToken = jsonReader.peek();
                month = jsonReader.nextInt();
            }
            if ("day".equals(fieldname)) {
                jsonToken = jsonReader.peek();
                day = jsonReader.nextInt();
            }
        }
        jsonReader.endObject();
        return LocalDate.of(year, month, day);
    }
}
