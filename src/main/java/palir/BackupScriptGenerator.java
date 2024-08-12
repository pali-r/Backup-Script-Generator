package palir;

import palir.Configuration.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BackupScriptGenerator {

    private Config config;
    private StringBuilder stringBuilder;

    public BackupScriptGenerator(Config config) {
        this.config = config;
        stringBuilder = new StringBuilder();
    }

    public void generate() {
        generateHeader();
        generateCloudBackups();
        generateLocalBackups();
        generateFooter();

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Settings.BACKUP_SCRIPT_PATH));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
            System.out.println("generating script done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateFooter() {
        ArrayList<String> choices = new ArrayList<>();
        config.getBackupDestinationsCloud().forEach(destination -> {
            String destinationName = destination.split(":")[0];
            choices.add("backup to " + destinationName);
        });
        choices.add("backup locally");
        choices.add("exit");

        for (int i = 0; i < choices.size(); i++) {
            if (i == 0) {
                stringBuilder.append("choices=\"%s\"".formatted(choices.get(i)));
            } else {
                stringBuilder.append("\nchoices+=\"\\n%s\"".formatted(choices.get(i)));
            }
        }

        stringBuilder.append("\n\nchosen=$(echo \"$choices\" | fzf --reverse)");

        stringBuilder.append("\n\ncase \"$chosen\" in\n");
        choices.forEach(choice -> {
            if (choice.substring(choice.lastIndexOf(' ') + 1).equals("locally")) {
                stringBuilder.append("\"%s\") backup_to_local ;;\n".formatted(choice));
            } else if (choice.equals("exit")) {
                stringBuilder.append("\"%s\") echo \"bye bye\"; exit ;;\n".formatted(choice));
            } else {
                stringBuilder.append("\"%s\") backup_to_%s ;;\n".formatted(choice, choice.substring(choice.lastIndexOf(' ') + 1)));
            }
        });
        stringBuilder.append("esac");
    }

    private void generateLocalBackups() {
        if (config.getBackupDestinationsLocal() != null) {
            generateCheckFailed("LOCAL");
            stringBuilder.append("function backup_to_local () {\n");

            config.getBackupDestinationsLocal().forEach(destination -> {
                stringBuilder.append("\t_curDir=${PWD}\n")
                        .append("\tcd " + destination + "\n");

                config.getLocalFolders().forEach(folder -> {
                    stringBuilder.append("\trsync ")
                            .append(config.getRsyncFlags() + " ")
                            .append(folder + " ")
                            .append(folder.substring(folder.lastIndexOf("/") + 1))
                            .append("\n")
                            .append("\ttest_error_LOCAL\n");
                });

                stringBuilder.append("\tcd $_curDir\n\n");
            });

            stringBuilder.append("\techo \"backup to local DONE!!!\"\n")
                    .append("}\n")
                    .append("\n\n\n");
        }
    }


    private void generateCloudBackups() {
        if (config.getBackupDestinationsCloud() != null) {
            config.getBackupDestinationsCloud().forEach(destination -> {
                String[] destinations = destination.split(":");
                generateCheckFailed(destinations[0]);
                stringBuilder.append("function backup_to_" + destinations[0] + "() {\n");

                config.getLocalFolders().forEach(folder -> {
                    generateCloudBackupEntry(destination, destinations[0], folder);
                });

                stringBuilder.append("\techo \"backup to " + destinations[0] + " DONE\"\n")
                        .append("}\n")
                        .append("\n\n\n");
            });
        }
    }

    private void generateCloudBackupEntry(String destination, String checkSubName, String folder) {
        String folderNameInCloud = folder.substring(folder.lastIndexOf('/'));
        stringBuilder.append("\trclone sync " + config.getRcloneFlags())
                .append(" " + folder)
                .append(" " + destination + folderNameInCloud + " \n")
                .append("\ttest_error_" + checkSubName + "\n\n");
    }

    private void generateCheckFailed(String destination) {
        stringBuilder.append("function test_error_" + destination + "() {\n")
                .append("\tif [[ $? != 0 ]]\n")
                .append("\tthen\n")
                .append("\t\techo \"ERROR backup up to " + destination + "!!!\"\n")
                .append("\t\texit\n")
                .append("\tfi\n")
                .append("}\n")
                .append("\n\n\n");
    }

    private void generateHeader() {
        stringBuilder.append("# ----- BACKS UP: -----\n");
        config.getLocalFolders().forEach(x -> stringBuilder.append("# " + x + "\n"));
        stringBuilder.append("#\n# to cloud:\n");
        config.getBackupDestinationsCloud().forEach(x -> stringBuilder.append("# " + x + "\n"));
        stringBuilder.append("#\n# to local:\n");
        config.getBackupDestinationsLocal().forEach(x -> stringBuilder.append("# " + x + "\n"));

        stringBuilder.append("\n\n\n");
    }
}
