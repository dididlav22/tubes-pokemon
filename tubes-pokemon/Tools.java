import java.util.*;
import java.io.*;

/**
 * Tools class for text or method print
 */
class Tools {	
    protected static Scanner stdin = new Scanner(System.in);
    protected static Console console = System.console();

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

    

}