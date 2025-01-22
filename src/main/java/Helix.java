import command.Command;
import command.CommandFactory;
import util.TaskList;

import java.util.Locale;
import java.util.Scanner;

/**
 * The main class for the Helix personal assistant application.
 */
public class Helix {
    public static void main(String[] args) {

        // initialise variables
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
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

        // print introduction message
        System.out.println(logo);
        System.out.println(
                "\uD83E\uDD16 [Helix] : Hello! I'm your personal assistant, Helix."
        );
        System.out.println("\uD83E\uDD16 [Helix] : What can I do for you today?\n");

        // run chat loop
        while (!isExit) {
            // retrieve user input
            System.out.print("👤 [You]   : ");
            String input = sc.nextLine().trim().toLowerCase(Locale.ROOT);

            // instantiate command.Command object
            Command command = CommandFactory.createCommand(input);

            // execute command.Command using polymorphism
            command.execute(taskList);
            isExit = command.isExit();
        }

        // clean up resources
        sc.close();
    }
}
