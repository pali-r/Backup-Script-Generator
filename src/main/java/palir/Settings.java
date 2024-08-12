package palir;

import java.util.List;

public class Settings {
    private Settings(){}

    public static final String CONFIG_PATH = "configFile";
    public static final List<String> DEFAULT_LOCAL_FOLDERS = List.of("~/Documents");
    public static final String DEFAULT_RCLONE_FLAGS = "--delete-during --checksum --verbose --transfers 4 --checkers 8 --contimeout 60s --timeout 300s --retries 3 --low-level-retries 10 --stats 1s";
    public static final String DEFAULT_RSYNC_FLAGS = "-acP --delete";
    public static final String BACKUP_SCRIPT_PATH = "backupScript";
}
