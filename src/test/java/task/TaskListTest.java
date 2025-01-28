package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import helix.task.Task;
import helix.task.TaskList;
import helix.task.Todo;
import helix.ui.Ui;
import stub.StubStorage;

public class TaskListTest {
    private TaskList taskList;
    private Ui ui;
    private StubStorage stubStorage;

    @BeforeEach
    void setUp() throws IOException {
        stubStorage = new StubStorage();
        ui = new Ui();
        taskList = new TaskList(stubStorage);
    }

    @Test
    void addTask_validTask_taskAdded() {
        Task task = new Todo("Sample Task");
        taskList.addTask(task, ui);
        assertEquals(1, taskList.getTaskCount());
        assertEquals("Sample Task", taskList.getTask(0).getDescription());
    }

    @Test
    void removeTask_validIndex_taskRemoved() {
        Task task = new Todo("Sample Task");
        taskList.addTask(task, ui);
        Task removedTask = taskList.removeTask(0, ui);
        assertEquals("Sample Task", removedTask.getDescription());
        assertEquals(0, taskList.getTaskCount());
    }
}
