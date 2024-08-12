package palir.TUI.Views;

import palir.Configuration.Config;
import palir.Configuration.ConfigReader;
import palir.TUI.ActionHandler;

public class MainView implements View {

    @Override
    public void print() {
        Config config = ConfigReader.readConfig();

        System.out.println("----------------------------------------------");
        System.out.println(">> backed up local folders:");
        System.out.println(config.getLocalFolders());

        System.out.println("\n>> backup destinations:");
        System.out.println("Cloud:");
        System.out.println(config.getBackupDestinationsCloud());
        System.out.println("Local:");
        System.out.println(config.getBackupDestinationsLocal());

        System.out.println("\n>> rclone flags:");
        System.out.println(config.getRcloneFlags());

        System.out.println("\n>> rsync flags:");
        System.out.println(config.getRsyncFlags());

        System.out.println("\na -> edit local folders to backup");
        System.out.println("e -> edit backup destinations");
        System.out.println("c -> configure rclone / rsync flags");
        System.out.println("g -> generate backup script");
        System.out.println("x -> exit");

        ActionHandler.handleAction(this);
    }

}
