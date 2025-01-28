package command;

import enums.CommandType;
import enums.TaskStatus;
import exception.HelixException;
import exception.TaskIndexOutOfBoundsException;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * A command to mark a task as not done in the TaskList.
 * Inherits from the Command class.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand for the specified task index.
     *
     * @param taskIndex the index of the task to unmark as not done
     */
    public UnmarkCommand(int taskIndex) {
        super(CommandType.UNMARK);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command, marking the specified task as not done.
     *
     * @param taskList the TaskList containing the task to be updated
     * @param ui the Ui component used to display messages to the user
     * @throws HelixException if the task index is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws HelixException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        // get the task
        Task task = taskList.getTask(taskIndex);

        // handle task status and notify via Ui
        if (task.getTaskStatus() != TaskStatus.COMPLETED) {
            ui.showTaskAlreadyPending();
        } else {
            taskList.markTaskAsUndone(taskIndex, ui);
            ui.showTaskMarkedPending(task);
        }
    }
}