import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public final class Shell {

    private static Trie trie;
    private static boolean run;


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
    private  static  void runShell(BufferedReader reader) throws IOException{
        run = true;

        while (run){
            System.out.print("trie> ");
            String input = reader.readLine();

            if(input != null) {
                evalInput(input);
            }
        }
    }

    /**
     *Evals the input of the user and executes the given command
     * @param input userinput
     */
    private static void evalInput(String input){
        Scanner sc =  new Scanner(input);
        sc.useDelimiter("\\s+");

        if(sc.hasNext()){

            String studentName = "";
            int points = -1;

            switch (sc.next()){
                case "new":
                    trie = new Trie();
                    break;

                case "add":
                    if(sc.hasNext())
                        studentName = sc.next();

                    if (sc.hasNextInt())
                        points = sc.nextInt();

                    if(!studentName.equals("") && points > 0){
                        if(!trie.add(studentName, points)){
                            System.out.println("Error! Could not add " +
                                    studentName);
                        }
                    }
                    break;

                case "change":
                    if(sc.hasNext())
                        studentName = sc.next();

                    if (sc.hasNextInt())
                        points = sc.nextInt();

                    if(!studentName.equals("") && points >= 0){

                        if(!trie.change(studentName, points)){
                            System.out.println("Error! Could not change " +
                                    studentName);
                        }
                    }
                    break;

                case "delete":
                    if(sc.hasNext())
                        studentName = sc.next();
                        if(!trie.remove(studentName)){
                            System.out.println("Error! Data could not delete " +
                                    studentName);
                        }
                    break;

                case "points":
                    if(sc.hasNext())
                        trie.points(sc.next());
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
                    System.out.println("Error! No valid input");
            }
        }
        sc.close();
    }

    /**
     *Prints a info-message for the user
     */
    private static void printHelpInfo() {

        String[] commands = {"new", "add <name> <points>",
                "change <name> <points>", "delete <name>", "points <points>",
                "trie", "help", "quit"};

        System.out.println("Following commands can be executed:");
        for(String s : commands){
            System.out.println("- " + s);
        }
    }
}
