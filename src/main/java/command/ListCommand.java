package command;

import enums.CommandType;
import enums.OutputSymbol;
import util.TaskList;
import task.Task;

import java.util.List;

/**
 * A command to list all tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Executes the list command, printing all tasks in the TaskList.
     *
     * @param taskList the TaskList containing tasks to be listed
     */
    @Override
    public void execute(TaskList taskList) {

        String helixSymbol = OutputSymbol.HELIX.getSymbol();
        String noteSymbol = OutputSymbol.NOTE.getSymbol();

        System.out.println(helixSymbol + " [Helix] : Listing tasks...");
        System.out.println("════════════════════════════════════");
        System.out.println(noteSymbol + " Task List:");

        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("There's no pending tasks.");
            System.out.println("════════════════════════════════════\n");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println("════════════════════════════════════\n");
    }
}

