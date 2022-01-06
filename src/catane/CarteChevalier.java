package catane;

public class CarteChevalier extends Carte {

	public CarteChevalier() {
		this.effet = "Permet de faire fuir le voleur et de renforcer son armée";

	}

	@Override
	protected void actionCarte() {
		Joueur j = this.getPossesseur();
		j.getR().incrementeChevalier();
		for (Joueur jou : j.partie.joueurs) {
			if (jou.getR().getChevalier() < jou.getR().getChevalier() && jou.maxcavalier) {
				jou.maxcavalier = false;
				j.maxcavalier = true;
			}

		}
		j.partie.vo.Voleurfuite(j);

	}

}