package catane;

public class CarteMonopole extends Carte{

	@Override
	protected void actionCarte() {
		Joueur j = this.getPossesseur();
		Partie p = j.partie ;
		if (this.getPossesseur().partie.plateau.graphique) {
		System.out.println("Tous les joueurs vont vous donner leur ressource d'un type de votre choix");
			
			
		}
		else {
			p.view.ResetCommunicator();
			p.view.Communicate("Tous les joueurs vont vous donner leur ressource d'un type de votre choix");
		}

		
	}
}