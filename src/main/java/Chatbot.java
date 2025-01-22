import java.util.Scanner;

public class Chatbot {
    private final String name;

    public Chatbot(String name) {
        this.name = name;
    }

    public void start() {
        printWelcomeMessage();
        runChatLoop();
        printGoodbyeMessage();
    }

    private void printWelcomeMessage() {
        String logo = """
           ╔════════════════════════════════════════════════════════╗
           ║                                                        ║
           ║             _   _  _____  _      ___ __  __            ║
           ║            | | | || ____|| |    |_ _|\\ \\/ /            ║
           ║            | |_| ||  _|  | |     | |  \\  /             ║
           ║            |  _  || |___ | |___  | |  /  \\             ║
           ║            |_| |_||_____||_____||___|/_/\\_\\            ║
           ║                                                        ║
           ╚════════════════════════════════════════════════════════╝
           """;

        System.out.println(logo);
        System.out.println(
                "\uD83E\uDD16 [Helix] : Hello! I'm your personal assistant, "
                        + this.name + "."
        );
        System.out.println("\uD83E\uDD16 [Helix] : What can I do for you today?\n");
    }

    private void printGoodbyeMessage() {
        System.out.println(
                "\uD83E\uDD16 [Helix] : Bye! Hope to see you soon! \uD83D\uDC4B\n"
        );
        printLine();
        System.out.println("✨ Thank you for using Helix. Have a great day! ✨");
        printLine();
    }

    private void printLine() {
        System.out.println("=========================================================");
    }

    private void runChatLoop() {
        Scanner sc = new Scanner(System.in);
        Command command;
        String input;

        while (true) {
            System.out.print("👤 [You]   : ");
            input = sc.nextLine();
            command = new Command(input);

            if (command.isExit()) {
                break;
            }
            command.executeCommand();
        }

        sc.close();
    }
}
