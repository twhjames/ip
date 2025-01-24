package command;

import enums.TaskStatus;
import enums.TaskType;
import enums.OutputSymbol;
import enums.CommandType;

import exception.HelixException;
import exception.TaskIndexOutOfBoundsException;

import task.Task;
import util.TaskList;

/**
 * A command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand for the specified task index.
     *
     * @param taskIndex the index of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        super(CommandType.DELETE);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the task from the task list.
     *
     * @param taskList the task list from which the task will be removed
     * @throws TaskIndexOutOfBoundsException if the task index is invalid
     */
    @Override
    public void execute(TaskList taskList) throws HelixException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        // Remove the task and notify the user
        Task task = taskList.removeTask(taskIndex);
        String taskType = task.getTaskType().name();
        String isDone = task.isDone().name();
        String taskDescription = task.getDescription();
        String taskDetails = task.getTaskDetails();

        // Output symbols for formatting
        String removedSymbol = OutputSymbol.BIN.getSymbol();
        String typeSymbol = OutputSymbol.CLIPBOARD.getSymbol();
        String descriptionSymbol = OutputSymbol.NOTE.getSymbol();
        String completedSymbol = OutputSymbol.WRENCH.getSymbol();

        System.out.println("════════════════════════════════════");
        System.out.println(removedSymbol + "  Task Removed!");
        System.out.println("════════════════════════════════════");
        System.out.println("  " + typeSymbol + " Type: " + taskType);
        System.out.println("  " + descriptionSymbol + " Description: " + taskDescription);
        if (!taskDetails.isEmpty()) {
            System.out.println("  " + taskDetails);
        }
        System.out.println("  " + completedSymbol + " Task Status: " + isDone);
        System.out.println("\nYou now have " + taskList.getTaskCount() + " task(s) in your list.");
        System.out.println("════════════════════════════════════\n");
    }
}