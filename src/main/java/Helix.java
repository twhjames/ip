import command.Command;
import command.CommandFactory;
import enums.FilePath;
import enums.ExecutionStatus;
import exception.HelixException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The main class for the Helix personal assistant application.
 *
 * <p>
 * This class serves as the entry point for the Helix application, managing the initialization
 * of components such as storage, the task list, and the user interface. It also handles the
 * main program loop, which processes user commands and executes them using polymorphism.
 * </p>
 */
public class Helix {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new instance of the Helix application with the specified storage file path.
     *
     * @param filePath the file path to the storage file where tasks are saved and loaded
     */
    public Helix(String filePath) {
        // create Ui component
        ui = new Ui();

        // resolve the file path using the enum
        String storageFile = FilePath.STORAGE_FILE.getPath();
        String resolvedFilePath;
        if (System.getProperty("user.dir").contains("text-ui-test")) {
            // running from `text-ui-test` folder
            resolvedFilePath = Paths.get("..", storageFile).toAbsolutePath().normalize().toString();
        } else {
            // running from project root
            resolvedFilePath = Paths.get(storageFile).toAbsolutePath().normalize().toString();
        }

        // load task from storage
        storage = new Storage(resolvedFilePath);
        try {
            taskList = new TaskList(storage);
        } catch (IOException e) {
            ui.showLoadingStorageError();
        }
    }

    /**
     * Runs the main program loop for the Helix application.
     * <p>
     * This method initializes the user interface, handles user input, and delegates command
     * execution to the appropriate components. It continues to run until the exit command
     * is issued.
     * </p>
     */
    public void run() {
        ui.showWelcome();
        ExecutionStatus isExit = ExecutionStatus.CONTINUE;
        Scanner sc = new Scanner(System.in);

        while (isExit != ExecutionStatus.EXIT) {
            try {
                // parse command from input and instantiate Command object
                String userInput = ui.readCommand(sc);
                Command command = CommandFactory.parseCommand(userInput);

                // execute command using polymorphism
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (HelixException e) {
                ui.showError(e.getFormattedMessage());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }

        // clean up resources
        sc.close();
    }

    /**
     * The main entry point of the Helix application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        new Helix("data/helix_tasklist.txt").run();
    }
}
