package task;

import enums.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a description, start time, and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {

    private static final DateTimeFormatter[] INPUT_FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),       // Format: 2/12/2019 1800
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),     // Format: 2019-10-15 1800
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")  // Format: Oct 11 2019, 5:00 pm
    };
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param start       the start time of the event
     * @param end         the end time of the event
     * @throws IllegalArgumentException if the time format is invalid or the end time is before the start time
     */
    public Event(String description, String start, String end) {
        super(description);

        // Parse the start and end date/time
        this.startDateTime = parseDateTime(start);
        this.endDateTime = parseDateTime(end);

        // Ensure end time is not before start time
        if (this.startDateTime.isAfter(this.endDateTime)) {
            throw new IllegalArgumentException("End time must be after start time.");
        }
    }

    /**
     * Parses a date-time string using predefined input formatters.
     *
     * @param dateTime the date-time string to parse
     * @return a LocalDateTime object representing the parsed date-time
     * @throws IllegalArgumentException if the input string does not match any expected format
     */
    private LocalDateTime parseDateTime(String dateTime) {
        for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next formatter
            }
        }
        throw new IllegalArgumentException(
                "Invalid date format. Supported formats: d/M/yyyy HHmm or yyyy-MM-dd HHmm."
        );
    }

    /**
     * Gets the type of the task.
     *
     * @return A TaskType enum representing the type of the task (EVENT).
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Retrieves additional details specific to the event task.
     *
     * @return A string describing the start and end times of the event,
     *         formatted as "MMM dd yyyy, h:mm a".
     */
    @Override
    public String getTaskDetails() {
        return startDateTime.format(OUTPUT_FORMATTER)
                + " - "
                + endDateTime.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string representing the event task, including its description,
     *         start time, and end time.
     */
    @Override
    public String toString() {
        return "[" + TaskType.EVENT.getCode() + "]"
                + super.toString()
                + " (from: " + startDateTime.format(OUTPUT_FORMATTER)
                + " to: " + endDateTime.format(OUTPUT_FORMATTER) + ")";
    }
}
