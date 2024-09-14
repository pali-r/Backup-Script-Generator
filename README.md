# Backup Script Generator

Simple Java command line application for generating backup script. Suports both cloud storage and local storage (eg connected external drive). Using config file (json) to make changing the script easy.

## Running the application

Project is based on Maven, so to run if from command line, use `mvn clean package` (MacOS / Linux) and than run the app from target/appName.jar (adding maven-jar-plugin is likely needed for running it like this). App was developed in `Intellij`, so running it in there is a breeze.

When the script is generated, run with `./script`. Dont forget to change access control list to be able to run it (eg `chmod 744 script`).

### Dependencies

- for compilation you need `JDK` (I have used version 22, but older one should be fine) and `Maven`

- generated script itself uses these command line tools:
    - `fzf` - for selection menu in the script
    - `rclone` - backing up to cloud, needs to be set up before running the script
    - `rsync` - backing up locally

## Other notes

Changing config file path, generated script path and other defaults is possible in `Settings.java'.

I don't guarantee correctness of the script, so check it before running it.
