package palir.Configuration;

import com.google.gson.Gson;
import palir.Settings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigWriter {

    public static void writeDefaultConfig() {
        Config config = new Config();
        config.setLocalFolders(Settings.DEFAULT_LOCAL_FOLDERS);
        config.setRcloneFlags(Settings.DEFAULT_RCLONE_FLAGS);
        config.setRsyncFlags(Settings.DEFAULT_RSYNC_FLAGS);

        writeConfig(config);
    }

    public static void writeConfig(Config config) {
        Gson gson = new Gson();
        String json = gson.toJson(config);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Settings.CONFIG_PATH));
            bufferedWriter.write(json);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
