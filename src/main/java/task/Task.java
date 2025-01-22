package task;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the completion status to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "✔" : " ") + "] " + description;
    }
}
