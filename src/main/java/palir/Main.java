package palir;

import palir.Configuration.ConfigChecker;
import palir.Configuration.ConfigWriter;
import palir.TUI.Views.MainView;
import palir.TUI.Views.View;


public class Main {

    public static void main(String[] args) {
        if (!ConfigChecker.configFileExists()) {
            ConfigWriter.writeDefaultConfig();
        }

        while (true) {
            View mainView = new MainView();
            mainView.print();
        }

    }
}