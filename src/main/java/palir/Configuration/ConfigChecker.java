package palir.Configuration;

import palir.Settings;

import java.io.File;

public class ConfigChecker {

    public static boolean configFileExists() {
        File file = new File(Settings.CONFIG_PATH);
        return file.exists();
    }

}
