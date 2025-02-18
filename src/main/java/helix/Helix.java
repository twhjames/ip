package helix;

import java.io.IOException;
import java.nio.file.Paths;

import helix.command.Command;
import helix.command.CommandFactory;
import helix.enums.FilePath;
import helix.exception.HelixException;
import helix.storage.Storage;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * Initializes and runs the Helix application.
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
    private ConsoleUi consoleUi;

    /**
     * Constructs a new instance of the Helix application with the specified storage file path.
     *
     * <p>
     * This constructor initializes the user interface and storage components, determines
     * the resolved file path for the storage file, and attempts to load the task list from
     * the storage. If the task list cannot be loaded due to an I/O error, it initializes
     * an empty task list and displays an error message.
     * </p>
     *
     * @param filePath The file path to the storage file where tasks are saved and loaded.
     */
    public Helix(String filePath) {
        // Initialize ConsoleUi component
        consoleUi = new ConsoleUi();

        // Resolve the file path using the FilePath enum
        String storageFile = FilePath.STORAGE_FILE.getPath();
        String resolvedFilePath;
        if (System.getProperty("user.dir").contains("text-consoleUi-test")) {
            // Running from the `text-consoleUi-test` folder
            resolvedFilePath = Paths.get("..", storageFile).toAbsolutePath().normalize().toString();
        } else {
            // Running from the project root
            resolvedFilePath = Paths.get(storageFile).toAbsolutePath().normalize().toString();
        }

        // Load tasks from storage
        storage = new Storage(resolvedFilePath);
        try {
            taskList = new TaskList(storage);
            assert taskList != null : "taskList is initialization failed!";
        } catch (IOException e) {
            consoleUi.showLoadingStorageError();
        }
    }

    /**
     * Executes a user command and returns the response message.
     *
     * <p>
     * This method parses the user input, delegates the command execution to the appropriate
     * command class, and returns the result of the execution. If any exceptions are thrown
     * during parsing or execution, the method catches and returns an appropriate error message.
     * </p>
     *
     * @param userInput The user input string representing the command to be executed.
     * @return A string containing the result of the command execution or an error message.
     */
    public String executeCommand(String userInput) {
        try {
            // Parse command from input and instantiate the Command object
            Command command = CommandFactory.parseCommand(userInput);

            // Execute the command using polymorphism
            command.execute(taskList, consoleUi);

            // Return the last message displayed by the ConsoleUi
            return consoleUi.getLastMessage();
        } catch (HelixException e) {
            return e.getFormattedMessage();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }

    /**
     * Starts the Helix application.
     *
     * <p>
     * This method initializes and runs the application. It serves as the starting point
     * for executing the Helix program when run from the command line.
     * </p>
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("Helix Running.");
    }
}
