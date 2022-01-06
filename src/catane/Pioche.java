/** simule une piche de cartes */

package catane;

import java.util.Collections;
import java.util.LinkedList;

public class Pioche {
	LinkedList<Carte> pioche = new LinkedList<Carte>();

	public Pioche() {
		this.pioche.add(new CarteInvention());
		this.pioche.add(new CarteInvention());
		this.pioche.add(new CarteMonopole());
		this.pioche.add(new CarteMonopole());
		this.pioche.add(new CarteMonopole());
		this.pioche.add(new CarteMonopole());
		this.pioche.add(new CarteRoute());
		this.pioche.add(new CarteRoute());
		for (int i = 0; i < 6; i++) {
			this.pioche.add(new CarteVictoire());
		}

		// je n'ai pas trouvé combien de cavaliers il y avait alors j'en ai mis 15
		for (int i = 0; i < 16; i++) {
			this.pioche.add(new CarteChevalier());
		}
		Collections.shuffle(pioche);
		for (int i = 0; i < pioche.size(); i++) {
			Carte c = pioche.get(i);
			c.setNumero(i + 1);
		}
	}
}
