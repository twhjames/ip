package command;

import exception.HelixException;
import exception.TaskIndexOutOfBoundsException;
import util.TaskList;

/**
 * A command to mark a task as not done in the TaskList.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand for the specified task index.
     *
     * @param taskIndex the index of the task to unmark as not done
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command, marking the specified task as not done.
     *
     * @param taskList the TaskList containing the task to be updated
     * @throws HelixException if the task index is invalid
     */
    @Override
    public void execute(TaskList taskList) throws HelixException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        // Unmark the task as not done
        if (!taskList.getTask(taskIndex).isDone()) {
            System.out.println("\uD83E\uDD16 [Helix] : This task is already not done!\n");
        } else {
            taskList.getTask(taskIndex).markAsUndone();
            System.out.println("❌ [Helix] : Task marked as incomplete!");
            System.out.println("    " + taskList.getTask(taskIndex) + "\n");
        }
    }
}