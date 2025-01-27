package command;

import enums.CommandType;
import enums.OutputSymbol;
import enums.TaskStatus;
import exception.HelixException;
import exception.TaskIndexOutOfBoundsException;
import task.Task;
import task.TaskList;

/**
 * A command to mark a task as done in the TaskList.
 * Inherits from the Command class.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand for the specified task index.
     *
     * @param taskIndex the index of the task to mark as done
     */
    public MarkCommand(int taskIndex) {
        super(CommandType.MARK);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command, marking the specified task as done.
     *
     * @param taskList the TaskList containing the task to be updated
     * @throws HelixException if the task index is invalid
     */
    @Override
    public void execute(TaskList taskList) throws HelixException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        String helixSymbol = OutputSymbol.HELIX.getSymbol();
        String checkSymbol = OutputSymbol.CHECK.getSymbol();

        // Mark the task as done
        Task task = taskList.getTask(taskIndex);
        if (task.getTaskStatus() == TaskStatus.COMPLETED) {
            System.out.println(helixSymbol + " [Helix] : This task is already marked as done!\n");
        } else {
            taskList.markTaskAsDone(taskIndex);
            System.out.println(checkSymbol + " [Helix] : Task marked as complete!");
            System.out.println("    " + task + "\n");
        }
    }
}