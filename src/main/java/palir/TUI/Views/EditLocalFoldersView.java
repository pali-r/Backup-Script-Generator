package palir.TUI.Views;

import palir.Configuration.Config;
import palir.Configuration.ConfigReader;
import palir.TUI.ActionHandler;

public class EditLocalFoldersView implements View {

    @Override
    public void print() {
        Config config = ConfigReader.readConfig();

        System.out.printf("""
                ----------------------------------------------
                Backed up local folders:
                %s
                d:[path] -> remove folder
                a:[path] -> add folder
                
                x-> go back
                """,
                config.getLocalFolders());

        ActionHandler.handleAction(this);
    }

}
