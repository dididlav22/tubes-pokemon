import java.util.*;
import java.io.*;

class Engine extends Tools{
    Random rand = new Random();


	public String[] pokeTitle = { 
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
     * Constructs a new pokemon and attack with a line from the data file
     * 
     * @param data     String line of data from the "resources/pokemon.txt" data file
     */
    public static void processLine(String data, Main game) {

        String[] content          = data.split(","); // Data line

        // Pokemon attributes
        String name               = content[0];
        String type               = content[1];
        String weakness           = content[2];
        int hp                    = Integer.parseInt(content[3]);
        int energy                    = Integer.parseInt(content[4]);
        // int attackNums            = Integer.parseInt(content[4]);

        game.allPokemon.add(new Pokemon(name, type, weakness, hp, energy));
        // System.out.println("name: " + name + "| type: " + type);

        Scanner inFile;

        // Try and catch the IOException
        try {
            inFile = new Scanner(new BufferedReader(new FileReader("resources/"+name+".txt")));
        } catch (IOException e) {
            System.out.println("Could not loadfile 'resources/"+name+".txt'!");
            System.exit(-1); // Exit program if file could not be found
            return;
        }

        // // Create a Pokemon[] based on the number of Pokemon
        int pokeAttack = inFile.nextInt();
        // Pokemon[] pokemon = new Pokemon[pokeNum];

        inFile.nextLine(); // Skip to next line

        for (int i = 0; i < pokeAttack; i++) {
            // Pass string to processLine method
            processLineAttack(inFile.nextLine(), game);
        }

        inFile.close();

    }

    /**
     * Constructs a new pokemon and attack with a line from the data file
     * 
     * @param data     String line of data from the "resources/pokemon.txt" data file
     */
    public static void processLineAttack(String data, Main game) {

        String[] content          = data.split(","); // Data line



        // Pokemon attributes
        String name               = content[0];
        // String type               = content[1];
        // String weakness           = content[2];
        // int hp                    = Integer.parseInt(content[3]);
        int cost                 = Integer.parseInt(content[1]);
        int attackNum            = Integer.parseInt(content[2]);

        Pokemon curPokemon = game.allPokemon.get(game.allPokemon.size() - 1);
        curPokemon.attacks.add(new Attack(name, cost, attackNum));
    }

    
    /**
     * Displays a set of options in a nice table
     * 
     * @param options     String[] of options
     * @param message     Message to be displayed
     */
    public static void listOptions (String[] options, String message) {

        int limit = options.length;

        delayedCharPrint(message, 30);

        // Create table
        System.out.println("+---------------------------------+");
        for (int i = 0; i < limit; i++) {
            if (i + 1 < limit) {
                delayedCharPrint(String.format("| %d. %11s | %d. %11s |", (i + 1), options[i], (i + 2), options[i + 1]), 5);
                i += 1;
                System.out.println("+---------------------------------+");
            }
        }

    }

    public static void intro(Main game) {
        game.delayedLinePrint(game.pokeTitle, 40);
        game.sleep(700);
        game.getString("enter", "Press [enter] to start...", false);
        game.delayedLinePrint(new String[] { "Welcome to the Pokemon Arena!", "Be prepared for what lies ahead!", "Let's start your journey...", "\n" }, 20);
        game.sleep(1000);
    }

    /**
     * Displays and numbers Pokemon in a styled table
     * 
     * @param pokemons     ArrayList of Pokemon objects
     */
    public static void listPokemon (ArrayList<Pokemon> pokemons) {

        int i = 1; // The number of Pokemon


        delayedCharPrint("\nYour Pokemon:\n", 30);

        for (Pokemon m : pokemons) {
            delayedCharPrint(i + " " + m.name, 40);
            i++;
        }

        delayedCharPrint("\n", 30);

    }

    void createPlayer(Main game) {
    	ArrayList<Pokemon> temp = new ArrayList<>();
        
    	temp.add(game.allPokemon.get(game.randomInt(2, 11)));
    	temp.add(game.allPokemon.get(game.randomInt(2, 11)));
    	temp.add(game.allPokemon.get(game.randomInt(2, 11)));

    	String name = game.inputString("Enter the character name: ");

        System.out.println("Select the pokemon: ");
        listPokemon(temp);

        System.out.println("\n");

        int firstPokemon = game.inputInteger(1, 3, "Enter your pokemon: ");

        game.player.money = 5000;
        game.player.name = name;

        game.player.catchPokemon(game.allPokemon.get(1));
        game.player.catchPokemon(game.allPokemon.get(firstPokemon));
        // game.player.myPokemon.get(0).stats();
    }

    public void loadFromFilePokemon(Main game) {
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
            processLine(inFile.nextLine(), game);
        }

        inFile.close();

        // return allPokemonTemp;
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

	public boolean battleSequence(Character enemy, String starter, Main game) {

        /**
         * TODO:
         * - choose pokemon from pokelot
         * - choose menu
         * -
         */
        delayedCharPrint("---------------------------------------------------\n", 30);
        delayedCharPrint("                "+game.player.name+"    vs    "+enemy.name+"                \n", 30);
        delayedCharPrint("                 Battle Start!                  \n", 30);
        delayedCharPrint("---------------------------------------------------\n", 30);

        boolean isWinning = false;
        Pokemon playerPokemon = choosePokemon(game.player.myPokemon, game);
        Pokemon enemyPokemon = enemy.myPokemon.get(game.randomInt(0, enemy.myPokemon.size()-1));
        int attackCount;
        int selection;
        Attack curAttack;

        while (playerPokemon.isAlive() && enemyPokemon.isAlive()) {
            boolean moveOn = false;

            if (starter.equals("player")) {
                while(true) {
					delayedCharPrint("\nYour Turn!\n", 30);
                    // playerPokemon.stats();
                    // enemyPokemon.stats();
                    game.listOptions(new String[] {
                        "Attack",
                        "Retreat",
                        "Pass",
                        "Stats",
                        "View",
                        "Help"
                    }, "\nSelect your action!");

                    int curAction = game.inputInteger(1, 6, "Enter number: ");

                    switch (curAction) {
                        // Attack
                        case 1:
                            while(true) {
                                delayedCharPrint("\nENTER < 0 > TO GO BACK\n", 30);
                                delayedCharPrint("Select an attack! Here are your options:\n", 30);
								
								playerPokemon.listAttack(); // Display attack options

								// Get attack
								attackCount = playerPokemon.attacks.size();
								selection = inputInteger(0, attackCount, "\nEnter number: ");

								// // Go back
								if (selection == 0) {
									moveOn = false;
									break;
								}

								// // Try to attack
								curAttack = playerPokemon.attacks.get(selection - 1);

								// // Try to attack enemy
								if (playerPokemon.canAfford(curAttack)) {
									curAttack.attack(playerPokemon, enemyPokemon);
									moveOn = true;
									break;
								} else {
									delayedCharPrint(String.format("\nYou cannot affort that attack!\nIt costs %s!", curAttack.cost), 30);
								}
                    		}
                    	break;

                        case 2:
                            delayedCharPrint("You retreated!\n", 30);
                            playerPokemon = choosePokemon(game.player.myPokemon, game);
                            moveOn = true; // Switch turns
                        break;

                        case 3:
                            delayedCharPrint("You passed your turn!", 30);
                            moveOn = true;
                        break;

                        case 4:
                            playerPokemon.stats();
                        break;
                    }

                    starter = "enemy";

                    if (moveOn) {
                        break;
                    }

                    delayedCharPrint("---------------------------------------------------\n", 30);
                }

                // Check if enemy is dead
                if (!enemyPokemon.isAlive()) {

                    delayedCharPrint(String.format("The Enemy %s fainted!", enemyPokemon.name.toString()), 30);

                    // Remove enemy Pokemon
                    enemy.defeatPokemon(enemyPokemon);

                    // // If the user has Pokemon that have not fainted
                    // if (enemy.myPokemon.size() > 0) {
                    //     isWinning = false;
                    // }

                    // if (!isWinning) {

                    //     // Choose new Pokemon
                    //     delayedCharPrint("Oh no! All enemy Pokemon have fainted!choose pokemon enemy", 30);
                    //     enemyPokemon = enemy.myPokemon.get(game.randomInt(0, enemy.myPokemon.size()-1));
                    //     moveOn = true;
                    //     starter = "enemy"; // Player starts
                    //     // continue;
                    //     // isWinning = game.battleSequence(game.allEnemyLevel1.get(2), starter, game);
                    // } else {
                    //     delayedCharPrint("Oh no! All enemy Pokemon have fainted!", 30);
                    //     // return false; // 
                    // }

                    // isWinning = true;
                    break;
                }

            } else {
                
                delayedCharPrint("\nEnemy Turn!\n", 30);

                // Get attack
                attackCount = playerPokemon.attacks.size();
                selection = game.randomInt(1, attackCount);

                // // Try to attack
                curAttack = playerPokemon.attacks.get(selection - 1);

                // // Try to attack enemy
                if (playerPokemon.canAfford(curAttack)) {
                    curAttack.attack(enemyPokemon, playerPokemon);
                    moveOn = true;
                    // break;
                } else {
                    delayedCharPrint(String.format("\nEnemy cannot affort that attack!\nIt costs %s!", curAttack.cost), 30);
                }


                starter = "player"; // User starts

                // Check if user is fainted
                if (!playerPokemon.isAlive()) {

                    delayedCharPrint(String.format("%s fainted!", playerPokemon.name.toString()), 30);
                    isWinning = false;

                    // Remove user Pokemon
                    game.player.defeatPokemon(playerPokemon);

                    // If the user has Pokemon that have not fainted
                    if (game.player.myPokemon.size() > 0) {
                        isWinning = true;
                    }

                    if (isWinning) {

                        // Choose new Pokemon
                        playerPokemon = choosePokemon(game.player.myPokemon, game);
                        moveOn = false;
                        // starter = "player"; // Player starts
                        // continue;
                        // isWinning = game.battleSequence(game.allEnemyLevel1.get(2), starter, game);
                    } else {
                        delayedCharPrint("Oh no! All your Pokemon have fainted!", 30);
                        // return false; // 
                    }
                }

                delayedCharPrint("---------------------------------------------------\n", 30);
            }

            // End of a turn
            // resetAllPokemon(enemy); // Reset Pokemon stats
            // starter = starter.equals("user") ? "enemy" : "user"; // Alternate turn


        }
        return isWinning;
    }

    public Pokemon choosePokemon(ArrayList<Pokemon> myPokemon, Main game) {
        Pokemon curPokemon;

        listPokemon(myPokemon);
        
        Integer numberPokemon = game.inputInteger(1, myPokemon.size(), "Choose your pokemon: ");
        curPokemon = myPokemon.get(numberPokemon-1);

        return curPokemon;
    }

    public int randomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
    
    public void createEnemy(Main game) {
        // Enemy Level 1
        // --------------------------------------------------------------
        game.allEnemyLevel1.add(new Character());
        game.allEnemyLevel1.get(0).name = "Vergil";
        game.allEnemyLevel1.get(0).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel1.add(new Character());
        game.allEnemyLevel1.get(1).name = "Amelia";
        game.allEnemyLevel1.get(1).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel1.add(new Character());
        game.allEnemyLevel1.get(2).name = "Brown";
        game.allEnemyLevel1.get(2).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel1.add(new Character());
        game.allEnemyLevel1.get(3).name = "Olivia";
        game.allEnemyLevel1.get(3).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel1.add(new Character());
        game.allEnemyLevel1.get(4).name = "Williams";
        game.allEnemyLevel1.get(4).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        // // --------------------------------------------------------------
        // // Enemy Level 2
        // // --------------------------------------------------------------
        game.allEnemyLevel2.add(new Character());
        game.allEnemyLevel2.get(0).name = "Ava";
        game.allEnemyLevel2.get(0).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel2.get(0).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel2.add(new Character());
        game.allEnemyLevel2.get(1).name = "Smith";
        game.allEnemyLevel2.get(1).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel2.get(1).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel2.add(new Character());
        game.allEnemyLevel2.get(2).name = "Mia";
        game.allEnemyLevel2.get(2).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel2.get(2).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel2.add(new Character());
        game.allEnemyLevel2.get(3).name = "Taylor";
        game.allEnemyLevel2.get(3).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel2.get(3).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel2.add(new Character());
        game.allEnemyLevel2.get(4).name = "Lily";
        game.allEnemyLevel2.get(4).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel2.get(4).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        // // --------------------------------------------------------------
        // // Enemy Level 3
        // // --------------------------------------------------------------
        game.allEnemyLevel3.add(new Character());
        game.allEnemyLevel3.get(0).name = "Lily";
        game.allEnemyLevel3.get(0).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(0).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(0).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.add(new Character());
        game.allEnemyLevel3.get(1).name = "Jones";
        game.allEnemyLevel3.get(1).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(1).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(1).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.add(new Character());
        game.allEnemyLevel3.get(2).name = "Isabella";
        game.allEnemyLevel3.get(2).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(2).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(2).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.add(new Character());
        game.allEnemyLevel3.get(3).name = "Emily";
        game.allEnemyLevel3.get(3).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(3).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(3).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.add(new Character());
        game.allEnemyLevel3.get(4).name = "Johnson";
        game.allEnemyLevel3.get(4).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(4).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(4).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.add(new Character());
        game.allEnemyLevel3.get(5).name = "Wilson";
        game.allEnemyLevel3.get(5).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(5).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        game.allEnemyLevel3.get(5).catchPokemon(game.allPokemon.get(game.randomInt(2, 11)));
        // // --------------------------------------------------------------
        // // Enemy Level 4
        // // --------------------------------------------------------------
        // Character sophia   = new Character();
        // sophia.name = "Sophia";
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // Character chloe  = new Character();
        // sophia.name = "Chloe";
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // Character thomas  = new Character();
        // sophia.name = "Thomas";
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // Character charlie = new Character();
        // sophia.name = "Charlie";
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // Character thompson  = new Character();
        // sophia.name = "Thompson";
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // sophia.catchPokemon(game.allPokemon.get(game.randomInt(2, 12)));
        // --------------------------------------------------------------
    }
}