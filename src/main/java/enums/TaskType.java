package enums;

/**
 * Represents the type of tasks in the system.
 */
public enum TaskType {
    /**
     * A to-do task.
     */
    TODO("T"),

    /**
     * A task with a deadline.
     */
    DEADLINE("D"),

    /**
     * An event task.
     */
    EVENT("E");

    private final String code;

    /**
     * Constructs a TaskType with the specified code.
     *
     * @param code the short representation of the task type.
     */
    TaskType(String code) {
        this.code = code;
    }

    /**
     * Returns the code representing the task type.
     *
     * @return the task type code.
     */
    public String getCode() {
        return this.code;
    }
}