package command;

import enums.CommandType;
import enums.OutputSymbol;
import util.TaskList;
import task.Task;

/**
 * A command to add a task to the TaskList.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
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
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(task);

        String taskType = task.getTaskType().name();
        String taskDescription = task.getDescription();
        String taskDetails = task.getTaskDetails();

        String folderSymbol = OutputSymbol.FOLDER.getSymbol();
        String clipboardSymbol = OutputSymbol.CLIPBOARD.getSymbol();
        String noteSymbol = OutputSymbol.NOTE.getSymbol();

        System.out.println("════════════════════════════════════");
        System.out.println(folderSymbol + "  Task Added!");
        System.out.println("════════════════════════════════════");
        System.out.println("  " + clipboardSymbol +" Type: " + taskType);
        System.out.println("  " + noteSymbol +" Description: " + taskDescription);
        if (!taskDetails.isEmpty()) {
            System.out.println("  " + taskDetails);
        }
        System.out.println("\nYou now have " + taskList.getTaskCount() + " task(s) in your list.");
        System.out.println("════════════════════════════════════\n");
    }

}

