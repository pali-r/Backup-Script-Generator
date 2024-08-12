package palir.TUI;

import palir.BackupScriptGenerator;
import palir.Configuration.Config;
import palir.Configuration.ConfigReader;
import palir.Configuration.ConfigWriter;
import palir.TUI.Views.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActionHandler {

    public static void handleAction(View view) {
        System.out.print("\naction::: ");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();

        if (view instanceof MainView) {
            handleMainView(view, action);
        } else if (view instanceof EditLocalFoldersView) {
            handleEditLocalFoldersView(view, action);
        } else if (view instanceof EditBackupDestinationsView) {
            handleEditBackupDestinationsView(view, action);
        } else if (view instanceof ConfigureFlagsView) {
            handleConfigureFlagsView(view, action);
        }
    }

    private static void handleConfigureFlagsView(View view, String action) {
        if (!action.equals("x")) {
            Scanner scanner = new Scanner(System.in);
            Config config = ConfigReader.readConfig();
            switch (action) {
                case "c" -> {
                    System.out.println("enter new rclone flags:");
                    String newFLags = scanner.nextLine();
                    config.setRcloneFlags(newFLags);
                    ConfigWriter.writeConfig(config);
                    System.out.println("new rsync rclone set");
                }
                case "s" -> {
                    System.out.println("enter new rsync flags:");
                    String newFLags = scanner.nextLine();
                    config.setRsyncFlags(newFLags);
                    ConfigWriter.writeConfig(config);
                    System.out.println("new rsync flags set");
                }
                default -> {
                    System.out.println("wrong input!!");
                }
            }
            handleAction(view);
        }
    }

    private static void handleEditBackupDestinationsView(View view, String action) {
        if (!action.equals("x")) {
            String[] actions = action.split(":");
            Config config = ConfigReader.readConfig();
            switch (actions[0]) {
                case "dc" -> {
                    System.out.println("removing '" + actions[1] + ":" + actions[2] + "'");
                    List<String> backupDestinationsCloud = config.getBackupDestinationsCloud();
                    if (backupDestinationsCloud != null) {
                        backupDestinationsCloud.remove(actions[1] + ":" + actions[2]);
                        config.setBackupDestinationsCloud(backupDestinationsCloud);
                        ConfigWriter.writeConfig(config);
                    }
                }
                case "ac" -> {
                    System.out.println("adding '" + actions[1] + ":" + actions[2] + "'");
                    List<String> backupDestinationsCloud = config.getBackupDestinationsCloud();
                    if (backupDestinationsCloud == null) backupDestinationsCloud = new ArrayList<>();
                    backupDestinationsCloud.add(actions[1] + ":" + actions[2]);
                    config.setBackupDestinationsCloud(backupDestinationsCloud);
                    ConfigWriter.writeConfig(config);
                }
                case "dp" -> {
                    System.out.println("removing '" + actions[1] + "'");
                    List<String> backupDestinationsLocal = config.getBackupDestinationsLocal();
                    if (backupDestinationsLocal != null) {
                        backupDestinationsLocal.remove(actions[1]);
                        config.setBackupDestinationsLocal(backupDestinationsLocal);
                        ConfigWriter.writeConfig(config);
                    }
                }
                case "ap" -> {
                    System.out.println("adding '" + actions[1] + "'");
                    List<String> backupDestinationsLocal = config.getBackupDestinationsLocal();
                    if (backupDestinationsLocal == null) backupDestinationsLocal = new ArrayList<>();
                    backupDestinationsLocal.add(actions[1]);
                    config.setBackupDestinationsLocal(backupDestinationsLocal);
                    ConfigWriter.writeConfig(config);
                }
                default -> {
                    System.out.println("wrong input!!");
                }
            }
            handleAction(view);
        }
    }

    private static void handleEditLocalFoldersView(View view, String action) {
        if (!action.equals("x")) {
            String[] actions = action.split(":");
            Config config = ConfigReader.readConfig();
            switch (actions[0]) {
                case "d" -> {
                    System.out.println("removing '" + actions[1] + "'");
                    List<String> localFolders = config.getLocalFolders();
                    if (localFolders != null) {
                        localFolders.remove(actions[1]);
                        config.setLocalFolders(localFolders);
                        ConfigWriter.writeConfig(config);
                    }
                }
                case "a" -> {
                    System.out.println("adding '" + actions[1] + "'");
                    List<String> localFolders = config.getLocalFolders();
                    if (localFolders == null) localFolders = new ArrayList<>();
                    localFolders.add(actions[1]);
                    config.setLocalFolders(localFolders);
                    ConfigWriter.writeConfig(config);
                }
                default -> {
                    System.out.println("wrong input!!");
                }
            }
            handleAction(view);
        }
    }

    private static void handleMainView(View view, String action) {
        switch (action) {
            case "a" -> {
                View editLocalFoldersView = new EditLocalFoldersView();
                editLocalFoldersView.print();
            }
            case "e" -> {
                View editBackupDestinationsView = new EditBackupDestinationsView();
                editBackupDestinationsView.print();
            }
            case "c" -> {
                View configureFlagsView = new ConfigureFlagsView();
                configureFlagsView.print();
            }
            case "g" -> {
                BackupScriptGenerator backupScriptGenerator = new BackupScriptGenerator(ConfigReader.readConfig());
                backupScriptGenerator.generate();
                System.exit(0);
            }
            case "x" -> {
                System.out.println("exiting ...");
                System.exit(0);
            }
            default -> {
                System.out.println("wrong input!!");
                handleAction(view);
            }
        }
    }

}
