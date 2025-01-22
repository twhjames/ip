package task;

/**
 * Represents a todo task with a description.
 * Inherits from the Task class.
 */
public class Todo extends Task {

    private final String taskType = "T"; // Identifier for Todo tasks

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the type of the task.
     *
     * @return A string representing the type of the task ("T").
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Gets additional details specific to a Todo task.
     *
     * @return An empty string as Todo tasks have no additional details.
     */
    @Override
    public String getTaskDetails() {
        return ""; // No additional details for a Todo task
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString();
    }
}
