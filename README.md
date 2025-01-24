### Helix Chatbot: Your Personal Task Manager

Helix is a simple, intuitive, and interactive chatbot designed to help you manage your tasks efficiently. Whether you're adding to-dos, setting deadlines, or planning events, Helix is here to assist you every step of the way.

---

## Features

1. **Task Management**:
   - Add tasks to your task list (To-Dos, Deadlines, Events).
   - Mark tasks as completed or unmark them.
   - Delete tasks when no longer needed.

2. **Interactive Commands**:
   - View a list of all your tasks.
   - Exit the chatbot with a friendly farewell message.

More features pending.

---

## Getting Started

### Prerequisites
- **Java Development Kit (JDK)** version 17 .
- A terminal or an IDE like IntelliJ IDEA, Eclipse, or VS Code.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/twhjames/ip.git
   ```
2. Navigate to the project directory:
   ```bash
   cd ip
   ```
3. Compile the project:
   ```bash
   javac -d bin $(find . -name "*.java")
   ```
4. Run the chatbot:
   ```bash
   java -cp bin Helix
   ```

---

## Usage

Here are the commands you can use with Helix:

| Command       | Description                                                                 |
|---------------|-----------------------------------------------------------------------------|
| `todo <description>` | Adds a simple to-do task. Example: `todo Read a book`                   |
| `deadline <description> /by <due date>` | Adds a task with a deadline. Example: `deadline Submit report /by 2025-01-30` |
| `event <description> /from <start time> /to <end time>` | Adds an event task. Example: `event Team meeting /from 10am /to 12pm` |
| `list`        | Displays all the tasks in your list.                                        |
| `mark <task number>` | Marks the specified task as done. Example: `mark 1`                      |
| `unmark <task number>` | Marks the specified task as not done. Example: `unmark 1`              |
| `delete <task number>` | Deletes the specified task. Example: `delete 1`                       |
| `bye`         | Exits the chatbot.                                                         |

---

## Project Structure

```
src/
├── command/
│   ├── AddCommand.java
│   ├── Command.java
│   ├── CommandFactory.java
│   ├── DeleteCommand.java
│   ├── ExitCommand.java
│   ├── ListCommand.java
│   ├── MarkCommand.java
│   ├── UnmarkCommand.java
├── enums/
│   ├── CommandType.java
│   ├── ExecutionStatus.java
│   ├── OutputSymbol.java
│   ├── TaskStatus.java
│   └── TaskType.java
├── exception/
│   ├── HelixException.java
│   ├── InvalidCommandException.java
│   ├── InvalidNumberFormatException.java
│   ├── MissingArgumentException.java
│   ├── TaskIndexOutOfBoundsException.java
│   └── TooManyArgumentsException.java
├── task/
│   ├── Deadline.java
│   ├── Event.java
│   ├── Task.java
│   ├── Todo.java
├── util/
│   └── TaskList.java
└── Helix.java
```

---

## Example Interaction

```
╔════════════════════════════════════════════════════════╗
║                                                        ║
║             _   _  _____  _      ___ __  __            ║
║            | | | || ____|| |    |_ _|\ \/ /            ║
║            | |_| ||  _|  | |     | |  \  /             ║
║            |  _  || |___ | |___  | |  /  \             ║
║            |_| |_||_____||_____||___|/_/\_\            ║
║                                                        ║
╚════════════════════════════════════════════════════════╝

🤖 [Helix] : Hello! I'm your personal assistant, Helix.
🤖 [Helix] : What can I do for you today?

👤 [You]   : todo Read a book
════════════════════════════════════
🗂️  Task Added!
════════════════════════════════════
  📋 Type: TODO
  📝 Description: Read a book

You now have 1 task(s) in your list.
════════════════════════════════════

👤 [You]   : event Team meeting /from 2pm /to 3pm
════════════════════════════════════
🗂️  Task Added!
════════════════════════════════════
  📋 Type: EVENT
  📝 Description: Team meeting
  🕒 From: 2pm
  🕒 To: 3pm

You now have 2 task(s) in your list.
════════════════════════════════════

👤 [You]   : deadline Submit assignment /by 2025-01-30
════════════════════════════════════
🗂️  Task Added!
════════════════════════════════════
  📋 Type: DEADLINE
  📝 Description: Submit assignment
  📅 Due: 2025-01-30

You now have 3 task(s) in your list.
════════════════════════════════════

👤 [You]   : list
🤖 [Helix] : Listing tasks...
════════════════════════════════════
📝 Task List:
    1. [T][ ] Read a book
    2. [E][ ] Team meeting (from: 2pm to: 3pm)
    3. [D][ ] Submit assignment (by: 2025-01-30)
════════════════════════════════════

👤 [You]   : mark 1
✅ [Helix] : Task marked as complete!
    [T][✔] Read a book

👤 [You]   : unmark 1
❌ [Helix] : Task marked as incomplete!
    [T][ ] Read a book

👤 [You]   : delete 1
════════════════════════════════════
🗑️  Task Removed!
════════════════════════════════════
  📋 Type: TODO
  📝 Description: Read a book
  🛠️ Task Status: PENDING

You now have 2 task(s) in your list.
════════════════════════════════════

👤 [You]   : bye
🤖 [Helix] : Bye! Hope to see you soon! 👋
=========================================================
✨ Thank you for using Helix. Have a great day! ✨
=========================================================
```