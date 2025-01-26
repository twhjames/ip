package task;

import enums.TaskType;
import enums.TaskStatus;
import enums.OutputSymbol;

/**
 * Abstract base class representing a generic task.
 * Task contains a description and a completion status.
 */
public abstract class Task {
    private final String description;
    private TaskStatus isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = TaskStatus.PENDING;
    }

    /**
     * Marks the task as done by setting the completion status to COMPLETED.
     */
    public void markAsDone() {
        this.isDone = TaskStatus.COMPLETED;
    }

    /**
     * Marks the task as not done by setting the completion status to PENDING.
     */
    public void markAsUndone() {
        this.isDone = TaskStatus.PENDING;
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
     * @return the status of the task (PENDING or COMPLETED)
     */
    public TaskStatus isDone() {
        return this.isDone;
    }

    /**
     * Returns the type of the task. To be overridden by subclasses.
     *
     * @return the type of the task as a TaskType Enum
     */
    public abstract TaskType getTaskType();

    /**
     * Returns task-specific details. To be overridden by subclasses.
     *
     * @return Additional details about the task.
     */
    public abstract String getTaskDetails();

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        String outputSymbol = (this.isDone == TaskStatus.COMPLETED)
                ? OutputSymbol.CHECKMARK.getSymbol() : " ";
        return "[" + outputSymbol + "] " + description;
    }
}
