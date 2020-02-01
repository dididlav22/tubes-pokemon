import java.io.*;
import java.util.*;

class Pokemon extends Tools{
	String nickname;
	String name;
	String type;
	int hp;
	String weakness;
	int energy;
	ArrayList<Attack> attacks = new ArrayList<Attack>();;
	int xp;

	Pokemon (String mName, String mType, String mWeakness, int mHp, int mEnergy){
		name = mName;
		type = mType;
		weakness = mWeakness;
		hp = mHp;
		energy = mEnergy;

        // System.out.println("name: " + name + "| type: " + type);
	}

	public boolean isAlive () {
		return hp > 0;
	}

	public void listAttack() {
		for (int i = 0; i < attacks.size(); i++) {
			delayedLinePrint(new String[] {
				String.format("%d. %s", (i + 1), attacks.get(i).name),
				String.format("COST    : %d", attacks.get(i).cost),
				String.format("DAMAGE  : %d", attacks.get(i).damage),
				// String.format("SPECIAL : %s\n", attacks.get(i).special.equals("") ? "NONE" : attacks.get(i).special.toUpperCase())	
			}, 20);
		}
	}

	/**
     * Checks if user can afford attack
     * 
     * @param attack     Attack object
     * @return           Boolean if attack can be afforded
     */
    public boolean canAfford (Attack attack) {
        return attack.cost <= energy;
    }

    /**
	 * Prints statistics about Pokemon in a nice table
	 *
	 * @param fancy     Boolean if table should be printed
	 * @return          String with hp and energy
	 */
	public void stats() {
		// if (fancy) {
			delayedLinePrint(new String[] {
				"\n+-----------------------------------------------+",
				"|                    STATS                      |",
				"+===============================================+",
				String.format("| HP: %5d | ENERGY : %5d | TYPE: %10s |", hp, energy, type.toUpperCase()),
				"+-----------------------------------------------+\n"
			}, 20);
		
	}
}
