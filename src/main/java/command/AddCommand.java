package command;

import enums.CommandType;
import enums.OutputSymbol;
import enums.TaskType;
import task.TaskList;
import task.Task;

/**
 * A command to add a task to the TaskList.
 * Inherits from the Command class.
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

        TaskType taskType = task.getTaskType();
        String taskDescription = task.getDescription();
        String taskDetails = task.getTaskDetails();

        String folderSymbol = OutputSymbol.FOLDER.getSymbol();
        String clipboardSymbol = OutputSymbol.CLIPBOARD.getSymbol();
        String noteSymbol = OutputSymbol.NOTE.getSymbol();
        String calendarSymbol = OutputSymbol.CALENDAR.getSymbol();
        String clockSymbol = OutputSymbol.CLOCK.getSymbol();

        System.out.println("════════════════════════════════════");
        System.out.println(folderSymbol + "  Task Added!");
        System.out.println("════════════════════════════════════");
        System.out.println("  " + clipboardSymbol +" Type: " + taskType.name());
        System.out.println("  " + noteSymbol +" Description: " + taskDescription);
        if (taskType == TaskType.DEADLINE) {
            System.out.println("  " + calendarSymbol + " Due: " + taskDetails);
        } else if (taskType == TaskType.EVENT) {
            String[] parts = taskDetails.split(" - ");
            String from = parts[0];
            String to = parts[1];
            System.out.println("  " + clockSymbol + " From: " + from + "\n  " + clockSymbol + " To: " + to);
        }
        System.out.println("\nYou now have " + taskList.getTaskCount() + " task(s) in your list.");
        System.out.println("════════════════════════════════════\n");
    }

}

