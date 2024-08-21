package palir.TUI.Views;

import palir.Configuration.Config;
import palir.Configuration.ConfigReader;
import palir.TUI.ActionHandler;

public class MainView implements View {

    @Override
    public void print() {
        Config config = ConfigReader.readConfig();

        System.out.printf("""
                ----------------------------------------------
                >> backed up local folders:
                %s
                
                >> backup destinations:
                Cloud:
                %s
                Local:
                %s
                
                >> rclone flags:
                %s
                
                >> rsync flags:
                %s
                
                a -> edit local folders to backup
                e -> edit backup destinations
                c -> configure rclone / rsync flags
                
                g -> generate backup script
                x -> exit
                """,
                config.getLocalFolders(),
                config.getBackupDestinationsCloud(),
                config.getBackupDestinationsLocal(),
                config.getRcloneFlags(),
                config.getRsyncFlags());

        ActionHandler.handleAction(this);
    }

}
