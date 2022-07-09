package local.pbaranowski.jpa;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name(YEAR);
        jsonWriter.value(localDate.getYear());
        jsonWriter.name(MONTH);
        jsonWriter.value(localDate.getMonthValue());
        jsonWriter.name(DAY);
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
            jsonToken = jsonReader.peek();
            if (jsonToken.equals(JsonToken.NUMBER)) {
                switch (fieldname) {
                    case YEAR -> year = jsonReader.nextInt();
                    case MONTH -> month = jsonReader.nextInt();
                    case DAY -> day = jsonReader.nextInt();
                }
            }
        }
        jsonReader.endObject();
        return LocalDate.of(year, month, day);
    }
}
