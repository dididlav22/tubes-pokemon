import java.io.*;

class Character {
	int charPosX;
	int charPosY;
  int money;

	public void catchPokemon(Pokemon mPokemon) {
		myPokemon.add(mPokemon);
	}

  public void goForward(int x, int y) { // geser ke kanan
    // charPosY = charPosY + 1;
    System.out.println("Maju");
  }

  public void goBackward(int x, int y) { // geser ke kiri
    // charPosY = charPosY - 1;
    System.out.println("Mundur");
  }

  public void goLeft(int x, int y) { // geser ke atas
    // charPosX = charPosX - 1;
    System.out.println("Ke kiri");
  }

  public void goRight(int x, int y) { // geser ke bawah
    // charPosX = charPosX + 1;
    System.out.println("Ke kanan");
  }

}
