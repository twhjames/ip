package command;

import util.TaskList;

/**
 * A command to mark a task as done in the TaskList.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand for the specified task index.
     *
     * @param taskIndex the index of the task to mark as done
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command, marking the specified task as done.
     *
     * @param taskList the TaskList containing the task to be updated
     */
    @Override
    public void execute(TaskList taskList) {
        if (taskIndex >= 0 && taskIndex < taskList.getTaskCount()) {
            if (taskList.getTask(taskIndex).isDone()) {
                System.out.println("\uD83E\uDD16 [Helix] : This task is already marked as done!\n");
            } else {
                taskList.getTask(taskIndex).markAsDone();
                System.out.println("\uD83E\uDD16 [Helix] : Nice! I've marked this task as done.");
                System.out.println("════════════════════════════════════");
                System.out.println("✅ Task Marked as Complete:");
                System.out.println("  " + taskList.getTask(taskIndex));
                System.out.println("════════════════════════════════════\n");
            }
        } else {
            System.out.println("\uD83E\uDD16 [Helix] : Invalid task number.\n");
        }
    }
}
