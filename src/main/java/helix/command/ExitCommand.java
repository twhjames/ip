package helix.command;

import helix.enums.CommandType;
import helix.enums.ExecutionStatus;
import helix.task.TaskList;
import helix.ui.ConsoleUi;

/**
 * A helix.command to terminate the application.
 * Inherits from the Command class.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a ExitCommand.
     */
    public ExitCommand() {
        super(CommandType.BYE);
    }

    /**
     * Executes the exit helix.command, displaying a farewell message.
     *
     * @param taskList the TaskList (unused in this implementation)
     * @param consoleUi the ConsoleUi component used to display messages to the user
     */
    @Override
    public void execute(TaskList taskList, ConsoleUi consoleUi) {
        consoleUi.showExit();
    }

    /**
     * Indicates that this helix.command should terminate the application.
     *
     * @return ExecutionStatus.EXIT to signal application termination
     */
    @Override
    public ExecutionStatus isExit() {
        return ExecutionStatus.EXIT;
    }
}
