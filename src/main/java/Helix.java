import command.Command;
import command.CommandFactory;
import enums.OutputSymbol;
import enums.ExecutionStatus;
import exception.HelixException;
import storage.Storage;
import task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the Helix personal assistant application.
 */
public class Helix {
    public static void main(String[] args) {

        // initialise variables
        TaskList taskList = null;
        String filePath = "./data/helix_tasklist.txt";
        Storage storage = new Storage(filePath);

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
        String helixSymbol = OutputSymbol.HELIX.getSymbol();
        String userSymbol = OutputSymbol.USER.getSymbol();
        String warningSymbol = OutputSymbol.WARNING.getSymbol();

        Scanner sc = new Scanner(System.in);
        ExecutionStatus isExit = ExecutionStatus.CONTINUE;

        // load task from storage
        try {
            taskList = new TaskList(storage);
        } catch (IOException e) {
            System.out.println(
                    warningSymbol + " [Helix] : Failed to load tasks. Starting with an empty list."
            );
        }

        // print introduction message
        System.out.println(logo);
        System.out.println(
                helixSymbol + " [Helix] : Hello! I'm your personal assistant, Helix."
        );
        System.out.println(
                helixSymbol + " [Helix] : What can I do for you today?\n"
        );

        // run chat loop
        while (isExit != ExecutionStatus.EXIT) {
            // retrieve user input
            System.out.print(userSymbol + " [You]   : ");
            String input = sc.nextLine();

            try {
                // instantiate command.Command object
                Command command = CommandFactory.createCommand(input);

                // execute command.Command using polymorphism
                command.execute(taskList);
                isExit = command.isExit();
            } catch (HelixException e) {
                System.out.println(e.getFormattedMessage());
            } catch (Exception e) {
                System.out.println(
                        warningSymbol + " [Helix] : An unexpected error occurred. Please try again."
                );
            }
        }

        // clean up resources
        sc.close();
    }
}
