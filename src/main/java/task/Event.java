package task;

/**
 * Represents an event task with a description, start time, and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {

    private final String taskType = "E"; // Identifier for Event tasks
    private final String from; // Start time of the event
    private final String to;   // End time of the event

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the type of the task.
     *
     * @return A string representing the type of the task ("E").
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Gets additional details specific to an Event task.
     *
     * @return A string describing the start and end times of the event.
     */
    @Override
    public String getTaskDetails() {
        return "🕒 From: " + this.from + "\n  🕒 To: " + this.to;
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
