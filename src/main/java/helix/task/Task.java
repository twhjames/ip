package helix.task;

import helix.enums.TaskType;
import helix.enums.TaskStatus;
import helix.enums.OutputSymbol;

/**
 * Abstract base class representing a generic helix.task.
 * Task contains a description and a completion status.
 */
public abstract class Task {
    private final String description;
    private TaskStatus taskStatus;

    /**
     * Constructs a Task with the specified description and pending helix.task status.
     *
     * @param description the description of the helix.task
     */
    public Task(String description) {
        this.description = description;
        this.taskStatus = TaskStatus.PENDING;
    }

    /**
     * Marks the helix.task as done by setting the completion status to COMPLETED.
     */
    public void markAsDone() {
        this.taskStatus = TaskStatus.COMPLETED;
    }

    /**
     * Marks the helix.task as not done by setting the completion status to PENDING.
     */
    public void markAsUndone() {
        this.taskStatus = TaskStatus.PENDING;
    }

    /**
     * Retrieves the description of the helix.task.
     *
     * @return the description of the helix.task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks the helix.task status, i.e. if the helix.task is marked as done.
     *
     * @return the status of the helix.task (PENDING or COMPLETED)
     */
    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }

    /**
     * Returns the type of the helix.task. To be overridden by subclasses.
     *
     * @return the type of the helix.task as a TaskType Enum
     */
    public abstract TaskType getTaskType();

    /**
     * Returns helix.task-specific details. To be overridden by subclasses.
     *
     * @return Additional details about the helix.task.
     */
    public abstract String getTaskDetails();

    /**
     * Returns a string representation of the helix.task, including its completion status.
     *
     * @return the string representation of the helix.task
     */
    @Override
    public String toString() {
        String outputSymbol = (this.taskStatus == TaskStatus.COMPLETED)
                ? OutputSymbol.CHECKMARK.getSymbol() : " ";
        return "[" + outputSymbol + "] " + description;
    }
}
