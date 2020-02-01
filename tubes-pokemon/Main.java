import java.util.*;
import java.io.*;

/**
 * Pokemon main class
 */
public class Main extends Engine{
    Character player = new Character();
    ArrayList<Pokemon> allPokemon = new ArrayList<Pokemon>();
    ArrayList<Character> allEnemyLevel1 = new ArrayList<Character>();
    ArrayList<Character> allEnemyLevel2 = new ArrayList<Character>();
    ArrayList<Character> allEnemyLevel3 = new ArrayList<Character>();

    public static void main (String[] args) {
        Main game = new Main();
        PokeCenter pokeCenter = new PokeCenter();

        // game.loadFromFilePokemon(game);
        // game.createEnemy(game);

        // game.intro(game);
        // game.createPlayer(game);

        // test PokeCenter
        pokeCenter.healAll();

        // Demo battle sequence
        // String starter = game.rand.nextBoolean() ? "player" : "enemy";
        // boolean statusWinning = game.battleSequence(game.allEnemyLevel2.get(2), starter, game);

    }
}
