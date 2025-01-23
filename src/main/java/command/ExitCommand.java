package command;

import enums.CommandType;
import enums.ExecutionStatus;
import enums.OutputSymbol;
import util.TaskList;

/**
 * A command to terminate the application.
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
     */
    @Override
    public void execute(TaskList taskList) {
        String helixSymbol = OutputSymbol.HELIX.getSymbol();
        String handwaveSymbol = OutputSymbol.HANDWAVE.getSymbol();
        String sparkleSymbol = OutputSymbol.SPARKLE.getSymbol();

        System.out.println(
                helixSymbol +
                " [Helix] : Bye! Hope to see you soon! " +
                handwaveSymbol
        );
        System.out.println("=========================================================");
        System.out.println(
                sparkleSymbol +
                " Thank you for using Helix. Have a great day! " +
                sparkleSymbol
        );
        System.out.println("=========================================================");
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

