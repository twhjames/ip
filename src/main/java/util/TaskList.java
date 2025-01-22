package util;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to manage a list of tasks.
 */
public class TaskList {
    private final List<String> tasks;

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
    public void addTask(String task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return a list of task descriptions
     */
    public List<String> getTasks() {
        return this.tasks;
    }
}
