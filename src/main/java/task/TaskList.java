package task;

import enums.OutputSymbol;
import storage.Storage;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Represents a task manager that maintains a list of tasks, allowing for operations such as adding,
 * removing, and marking tasks as done or undone. The TaskList automatically synchronizes changes
 * with a storage backend.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;

    /**
     * Constructs a TaskList and initializes it with tasks loaded from the specified storage.
     *
     * @param storage the Storage object responsible for saving and loading tasks
     * @throws IOException if an error occurs while loading tasks from storage
     */
    public TaskList(Storage storage) throws IOException {
        this.storage = storage;
        this.tasks = storage.load();
    }

    /**
     * Adds a new task to the TaskList and updates the storage.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        updateStorage();
    }

    /**
     * Removes a task from the TaskList by its index and updates the storage.
     *
     * @param taskIndex the index of the task to be removed (0-based index)
     * @return the task that was removed
     * @throws IndexOutOfBoundsException if the taskIndex is out of range
     */
    public Task removeTask(int taskIndex) {
        Task task = this.tasks.remove(taskIndex);
        updateStorage();
        return task;
    }

    /**
     * Marks a task as done based on its index and updates the storage.
     *
     * @param taskIndex the index of the task to mark as done (0-based index)
     * @throws IndexOutOfBoundsException if the taskIndex is out of range
     */
    public void markTaskAsDone(int taskIndex) {
        Task task = this.tasks.get(taskIndex);
        task.markAsDone();
        updateStorage();
    }

    /**
     * Marks a task as not done based on its index and updates the storage.
     *
     * @param taskIndex the index of the task to mark as not done (0-based index)
     * @throws IndexOutOfBoundsException if the taskIndex is out of range
     */
    public void markTaskAsUndone(int taskIndex) {
        Task task = this.tasks.get(taskIndex);
        task.markAsUndone();
        updateStorage();
    }

    /**
     * Retrieves an unmodifiable view of the current list of tasks.
     *
     * @return an unmodifiable List of tasks
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    /**
     * Retrieves the total number of tasks in the TaskList.
     *
     * @return the number of tasks in the TaskList
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Retrieves a specific task from the TaskList by its index.
     *
     * @param taskIndex the index of the task to retrieve (0-based index)
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the taskIndex is out of range
     */
    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    /**
     * Updates the current state of the tasks to the storage. If saving fails, logs a warning message.
     */
    private void updateStorage() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            String warningSymbol = OutputSymbol.WARNING.getSymbol();
            System.out.println(warningSymbol + " [Helix] : Failed to save tasks: " + e.getMessage());
        }
    }
}