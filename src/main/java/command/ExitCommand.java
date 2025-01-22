package command;

import util.TaskList;

/**
 * A command to terminate the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command, displaying a farewell message.
     *
     * @param taskList the TaskList (unused in this implementation)
     */
    @Override
    public void execute(TaskList taskList) {
        System.out.println(
                "\uD83E\uDD16 [Helix] : Bye! Hope to see you soon! \uD83D\uDC4B\n"
        );
        System.out.println("=========================================================");
        System.out.println("✨ Thank you for using Helix. Have a great day! ✨");
        System.out.println("=========================================================");
    }

    /**
     * Indicates that this command should terminate the application.
     *
     * @return true to signal application termination
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

