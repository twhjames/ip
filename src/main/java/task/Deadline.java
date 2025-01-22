package task;

/**
 * Represents a deadline task with a description and a due date.
 * Inherits from the Task class.
 */
public class Deadline extends Task {

    private final String taskType = "D"; // Identifier for Deadline tasks
    private final String duedate; // Due date for the deadline task

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param duedate     The due date of the task.
     */
    public Deadline(String description, String duedate) {
        super(description);
        this.duedate = duedate;
    }

    /**
     * Gets the type of the task.
     *
     * @return A string representing the type of the task ("D").
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Gets additional details specific to a Deadline task.
     *
     * @return A string describing the due date of the task.
     */
    @Override
    public String getTaskDetails() {
        return "📅 Due: " + this.duedate;
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString() + " (by: " + this.duedate + ")";
    }
}
