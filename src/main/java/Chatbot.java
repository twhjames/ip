public class Chatbot {
    private final String name;

    public Chatbot(String name) {
        this.name = name;
    }

    public void start() {
        printWelcomeMessage();
        printGoodbyeMessage();
    }

    private void printWelcomeMessage() {
        String logo = """
             _   _ _____ _     _____  __
            | | | | ____| |   |_ _\\ \\/ /
            | |_| |  _| | |    | | \\  / 
            |  _  | |___| |___ | | /  \\ 
            |_| |_|_____|_____|___/_/\\_\\
            """;

        printLine();
        System.out.println(logo);
        System.out.println(
                "\uD83E\uDD16 [Helix] Hello! I'm your personal assistant, "
                        + this.name + "."
        );
        System.out.println("\uD83E\uDD16 [Helix] What can I do for you today?");
        printLine();
    }

    private void printGoodbyeMessage() {
        System.out.println(
                "\uD83E\uDD16 [Helix] Bye! Hope to see you again soon! \uD83D\uDC4B"
        );
        System.out.println("✨ Thank you for using Helix. Have a great day! ✨");
        printLine();
    }

    private void printLine() {
        System.out.println("=========================================================");
    }
}
