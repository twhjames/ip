package command;

import util.TaskList;

/**
 * A command to mark a task as not done in the TaskList.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand for the specified task index.
     *
     * @param taskIndex the index of the task to unmark as not done
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command, marking the specified task as not done.
     *
     * @param taskList the TaskList containing the task to be updated
     */
    @Override
    public void execute(TaskList taskList) {
        if (taskIndex >= 0 && taskIndex < taskList.getTaskCount()) {
            if (!taskList.getTask(taskIndex).isDone()) {
                System.out.println("\uD83E\uDD16 [Helix] : This task is already marked as done!\n");
            } else {
                taskList.getTask(taskIndex).markAsUndone();
                System.out.println("\uD83E\uDD16 [Helix] : OK, I've marked this task as not done yet.");
                System.out.println("══════════════════════════════");
                System.out.println("❌ Task Marked as Incomplete:");
                System.out.println("  " + taskList.getTask(taskIndex));
                System.out.println("══════════════════════════════\n");
            }
        } else {
            System.out.println("\uD83E\uDD16 [Helix] : Invalid task number.\n");
        }
    }
}
