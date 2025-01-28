package helix.command;

import helix.enums.CommandType;
import helix.task.Task;
import helix.task.TaskList;
import helix.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A command to find tasks that match a keyword in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    /**
     * Executes the find command by filtering tasks in the task list that contain
     * the specified keyword and displaying them to the user.
     *
     * @param taskList the TaskList to search for matching tasks
     * @param ui the Ui component used to display messages to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        List<Task> matchingTasks = taskList.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        ui.showMatchingTasks(matchingTasks);
    }
}