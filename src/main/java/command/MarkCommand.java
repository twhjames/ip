package command;

import exception.HelixException;
import exception.TaskIndexOutOfBoundsException;
import util.TaskList;

/**
 * A command to mark a task as done in the TaskList.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand for the specified task index.
     *
     * @param taskIndex the index of the task to mark as done
     */
    public MarkCommand(int taskIndex) {
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

        // Mark the task as done
        if (taskList.getTask(taskIndex).isDone()) {
            System.out.println("\uD83E\uDD16 [Helix] : This task is already marked as done!\n");
        } else {
            taskList.getTask(taskIndex).markAsDone();
            System.out.println("✅ [Helix] : Task marked as complete!");
            System.out.println("    " + taskList.getTask(taskIndex) + "\n");
        }
    }
}