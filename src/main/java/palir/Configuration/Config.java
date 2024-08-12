package palir.Configuration;

import java.util.List;

public class Config {
    private List<String> localFolders;
    private List<String> backupDestinationsCloud;
    private List<String> backupDestinationsLocal;
    private String rcloneFlags;
    private String rsyncFlags;

    public List<String> getLocalFolders() {
        return localFolders;
    }

    public void setLocalFolders(List<String> localFolders) {
        this.localFolders = localFolders;
    }

    public List<String> getBackupDestinationsCloud() {
        return backupDestinationsCloud;
    }

    public void setBackupDestinationsCloud(List<String> backupDestinationsCloud) {
        this.backupDestinationsCloud = backupDestinationsCloud;
    }

    public List<String> getBackupDestinationsLocal() {
        return backupDestinationsLocal;
    }

    public void setBackupDestinationsLocal(List<String> backupDestinationsLocal) {
        this.backupDestinationsLocal = backupDestinationsLocal;
    }

    public String getRcloneFlags() {
        return rcloneFlags;
    }

    public void setRcloneFlags(String rcloneFlags) {
        this.rcloneFlags = rcloneFlags;
    }

    public String getRsyncFlags() {
        return rsyncFlags;
    }

    public void setRsyncFlags(String rsyncFlags) {
        this.rsyncFlags = rsyncFlags;
    }
}
