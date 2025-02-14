package helix.command;

import helix.enums.CommandType;
import helix.enums.TaskType;
import helix.exception.HelixException;
import helix.exception.InvalidCommandException;
import helix.exception.InvalidTaskTypeException;
import helix.exception.TaskIndexOutOfBoundsException;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * Handles the update of an existing task.
 */
public class UpdateCommand extends Command {
    private final int taskIndex;
    private final TaskType expectedType;
    private final String newDetails;

    /**
     * Constructs an UpdateCommand.
     *
     * @param taskIndex    The index of the task to be updated.
     * @param expectedType The expected task type.
     * @param newDetails   The new details for the task.
     */
    public UpdateCommand(int taskIndex, TaskType expectedType, String newDetails) {
        super(getCommandTypeFromTaskType(expectedType));
        this.taskIndex = taskIndex;
        this.expectedType = expectedType;
        this.newDetails = newDetails;
    }

    /**
     * Converts a {@link TaskType} to the corresponding {@link CommandType}.
     *
     * @param taskType The task type to convert.
     * @return The corresponding CommandType.
     */
    private static CommandType getCommandTypeFromTaskType(TaskType taskType) {
        return switch (taskType) {
        case TODO -> CommandType.TODO;
        case DEADLINE -> CommandType.DEADLINE;
        case EVENT -> CommandType.EVENT;
        default -> CommandType.UPDATE; // Assume unknown types are update requests;
        };
    }

    /**
     * Executes the update helix.command, modifying the specified task in the {@link TaskList}.
     *
     * @param taskList The TaskList containing the helix.task to be updated.
     * @param consoleUi The ConsoleUi component used to display messages to the user.
     * @throws HelixException If the task index is invalid or the task type does not match.
     * @throws InvalidCommandException If the new details are in an invalid format.
     */
    @Override
    public void execute(TaskList taskList, ConsoleUi consoleUi) throws HelixException {
        // Validate task number
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        // Retrieve task
        Task task = taskList.getTask(taskIndex);

        // Validate task type
        if (task.getTaskType() != expectedType) {
            throw new InvalidTaskTypeException("Task type mismatch. Expected: " + task.getTaskType());
        }

        // Update based on task type
        switch (expectedType) {
        case TODO: task.updateTaskDetails(newDetails);
            break;
        case DEADLINE:
            String[] deadlineParts = newDetails.split(" /by ", 2);
            if (deadlineParts.length != 2) {
                throw new InvalidCommandException(
                        "Invalid format. Use: update <taskNum> deadline <description> /by <due date>"
                );
            }
            task.updateTaskDetails(deadlineParts[0] + " - " + deadlineParts[1]);
            break;
        case EVENT:
            String[] eventParts = newDetails.split(" /from | /to ");
            if (eventParts.length != 3) {
                throw new InvalidCommandException(
                        "Invalid format. Use: update <taskNum> event <description> /from <start> /to <end>"
                );
            }
            task.updateTaskDetails(eventParts[0] + " - " + eventParts[1] + " - " + eventParts[2]);
            break;
        default:
            throw new InvalidTaskTypeException("Unsupported task type.");
        }

        taskList.updateTask(taskIndex, newDetails, consoleUi);
        consoleUi.showTaskUpdated(task);
    }
}
