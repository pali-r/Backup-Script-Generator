package palir.TUI.Views;

import palir.Configuration.Config;
import palir.Configuration.ConfigReader;
import palir.TUI.ActionHandler;

public class EditLocalFoldersView implements View {

    @Override
    public void print() {
        Config config = ConfigReader.readConfig();
        System.out.println("----------------------------------------------");
        System.out.println("Backed up local folders:");
        System.out.println(config.getLocalFolders());
        System.out.println("d:[path] -> remove folder");
        System.out.println("a:[path] -> add folder");
        System.out.println("x-> go back");

        ActionHandler.handleAction(this);
    }

}
