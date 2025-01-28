package storage;

import helix.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import helix.task.Task;
import helix.task.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        Path tempFile = Files.createTempFile("test_storage", ".txt");
        storage = new Storage(tempFile.toString());
    }

    @Test
    void save_tasksWithSingleTask_taskPersistedSuccessfully() throws IOException {
        List<Task> tasks = List.of(new Todo("Sample Task"));
        storage.save(tasks);

        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("Sample Task", loadedTasks.get(0).getDescription());
    }

    @Test
    void saveAndLoad_emptyTaskList_emptyListRetrieved() throws IOException {
        // Arrange
        List<Task> tasksToSave = List.of();

        // Act
        storage.save(tasksToSave);
        List<Task> loadedTasks = storage.load();

        // Assert
        assertEquals(0, loadedTasks.size(), "The loaded task list should be empty.");
    }
}
