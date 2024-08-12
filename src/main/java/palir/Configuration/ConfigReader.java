package palir.Configuration;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import palir.Settings;

import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {

    public static Config readConfig() {
        try {
            Gson gson = new Gson();

            JsonReader jsonReader = new JsonReader(new FileReader(Settings.CONFIG_PATH));

            Config config = gson.fromJson(jsonReader, Config.class);

            jsonReader.close();

            return config;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
