package enums;

/**
 * Represents the status of a task.
 */
public enum TaskStatus {
    /**
     * Task is pending and not yet completed.
     */
    PENDING(false),

    /**
     * Task has been completed.
     */
    COMPLETED(true);

    private final boolean isDone;

    /**
     * Constructs a TaskStatus with the specified completion status.
     *
     * @param isDone true if the task is completed, false otherwise.
     */
    TaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }
}