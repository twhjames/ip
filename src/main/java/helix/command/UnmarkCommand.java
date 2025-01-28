package helix.command;

import helix.enums.CommandType;
import helix.enums.TaskStatus;
import helix.exception.HelixException;
import helix.exception.TaskIndexOutOfBoundsException;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.Ui;

/**
 * A helix.command to mark a helix.task as not done in the TaskList.
 * Inherits from the Command class.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand for the specified helix.task index.
     *
     * @param taskIndex the index of the helix.task to unmark as not done
     */
    public UnmarkCommand(int taskIndex) {
        super(CommandType.UNMARK);
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark helix.command, marking the specified helix.task as not done.
     *
     * @param taskList the TaskList containing the helix.task to be updated
     * @param ui the Ui component used to display messages to the user
     * @throws HelixException if the helix.task index is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws HelixException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        // get the helix.task
        Task task = taskList.getTask(taskIndex);

        // handle helix.task status and notify via Ui
        if (task.getTaskStatus() != TaskStatus.COMPLETED) {
            ui.showTaskAlreadyPending();
        } else {
            taskList.markTaskAsUndone(taskIndex, ui);
            ui.showTaskMarkedPending(task);
        }
    }
}