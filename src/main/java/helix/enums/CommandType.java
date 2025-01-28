package helix.enums;

/**
 * Represents the types of commands that can be issued.
 */
public enum CommandType {
    /**
     * Command to add a helix.task without a deadline.
     */
    TODO,

    /**
     * Command to add a helix.task with a deadline.
     */
    DEADLINE,

    /**
     * Command to add an event helix.task.
     */
    EVENT,

    /**
     * Command to list all tasks.
     */
    LIST,

    /**
     * Command to mark a helix.task as completed.
     */
    MARK,

    /**
     * Command to unmark a completed helix.task.
     */
    UNMARK,

    /**
     * Command to delete a helix.task.
     */
    DELETE,

    /**
     * Command to exit the program.
     */
    BYE;
}