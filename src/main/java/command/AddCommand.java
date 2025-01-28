package command;

import enums.CommandType;
import task.TaskList;
import task.Task;
import ui.Ui;

/**
 * A command to add a task to the TaskList.
 * Inherits from the Command class.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param commandType the type of the command
     * @param task the task to be added
     */
    public AddCommand(CommandType commandType, Task task) {
        super(commandType);
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the TaskList.
     *
     * @param taskList the TaskList to which the task will be added
     * @param ui the Ui component used to display messages to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(task, ui);
        ui.showTaskAdded(task, taskList.getTaskCount());
    }

}

