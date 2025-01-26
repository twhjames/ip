package command;

import enums.CommandType;
import enums.ExecutionStatus;
import exception.HelixException;
import task.TaskList;

/**
 * Abstract base class representing a generic command.
 * Commands are executable actions that operate on a TaskList.
 */
public abstract class Command {

    private final CommandType commandType;

    /**
     * Constructs a new Command with the specified {@link CommandType}.
     *
     * @param commandType the type of the command
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
    }

    /**
     * Retrieves the {@link CommandType} of this command.
     *
     * @return the type of the command
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Executes the command on the specified TaskList.
     *
     * @param taskList the TaskList on which the command will operate
     * @throws HelixException if the command fails
     */
    public abstract void execute(TaskList taskList) throws HelixException;

    /**
     * Determines whether the application should continue or terminate after executing the command.
     *
     * @return {@code ExecutionStatus.CONTINUE} to continue or {@code ExecutionStatus.EXIT} to terminate.
     */
    public ExecutionStatus isExit() {
        return ExecutionStatus.CONTINUE;
    }
}
