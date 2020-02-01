class Attack extends Tools{

	String name;
	int cost;
	int damage;

	Attack(String mName, int mCost, int mDamage) {
		name = mName;
		cost = mCost;
		damage = mDamage;
	}

	void attack(Pokemon predator, Pokemon prey) {
		// Stores attack damage factoring in all conditions
		int attackPower = damage;

		// Display attack name
		delayedCharPrint(String.format("%s uses %s against %s", predator.name.toString(), name, prey.name.toString()), 40);

		predator.energy -= cost; // Lower attacker's energy

		// If the attacker or defender has a weakness
		if (predator.type.equals(prey.weakness)) {
			delayedCharPrint(String.format("\nOh no! %s is weak to %s!\nThe attack was effective!", prey.name.toString(), predator.name), 40);
			attackPower *= 2; // Cut attack power in half
		}

		prey.hp -= attackPower;
		delayedCharPrint(String.format("%s dealt %d damage to %s!", predator.name.toString(), attackPower, prey.name.toString()), 40);
	}
}
