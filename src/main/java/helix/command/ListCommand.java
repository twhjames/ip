package helix.command;

import java.util.List;

import helix.enums.CommandType;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.Ui;

/**
 * A helix.command to list all tasks in the TaskList.
 * Inherits from the Command class.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Executes the list helix.command, printing all tasks in the TaskList.
     *
     * @param taskList the TaskList containing tasks to be listed
     * @param ui the Ui component used to display messages to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        // get unmodifiable list of tasks and pass it to Ui
        List<Task> tasks = taskList.getTasks();
        ui.showTaskList(tasks);
    }
}
