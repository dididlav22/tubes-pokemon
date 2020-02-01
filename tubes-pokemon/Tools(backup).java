import java.util.*;
import java.io.*;

/**
 * Tools class for text or method print
 */
class Tools {
	
    private static Scanner stdin = new Scanner(System.in);
    private static Console console = System.console();
    private static ArrayList<Pokemon> allPokemon = new ArrayList<>();

	public static String[] pokeTitle = { 
    "                               .::.                           ",
    "                              .;:**'                          ",
    "                              `                               ",
    "  .:XHHHHk.              db.   .;;.     dH  MX                ",
    "oMMMMMMMMMMM       ~MM  dMMP :MMMMMR   MMM  MR      ~MRMN     ",
    "QMMMMMb  'MMX       MMMMMMP !MX' :M~   MMM MMM  .oo. XMMM 'MMM",
    "  `MMMM.  )M> :X!Hk. MMMM   XMM.o'  .  MMMMMMM X?XMMM MMM>!MMP",
    "   'MMMb.dM! XM M'?M MMMMMX.`MMMMMMMM~ MM MMM XM `' MX MMXXMM ",
    "    ~MMMMM~ XMM. .XM XM`'MMMb.~*?**~ .MMX M t MMbooMM XMMMMMP ",
    "     ?MMM>  YMMMMMM! MM   `?MMRb.    `MM   !L'MMMMM XM IMMM   ",
    "      MMMX   'MMMM'  MM       ~%:           !Mh.''' dMI IMMP  ",
    "      'MMM.                                             IMX   ",
    "       ~M!M                                             IM    " 
    };

    /**
     * Pauses the program for a given duration
     * 
     * @param milliseconds The duration, in milliseconds, to pause the program
     */
    public static void sleep(long milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Prints a string to the console with a typing effect
     * 
     * @param contents     The contents to be printed
     * @param milliseconds Duration to sleep in milliseconds
     */
    public static void delayedCharPrint(String content, long milliseconds) {

        for (int i = 0; i < content.length(); i++) {

            // Don't sleep for spaces
            if (content.charAt(i) == ' ') {
                System.out.print(content.charAt(i));
            } else {
                System.out.print(content.charAt(i));
                sleep(milliseconds);
            }
        }

        System.out.println();

    }

    /**
     * Prints lines of multiline text with a delay
     * 
     * @param multiline    String array of lines to print
     * @param milliseconds Duration to sleep in milliseconds
     */
    public static void delayedLinePrint(String[] multiline, long milliseconds) {
        for (int i = 0; i < multiline.length; i++) {
            System.out.println(multiline[i]);
            sleep(milliseconds);
        }
    }

    /**
     * Method to get a string input
     * 
     * @param toFind Used to check if checking for enter pressed
     * @param inline Boolean to invoke new line
     * @return Input string
     */
    public static String getString(String toFind, String message, boolean inline) {

        String output = "";

        if (inline) {
            System.out.print(message);
        } else {
            delayedCharPrint(message, 30);
        }

        if (toFind.equals("enter")) {
            // Mask input to appear as only enter was pressed
            char[] hidden = console.readPassword("");
            output = "enter";
        } else {
            output = stdin.nextLine();
        }

        return output;
    }

    public static void intro() {
        Tools mTools = new Tools();

        mTools.delayedLinePrint(mTools.pokeTitle, 40);
        mTools.sleep(700);
        mTools.getString("enter", "Press [enter] to start...", false);
        mTools.delayedLinePrint(new String[] { "Welcome to the Pokemon Arena!", "Be prepared for what lies ahead!", "Let's start your journey...", "\n" }, 20);
        mTools.sleep(1000);
    }

    /**
     * Displays and numbers Pokemon in a styled table
     * 
     * @param pokemons     ArrayList of Pokemon objects
     */
    public static void listPokemon (ArrayList<Pokemon> pokemons) {

        int limit = pokemons.size(); // The number of Pokemon

        // Display data in a formatted table
        // Special formatting for spaces required for row with single and double digit numbers
        System.out.println(limit != 1 ? "+---------------------------------+" : "+----------------+");

        for (int i = 0; i < limit; i++) {

            // Display two Pokemon names side-by-side
            if (i + 1 < limit) {

                if (i > 9) {
                    delayedCharPrint(String.format("| %d. %10s | %d. %10s |", (i + 1), pokemons.get(i).toString(), (i + 2), pokemons.get(i + 1).toString()), 4);
                } else {
                    if (i == 8) {
                        delayedCharPrint(String.format("| %d. %11s | %d. %10s |", (i + 1), pokemons.get(i).toString(), (i + 2), pokemons.get(i + 1).toString()), 4);
                    } else {
                        delayedCharPrint(String.format("| %d. %11s | %d. %11s |", (i + 1), pokemons.get(i).toString(), (i + 2), pokemons.get(i + 1).toString()), 4);
                    }
                }

                i += 1;
                System.out.println("+---------------------------------+");

            // Display a row with only 1 Pokemon name
            } else {

                if (i > 9) {
                    delayedCharPrint(String.format("| %d. %10s |", (i + 1), pokemons.get(i).toString()), 4);
                    System.out.println("+----------------+");
                } else {
                    delayedCharPrint(String.format("| %d. %11s |", (i + 1), pokemons.get(i).toString()), 4);
                    System.out.println("+----------------+");
                }

                break;
            }

        }

    }

    void createPlayer() {
        
        System.out.println("Enter the character name: ");
        String name = stdin.nextLine();

        System.out.println("Select the pokemon: ");

        listPokemon(allPokemon);

    }

    /**
     * Constructs a new pokemon and attack with a line from the data file
     * 
     * @param data     String line of data from the "resources/pokemon.txt" data file
     */
    public static void processLine(String data) {

        String[] content          = data.split(","); // Data line

        // Pokemon attributes
        String name               = content[0];
        String type               = content[1];
        int hp                    = Integer.parseInt(content[2]);
        int attackNums            = Integer.parseInt(content[3]);

        // System.out.println("name: " + name + "| type: " + attackNums);
        allPokemon.add(new Pokemon(name, type, hp, attackNums));
    }

    public static void loadFromFilePokemon() {
        ArrayList<Pokemon> allPokemonTemp = new ArrayList<>();

        Scanner inFile;

        // Try and catch the IOException
        try {
            inFile = new Scanner(new BufferedReader(new FileReader("resources/pokemon.txt")));
        } catch (IOException e) {
            System.out.println("Could not loadfile 'resources/pokemon.txt'!");
            System.exit(-1); // Exit program if file could not be found
            return;
        }

        // Create a Pokemon[] based on the number of Pokemon
        int pokeNum = inFile.nextInt();
        Pokemon[] pokemon = new Pokemon[pokeNum];

        inFile.nextLine(); // Skip to next line

        for (int i = 0; i < pokeNum; i++) {
            // Pass string to processLine method
            processLine(inFile.nextLine());
        }

        inFile.close();

        // return allPokemonTemp;
    }
}