package task;

import enums.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a description and a due date.
 * Inherits from the Task class.
 */
public class Deadline extends Task {

    private final LocalDateTime dueDate;

    private static final DateTimeFormatter[] INPUT_FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),       // Format: 2/12/2019 1800
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),     // Format: 2019-10-15 1800
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")  // Format: Oct 11 2019, 5:00 pm
    };
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the task.
     * @param dueDate     The due date of the task in either "d/M/yyyy HHmm" or "yyyy-MM-dd HHmm" format.
     * @throws IllegalArgumentException if the due date format is invalid.
     */
    public Deadline(String description, String dueDate) throws IllegalArgumentException {
        super(description);
        this.dueDate = parseDueDate(dueDate);
    }

    /**
     * Parses the due date string using multiple input formatters.
     *
     * @param dueDate The due date string to parse.
     * @return A LocalDateTime object representing the parsed due date.
     * @throws IllegalArgumentException if the input does not match any expected format.
     */
    private LocalDateTime parseDueDate(String dueDate) {
        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                return LocalDateTime.parse(dueDate, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next formatter
            }
        }
        throw new IllegalArgumentException(
                "Invalid date format. Supported formats: d/M/yyyy HHmm or yyyy-MM-dd HHmm."
        );
    }

    /**
     * Retrieves the due date of the deadline task.
     *
     * @return The due date as a {@link LocalDateTime} object.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Gets the type of the task.
     *
     * @return A TaskType enum representing the type of the task (DEADLINE).
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Gets additional details specific to a Deadline task.
     *
     * @return A string describing the due date of the task in "MMM dd yyyy, h:mm a" format.
     */
    @Override
    public String getTaskDetails() {
        return this.dueDate.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns the string representation of the Deadline task.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.DEADLINE.getCode() + "]" + super.toString()
                + " (by: " + this.dueDate.format(OUTPUT_FORMATTER) + ")";
    }
}
