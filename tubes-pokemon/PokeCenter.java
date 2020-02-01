class PokeCenter extends Tools {

	// ArrayList<Item> items = new ArrayList<>();

	void healAll(Pokemon targetPokemon, Main game) {

		delayedCharPrint(String.format(".............."), 100);
		System.out.println("DONE, All of your Pokemon Healed");
		for (Pokemon m:game.allPokemon) {
			targetPokemon.hp = m.hp;
		}

	}

	

}
