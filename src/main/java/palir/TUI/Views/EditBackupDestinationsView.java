package palir.TUI.Views;

import palir.Configuration.Config;
import palir.Configuration.ConfigReader;
import palir.TUI.ActionHandler;

public class EditBackupDestinationsView implements View {

    @Override
    public void print() {
        Config config = ConfigReader.readConfig();

        System.out.printf("""
                ----------------------------------------------
                Cloud:
                %s
                dc:[name]:[path] -> remove [C]loud destination
                ac:[name]:[path] -> add [C]loud destination (configured in rclone)
                
                Local:
                %s
                dp:[path] -> remove [P]ath to attached storage
                ap:[path] -> add [P]ath to attached storage
                
                x -> go back
                """,
                config.getBackupDestinationsCloud(),
                config.getBackupDestinationsLocal());

        ActionHandler.handleAction(this);
    }

}
