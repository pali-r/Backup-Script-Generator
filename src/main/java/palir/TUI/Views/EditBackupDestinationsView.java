package palir.TUI.Views;

import palir.Configuration.Config;
import palir.Configuration.ConfigReader;
import palir.TUI.ActionHandler;

public class EditBackupDestinationsView implements View {

    @Override
    public void print() {
        Config config = ConfigReader.readConfig();
        System.out.println("----------------------------------------------");
        System.out.println("Cloud:");
        System.out.println(config.getBackupDestinationsCloud());
        System.out.println("dc:[name]:[path] -> remove [C]loud destination");
        System.out.println("ac:[name]:[path] -> add [C]loud destination (configured in rclone)\n");

        System.out.println("Local:");
        System.out.println(config.getBackupDestinationsLocal());
        System.out.println("dp:[path] -> remove [P]ath to attached storage");
        System.out.println("ap:[path] -> add [P]ath to attached storage");
        System.out.println("x -> go back");

        ActionHandler.handleAction(this);
    }

}
