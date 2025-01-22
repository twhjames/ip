package command;

import exception.HelixException;
import exception.TaskIndexOutOfBoundsException;
import task.Task;
import util.TaskList;

/**
 * A command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand for the specified task index.
     *
     * @param taskIndex the index of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the task from the task list.
     *
     * @param taskList the task list from which the task will be removed
     * @throws TaskIndexOutOfBoundsException if the task index is invalid
     */
    @Override
    public void execute(TaskList taskList) throws HelixException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new TaskIndexOutOfBoundsException(taskIndex + 1, taskList.getTaskCount());
        }

        // Remove the task and notify the user
        Task task = taskList.removeTask(taskIndex);
        String taskType = task.getTaskType();
        String taskDescription = task.getDescription();
        String taskDetails = task.getTaskDetails();
        boolean isDone = task.isDone();

        System.out.println("════════════════════════════════════");
        System.out.println("🗑️ Task Removed:");
        System.out.println("  📋 Type: " + taskType);
        System.out.println("  📝 Description: " + taskDescription);
        if (!taskDetails.isEmpty()) {
            System.out.println("  " + taskDetails);
        }
        System.out.println("  🛠️ Completed: " + isDone);
        System.out.println("You now have " + taskList.getTaskCount() + " task(s) in your list.");
        System.out.println("════════════════════════════════════\n");
    }
}