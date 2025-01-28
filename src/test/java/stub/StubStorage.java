package stub;

import java.util.ArrayList;
import java.util.List;

import helix.storage.Storage;
import helix.task.Task;

public class StubStorage extends Storage {
    public StubStorage() {
        super(""); // No file path required
    }

    @Override
    public List<Task> load() {
        return new ArrayList<>(); // Return an empty task list
    }

    @Override
    public void save(List<Task> tasks) {
        // Do nothing, as this is a stub
    }
}