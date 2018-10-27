import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public final class Shell {

    private static Trie trie;
    private static final String[] commands = {"new", "add <name> <points>", "change <name> <points>", "delete <name>", "points <points>", "trie", "help", "quit"};

    public static void main(String[] args) throws IOException {
        BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
        runShell(shellReader);
    }

    private  static  void runShell(BufferedReader reader) throws IOException{
        boolean run = true;

        while (run){
            System.out.println("trie> ");
            String input = reader.readLine();

            if(input != null) {
                evalInput(input);
            }
        }
    }

    /**
     * Evals the input of the user and executes the given command
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
                        trie.add(studentName, points);
                    }
                    break;

                case "change":
                    if(sc.hasNext())
                        studentName = sc.next();

                    if (sc.hasNextInt())
                        points = sc.nextInt();

                    if(!studentName.equals("") && points > 0){
                        trie.change(studentName, points);
                    }
                    break;

                case "delete":
                    if(sc.hasNext())
                        trie.remove(sc.next());
                    break;

                case "points":
                    if(sc.hasNext())
                        trie.points(sc.next());
                    break;

                case "trie":
                    trie.toString();
                    break;

                case "help":
                    printHelpInfo();
                    break;

                case "quit":

                    break;

                default:
                    System.out.println("Error! No valid input");
            }
        }

        sc.close();
    }

    private static void printHelpInfo() {
        System.out.println("");
    }
}
