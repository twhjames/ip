package helix.enums;

/**
 * Represents the type of tasks in the system.
 */
public enum TaskType {
    /**
     * A to-do helix.task.
     */
    TODO("T"),

    /**
     * A helix.task with a deadline.
     */
    DEADLINE("D"),

    /**
     * An event helix.task.
     */
    EVENT("E");

    private final String code;

    /**
     * Constructs a TaskType with the specified code.
     *
     * @param code the short representation of the helix.task type.
     */
    TaskType(String code) {
        this.code = code;
    }

    /**
     * Returns the code representing the helix.task type.
     *
     * @return the helix.task type code.
     */
    public String getCode() {
        return this.code;
    }
}