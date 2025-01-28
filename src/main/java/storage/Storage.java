package storage;

import enums.TaskStatus;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;
import enums.TaskType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the saving and loading of tasks from a file.
 * The tasks are stored in a plain text file and are serialised / de-serialised using a specific format.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     * Ensures the file path always resolves to the `data` directory in the project root.
     *
     * @param filePath the relative path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath).normalize();
    }

    /**
     * Loads tasks from the storage file.
     * If the file or its parent directory does not exist, they are created, and an empty list is returned.
     *
     * @return a {@link List} of {@link Task} objects loaded from the storage file
     * @throws IOException if there is an error reading the file
     */
    public List<Task> load() throws IOException {
        // check if storage folder and file exists
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());  // create directory if it does not exist
            Files.createFile(filePath);                     // create file if it does not exist
            return new ArrayList<>();                       // return an empty list for first-time use
        }

        // storage file exists, read and populate taskList
        List<Task> taskList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line = br.readLine();
            while (line != null) {
                taskList.add(Storage.parseTask(line));
                line = br.readLine();
            }
        }
        return taskList;
    }

    /**
     * Saves a list of tasks to the storage file.
     * The tasks are serialised into a specific format before being written to the file.
     *
     * @param taskList the {@link List} of {@link Task} objects to save
     * @throws IOException if there is an error writing to the file
     */
    public void save(List<Task> taskList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Task task : taskList) {
                writer.write(Storage.serialiseTask(task));
                writer.newLine();
            }
        }
    }

    /**
     * Parses a {@link Task} from its string representation.
     * The string must follow the format: {@code TaskType | TaskStatus | Description | [Additional Details]}.
     *
     * @param line the string representing the task in storage
     * @return the parsed {@link Task} object
     * @throws IllegalArgumentException if the string is invalid or cannot be parsed
     */
    private static Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            TaskType taskType = TaskType.valueOf(parts[0]);
            TaskStatus taskStatus = TaskStatus.valueOf(parts[1]);
            String description = parts[2];

            return switch (taskType) {
                case TODO -> {
                    Todo todo = new Todo(description);
                    if (taskStatus == TaskStatus.COMPLETED) {
                        todo.markAsDone();
                    }
                    yield todo;
                }
                case DEADLINE -> {
                    String duedate = parts[3];
                    Deadline deadline = new Deadline(description, duedate);
                    if (taskStatus == TaskStatus.COMPLETED) {
                        deadline.markAsDone();
                    }
                    yield deadline;
                }
                case EVENT -> {
                    String[] eventDuration = parts[3].split(" - ");
                    String startDateTime = eventDuration[0];
                    String endDateTime = eventDuration[1];
                    Event event = new Event(description, startDateTime, endDateTime);
                    if (taskStatus == TaskStatus.COMPLETED) {
                        event.markAsDone();
                    }
                    yield event;
                }
            };
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error parsing task: " + line, e);
        }
    }

    /**
     * Serialises a {@link Task} into its string representation.
     * The format is: {@code TaskType | TaskStatus | Description | [Additional Details]}.
     *
     * @param task the {@link Task} to serialise
     * @return the string representation of the task
     */
    private static String serialiseTask(Task task) {
        StringBuilder sb = new StringBuilder();

        // Task Type
        TaskType taskType = task.getTaskType();
        sb.append(taskType);
        sb.append(" | ");
        // Completion status
        sb.append(
                task.getTaskStatus() == TaskStatus.COMPLETED
                        ? TaskStatus.COMPLETED
                        : TaskStatus.PENDING
        );
        sb.append(" | ");
        // Task description
        sb.append(task.getDescription());
        // Task Details (if any)
        String taskDetails = task.getTaskDetails();
        if (taskType == TaskType.DEADLINE || taskType == TaskType.EVENT) {
            sb.append(" | ");
            sb.append(taskDetails);
        }
        return sb.toString();
    }
}
