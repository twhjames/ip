package enums;

/**
 * Represents the types of commands that can be issued.
 */
public enum CommandType {
    /**
     * Command to add a task without a deadline.
     */
    TODO,

    /**
     * Command to add a task with a deadline.
     */
    DEADLINE,

    /**
     * Command to add an event task.
     */
    EVENT,

    /**
     * Command to list all tasks.
     */
    LIST,

    /**
     * Command to mark a task as completed.
     */
    MARK,

    /**
     * Command to unmark a completed task.
     */
    UNMARK,

    /**
     * Command to delete a task.
     */
    DELETE,

    /**
     * Command to exit the program.
     */
    BYE;
}