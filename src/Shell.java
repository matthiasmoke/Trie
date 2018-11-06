import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *Main class for representing shell
 */
public final class Shell {

    private static Trie trie;
    private static boolean run;

    /**
     *Private constructor
     */
    private Shell(){

    }

    /**
     *Main method that executes the shell
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader shellReader = new BufferedReader(
                new InputStreamReader(System.in));
        runShell(shellReader);
    }

    /**
     *Method that runns the shell
     * @param reader Reader for user input
     * @throws IOException
     */
    private  static  void runShell(BufferedReader reader) throws IOException {
        run = true;
        trie = new Trie();

        while (run) {
            System.out.print("trie> ");
            String input = reader.readLine();

            if (input != null) {
                evalInput(input);
            }
        }
    }

    /**
     *Evals the input of the user and executes the given command
     * @param input userinput
     */
    private static void evalInput(String input) {
        Scanner sc =  new Scanner(input);
        sc.useDelimiter("\\s+");

        if (sc.hasNext()){

            String studentName = "";
            int points = -1;

            switch (sc.next()){
                case "new":
                    trie = new Trie();
                    break;

                case "add":
                    if (sc.hasNext()) {
                        studentName = sc.next();
                    }

                    if (sc.hasNextInt()) {
                        points = sc.nextInt();
                    }

                    add(studentName, points);
                    break;

                case "change":
                    if (sc.hasNext()) {
                        studentName = sc.next();
                    }

                    if (sc.hasNextInt()) {
                        points = sc.nextInt();
                    }

                    change(studentName, points);
                    break;

                case "delete":
                    if (sc.hasNext()) {
                        studentName = sc.next();
                    }
                    delete(studentName);
                    break;

                case "points":
                    if (sc.hasNext()) {
                        int p = trie.points(sc.next());
                        if (p >= 0) {
                            System.out.println(p);
                        } else {
                            System.out.println("Error! No value saved");
                        }

                    }
                    break;

                case "trie":
                    System.out.println(trie.toString());
                    break;

                case "help":
                    printHelpInfo();
                    break;

                case "quit":
                    run = false;
                    break;

                default:
                    printErrorMessage();
            }
        }
        sc.close();
    }

    private static void add(String name, Integer points) {
        if (!name.equals("") && points >= 0) {
            if (!trie.add(name, points)) {
                System.out.println("Error! Could not add " + name);
            }
        } else {
            printErrorMessage();
        }
    }

    private static void change(String name, Integer points) {
        if (!name.equals("") && points >= 0) {
            if (!trie.change(name, points)) {
                System.out.println("Error! Could not change " + name);
            }
        } else {
            printErrorMessage();
        }
    }

    private static void delete(String name) {
        if (!trie.remove(name)) {
            System.out.println("Error! Could not delete " + name);
        }
    }

    private static void printErrorMessage() {
        System.out.println("Error! No valid input");
    }

    /**
     *Prints an info-message for the user
     */
    private static void printHelpInfo() {

        String[] commands = {"new", "add <name> <points>",
                "change <name> <points>", "delete <name>", "points <points>",
                "trie", "help", "quit"};

        System.out.println("Following commands can be executed:");
        for (String s : commands) {
            System.out.println("- " + s);
        }
    }
}
