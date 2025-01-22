package command;

import util.TaskList;

/**
 * Abstract base class representing a generic command.
 * Commands are executable actions that operate on a TaskList.
 */
public abstract class Command {
    /**
     * Executes the command on the specified TaskList.
     *
     * @param taskList the TaskList on which the command will operate
     */
    public abstract void execute(TaskList taskList);

    /**
     * Indicates whether the command should terminate the application.
     *
     * @return true if the command signals termination, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
