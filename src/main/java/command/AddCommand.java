package command;

import util.TaskList;

/**
 * A command to add a task to the TaskList.
 */
public class AddCommand extends Command {
    private final String task;

    /**
     * Constructs an AddCommand with the specified task description.
     *
     * @param task the description of the task to be added
     */
    public AddCommand(String task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the TaskList.
     *
     * @param taskList the TaskList to which the task will be added
     */
    @Override
    public void execute(TaskList taskList) {
        System.out.println("\uD83E\uDD16 [Helix] : Added Task - " + this.task + "\n");
        taskList.addTask(this.task);
    }
}

