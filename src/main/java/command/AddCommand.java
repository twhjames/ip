package command;

import util.TaskList;
import task.Task;

/**
 * A command to add a task to the TaskList.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task description.
     *
     * @param input the description of the task to be added
     */
    public AddCommand(String input) {
        this.task = new Task(input);
    }

    /**
     * Executes the add command by adding the task to the TaskList.
     *
     * @param taskList the TaskList to which the task will be added
     */
    @Override
    public void execute(TaskList taskList) {
        String taskDescription = task.getDescription();
        System.out.println("\uD83E\uDD16 [Helix] : Added Task - " + taskDescription + "\n");
        taskList.addTask(this.task);
    }
}

