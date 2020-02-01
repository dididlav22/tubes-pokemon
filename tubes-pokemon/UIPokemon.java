import java.util.*;

class UIPokemon {
	
    private static Scanner stdin = new Scanner(System.in);

	public void header(String text) {
		System.out.println(" ");
		System.out.println("===========================================================");
		System.out.println("\t\t\t " + text);
		System.out.println("===========================================================");
	}

	public int mainMenu() {
		int input = 0;

		// while(input)
		header("Pokemon");
		System.out.println("[1] Walk");
		System.out.println("[2] Bag");
		System.out.println("[3] Pokelot");
		System.out.println("[4] Save");
		System.out.println("[5] Help");
		System.out.println("[0] Exit");
		System.out.println(" ");
		input = inputInteger(0, 5, "Enter menu: ");

		return input;
	}

	public String inputString(String input) {
		String data;
		System.out.println(input);
		data = stdin.nextLine();
		return data;
	}

	public int inputInteger(int min, int max, String input) {
		int data;

		while (true) {
			System.out.println(input);
			String n = stdin.nextLine();

			// Try to parse input as an integer
			try {
				data = Integer.parseInt(n);
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number!");
				continue; // Skip to next iteration
			}

			// If the number is not an acceptable range
			if (data < min || data > max) {
				System.out.printf("That's not an option! Enter a number in the range of %d and %d!\n", min, max);
			} else {
				break; // Valid input 
			}

		}

		return data;
	}

	public static void main(String[] args) {
		UIPokemon mUi = new UIPokemon();
		mUi.mainMenu();
	}

}