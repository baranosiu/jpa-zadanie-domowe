package local.pbaranowski.jpa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class TestGson {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class,new LocalDateAdapter())
                .create();
        LocalDate localDate = LocalDate.now();
        String json = gson.toJson(localDate).toString();
        json = json.replace("2022","2035");
        LocalDate localDateFromJson = gson.fromJson(json,LocalDate.class);
        System.out.println(json + System.lineSeparator() + localDateFromJson.toString());
    }
}
