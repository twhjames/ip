package util;

import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to manage a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return a list of tasks in the TaskList
     */
    public List<Task> getTasks() {
        return this.tasks;
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
     * Retrieves a task from the TaskList based on its index.
     *
     * @param taskIndex the index of the task to be retrieved
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }
}
