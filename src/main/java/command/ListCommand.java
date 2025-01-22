package command;

import util.TaskList;
import task.Task;

import java.util.List;

/**
 * A command to list all tasks in the TaskList.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command, printing all tasks in the TaskList.
     *
     * @param taskList the TaskList containing tasks to be listed
     */
    @Override
    public void execute(TaskList taskList) {
        System.out.println("\uD83E\uDD16 [Helix] : Listing tasks...");
        System.out.println("══════════════════════════════");
        System.out.println("📝 Task List:");

        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("There's no pending tasks.");
            System.out.println("══════════════════════════════\n");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println("══════════════════════════════\n");
    }
}

