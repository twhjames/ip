package command;

import enums.CommandType;
import enums.ExecutionStatus;
import task.TaskList;
import ui.Ui;

/**
 * A command to terminate the application.
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
     * Executes the exit command, displaying a farewell message.
     *
     * @param taskList the TaskList (unused in this implementation)
     * @param ui the Ui component used to display messages to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showExit();
    }

    /**
     * Indicates that this command should terminate the application.
     *
     * @return ExecutionStatus.EXIT to signal application termination
     */
    @Override
    public ExecutionStatus isExit() {
        return ExecutionStatus.EXIT;
    }
}

