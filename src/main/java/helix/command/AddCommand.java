package helix.command;

import helix.enums.CommandType;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.Ui;

/**
 * A helix.command to add a helix.task to the TaskList.
 * Inherits from the Command class.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified helix.task.
     *
     * @param commandType the type of the helix.command
     * @param task the helix.task to be added
     */
    public AddCommand(CommandType commandType, Task task) {
        super(commandType);
        this.task = task;
    }

    /**
     * Executes the add helix.command by adding the helix.task to the TaskList.
     *
     * @param taskList the TaskList to which the helix.task will be added
     * @param ui the Ui component used to display messages to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(task, ui);
        ui.showTaskAdded(task, taskList.getTaskCount());
    }

}
