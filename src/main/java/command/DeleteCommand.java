package command;

import enums.CommandType;

import exception.HelixException;
import exception.TaskIndexOutOfBoundsException;

import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * A command to delete a task from the task list.
 * Inherits from the Command class.
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
     * @param ui the Ui component used to display messages to the user
     * @throws TaskIndexOutOfBoundsException if the task index is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws HelixException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        // Remove the task and notify the user
        Task task = taskList.removeTask(taskIndex, ui);
        ui.showTaskRemoved(task, taskList.getTaskCount());

    }
}