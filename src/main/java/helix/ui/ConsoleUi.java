package helix.ui;

import java.util.List;
import java.util.Scanner;

import helix.enums.OutputSymbol;
import helix.enums.TaskType;
import helix.task.Task;

/**
 * Handles all user interaction for the helix.Helix application.
 *
 * <p>
 * This class provides methods to display messages to the user, read user input, and
 * format output for various operations, such as adding, removing, or listing tasks.
 * It centralizes all console-based interactions to maintain a clear separation of concerns.
 * </p>
 */
public class ConsoleUi {

    private String lastMessage = "";

    private final String LOGO = """
           ╔════════════════════════════════════════════════════════╗
           ║                                                        ║
           ║             _   _  _____  _      ___ __  __            ║
           ║            | | | || ____|| |    |_ _|\\ \\/ /            ║
           ║            | |_| ||  _|  | |     | |  \\  /             ║
           ║            |  _  || |___ | |___  | |  /  \\             ║
           ║            |_| |_||_____||_____||___|/_/\\_\\            ║
           ║                                                        ║
           ╚════════════════════════════════════════════════════════╝
           """;

    // Output symbols for formatting
    private final String HELIX_SYMBOL = OutputSymbol.HELIX.getSymbol();
    private final String USER_SYMBOL = OutputSymbol.USER.getSymbol();
    private final String WARNING_SYMBOL = OutputSymbol.WARNING.getSymbol();
    private final String FOLDER_SYMBOL = OutputSymbol.FOLDER.getSymbol();
    private final String CLIPBOARD_SYMBOL = OutputSymbol.CLIPBOARD.getSymbol();
    private final String NOTE_SYMBOL = OutputSymbol.NOTE.getSymbol();
    private final String CALENDAR_SYMBOL = OutputSymbol.CALENDAR.getSymbol();
    private final String CLOCK_SYMBOL = OutputSymbol.CLOCK.getSymbol();
    private final String HANDWAVE_SYMBOL = OutputSymbol.HANDWAVE.getSymbol();
    private final String SPARKLE_SYMBOL = OutputSymbol.SPARKLE.getSymbol();
    private final String REMOVED_SYMBOL = OutputSymbol.BIN.getSymbol();
    private final String TYPE_SYMBOL = OutputSymbol.CLIPBOARD.getSymbol();
    private final String DESCRIPTION_SYMBOL = OutputSymbol.NOTE.getSymbol();
    private final String COMPLETED_SYMBOL = OutputSymbol.WRENCH.getSymbol();
    private final String CHECK_SYMBOL = OutputSymbol.CHECK.getSymbol();
    private final String CROSS_SYMBOL = OutputSymbol.CROSS.getSymbol();

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        // print introduction message
        System.out.println(LOGO);
        System.out.println(
                HELIX_SYMBOL + " [Helix] : Hello! I'm your personal assistant, "
        );
        System.out.println(
                HELIX_SYMBOL + " [Helix] : What can I do for you today?\n"
        );
    }

    /**
     * Displays the exit message when the application terminates.
     */
    public void showExit() {
        System.out.println(
                HELIX_SYMBOL
                        + " [Helix] : Bye! Hope to see you soon! "
                        + HANDWAVE_SYMBOL
        );
        System.out.println("=========================================================");
        System.out.println(
                SPARKLE_SYMBOL
                        + " Thank you for using  Have a great day! "
                        + SPARKLE_SYMBOL
        );
        System.out.println("=========================================================");
        lastMessage = HANDWAVE_SYMBOL + "Bye! Hope to see you soon!";
    }

    /**
     * Reads a command input from the user.
     *
     * @param sc the {@link Scanner} object for reading user input
     * @return the user input as a {@link String}
     */
    public String readCommand(Scanner sc) {
        // retrieve user input
        System.out.print(USER_SYMBOL + " [You]   : ");
        return sc.nextLine();
    }

    /**
     * Displays a decorative line for formatting.
     */
    public void showLine() {
        System.out.println("════════════════════════════════════");
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        lastMessage = String.format("%s Error message - %s", WARNING_SYMBOL, message);
        System.out.println(WARNING_SYMBOL + " [Helix] : " + message + "\n");
    }

    /**
     * Displays an error message indicating a failure to load tasks from storage.
     */
    public void showLoadingStorageError() {
        lastMessage = String.format("%s Failed to load tasks. Starting with an empty list.", WARNING_SYMBOL);
        System.out.println(
                WARNING_SYMBOL + " [Helix] : Failed to load tasks. Starting with an empty list."
        );
    }

    /**
     * Displays an error message indicating a failure to save tasks to storage.
     *
     * @param message the detailed error message
     */
    public void showSavingStorageError(String message) {
        lastMessage = String.format("%s Failed to save tasks: %s", WARNING_SYMBOL, message);
        System.out.println(
                WARNING_SYMBOL + " [Helix] : Failed to save tasks: " + message
        );
    }

    /**
     * Displays a message indicating that a task was added successfully.
     *
     * @param task the {@link Task} that was added
     * @param taskCount the total number of tasks in the list after the addition
     */
    public void showTaskAdded(Task task, int taskCount) {
        assert task != null : "Task should not be null!";

        TaskType taskType = task.getTaskType();
        assert taskType != null : "Task type should not be null!";

        String taskDescription = task.getDescription();
        String taskDetails = task.getTaskDetails();

        showLine();
        System.out.println(FOLDER_SYMBOL + "  Task Added!");
        showLine();

        System.out.println("  " + CLIPBOARD_SYMBOL + " Type: " + taskType.name());
        System.out.println("  " + NOTE_SYMBOL + " Description: " + taskDescription);

        lastMessage = FOLDER_SYMBOL + "  Task Added!\n"
                + "  " + CLIPBOARD_SYMBOL + " Type: " + taskType.name() + "\n"
                + "  " + NOTE_SYMBOL + " Description: " + taskDescription + "\n";

        if (taskType == TaskType.DEADLINE) {
            System.out.println("  " + CALENDAR_SYMBOL + " Due: " + taskDetails);
            lastMessage += "  " + CALENDAR_SYMBOL + " Due: " + taskDetails;
        } else if (taskType == TaskType.EVENT) {
            String[] parts = taskDetails.split(" - ");
            String from = parts[0];
            String to = parts[1];
            System.out.println("  " + CLOCK_SYMBOL + " From: " + from + "\n  " + CLOCK_SYMBOL + " To: " + to);
            lastMessage += "  " + CLOCK_SYMBOL + " From: " + parts[0] + "\n  " + CLOCK_SYMBOL + " To: " + parts[1] + "\n";
        }
        System.out.println("\nYou now have " + taskCount + " task(s) in your list.");
        lastMessage += "\nYou now have " + taskCount + " task(s) in your list.\n";
        showLine();
        System.out.println();
    }

    /**
     * Displays a message indicating that a task was removed successfully.
     *
     * @param task the {@link Task} that was removed
     * @param taskCount the total number of tasks in the list after the removal
     */
    public void showTaskRemoved(Task task, int taskCount) {
        assert task != null : "Task should not be null!";

        TaskType taskType = task.getTaskType();
        assert taskType != null : "Task type should not be null!";

        String taskStatus = task.getTaskStatus().name();
        String taskDescription = task.getDescription();
        String taskDetails = task.getTaskDetails();

        showLine();
        System.out.println(REMOVED_SYMBOL + "  Task Removed!");
        lastMessage = REMOVED_SYMBOL + "  Task Removed!\n";

        showLine();
        System.out.println("  " + TYPE_SYMBOL + " Type: " + taskType.name());
        System.out.println("  " + DESCRIPTION_SYMBOL + " Description: " + taskDescription);
        lastMessage += "  " + TYPE_SYMBOL + " Type: " + taskType.name() + "\n"
                + "  " + DESCRIPTION_SYMBOL + " Description: " + taskDescription + "\n";

        if (taskType == TaskType.DEADLINE) {
            System.out.println("  " + CALENDAR_SYMBOL + " Due: " + taskDetails);
            lastMessage += "  " + CALENDAR_SYMBOL + " Due: " + taskDetails;
        } else if (taskType == TaskType.EVENT) {
            String[] parts = taskDetails.split(" - ");
            String from = parts[0];
            String to = parts[1];
            System.out.println("  " + CLOCK_SYMBOL + " From: " + from + "\n  " + CLOCK_SYMBOL + " To: " + to);
            lastMessage += "  " + CLOCK_SYMBOL + " From: " + from + "\n  " + CLOCK_SYMBOL + " To: " + to;
        }
        System.out.println("  " + COMPLETED_SYMBOL + " Task Status: " + taskStatus);
        System.out.println("\nYou now have " + taskCount + " task(s) in your list.");
        lastMessage += "  " + COMPLETED_SYMBOL + " Task Status: " + taskStatus
                + "\n\nYou now have " + taskCount + " task(s) in your list.";
        showLine();
        System.out.println();
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks the {@link List} of {@link Task} objects to display
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println(HELIX_SYMBOL + " [Helix] : Listing tasks...");
        showLine();
        System.out.println(NOTE_SYMBOL + " Task List:");

        lastMessage = "Listing tasks...\n" + NOTE_SYMBOL + " Task List:\n";

        if (tasks.isEmpty()) {
            System.out.println("There's no pending tasks.");
            showLine();
            System.out.println();
            lastMessage += "There's no pending tasks.";
            return;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s%n", i + 1, tasks.get(i));
            messageBuilder.append(String.format("    %d. %s%n", i + 1, tasks.get(i)));
        }
        lastMessage += messageBuilder.toString();

        showLine();
        System.out.println();
    }

    /**
     * Displays a message indicating that the task is already marked as completed.
     */
    public void showTaskAlreadyCompleted() {
        System.out.println(HELIX_SYMBOL + " [Helix] : This task is already marked as done!\n");
        lastMessage = WARNING_SYMBOL + " This task is already completed!";
    }

    /**
     * Displays a message indicating that the task was marked as complete.
     *
     * @param task the {@link Task} that was marked as complete
     */
    public void showTaskMarkedComplete(Task task) {
        System.out.println(CHECK_SYMBOL + " [Helix] : Task marked as complete!");
        System.out.println("    " + task + "\n");
        lastMessage = CHECK_SYMBOL + " Task marked as complete!\n" + "    " + task;
    }

    /**
     * Displays a message indicating that the task is already marked as pending.
     */
    public void showTaskAlreadyPending() {
        System.out.println(HELIX_SYMBOL + " [Helix] : This task is already not done!\n");
        lastMessage = WARNING_SYMBOL + " This task is already pending!";
    }

    /**
     * Displays a message indicating that the task was marked as pending.
     *
     * @param task the {@link Task} that was marked as pending
     */
    public void showTaskMarkedPending(Task task) {
        System.out.println(CROSS_SYMBOL + " [Helix] : Task marked as incomplete!");
        System.out.println("    " + task + "\n");
        lastMessage = CROSS_SYMBOL + " Task marked as incomplete!\n" + "    " + task;
    }

    /**
     * Displays the matching tasks found in the task list.
     *
     * @param matchingTasks the list of tasks that match the search keyword
     */
    public void showMatchingTasks(List<Task> matchingTasks) {
        assert matchingTasks != null : "matchingTasks list should not be null!";

        if (matchingTasks.isEmpty()) {
            System.out.println(HELIX_SYMBOL + " [Helix] : No matching tasks found!");
            lastMessage = "No matching tasks found!";
        } else {
            showLine();
            System.out.println(HELIX_SYMBOL + " [Helix] : Tasks with matching keywords...");
            lastMessage = "Tasks with matching keywords...\n";

            StringBuilder messageBuilder = new StringBuilder();
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.printf("    %d. %s%n", i + 1, matchingTasks.get(i));
                messageBuilder.append(String.format("    %d. %s%n", i + 1, matchingTasks.get(i)));
            }
            lastMessage += messageBuilder.toString();
            showLine();
        }
        System.out.println();
    }

    /**
     * Retrieves the last message displayed by the ConsoleUi.
     *
     * @return the last message as a String
     */
    public String getLastMessage() {
        assert lastMessage != null : "lastMessage should not be null!";
        return lastMessage;
    }
}
