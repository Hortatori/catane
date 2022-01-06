package catane;

import java.util.Random;

public class IA {
	Random rd = new Random();
	Joueur joueur;
	boolean graphique;
	InterfaceJoueur ij; // pour un accès facile aux méthodes de construction

	public IA(Joueur j) {
		this.joueur = j;
		graphique = j.partie.plateau.graphique;
		ij = j.partie.ij;
	}

	public void takeDecision() {
		Ressources r = this.joueur.getR();
		int decision = rd.nextInt(8);
		switch (decision) {

		case 1:
			if ((r.getBle() > 0) && (r.getBois() > 0) && (r.getMouton() > 0) && (r.getArgile() > 0)) {
			ij.construireColonie(joueur, getCoord());
			r.payArgile(1);
			r.payBle(1);
			r.payBois(1);
			r.payMouton(1); }
			
			break;

		case 2:
			if ((r.getBle() > 1) && (r.getPierre() > 2) ) {
			ij.construireVille(joueur, getCoord());
			r.payPierre(3);
			r.payBle(2); }
			break;

		case 3:
			if ((r.getBois() > 1) && (r.getArgile() > 1) ) {
			ij.construireRoute(joueur, getCoord(), getCoord());
			r.payArgile(1);
			r.payBois(1);}
			break;

		case 4:
			OperationCommerciale tc = new OperationCommerciale(joueur);
			tc.effectuer();
			break;
			
		case 5:
			if (joueur.partie.pioche.pioche.isEmpty()) {
				joueur.partie.Communicate("la pioche est vide ! ");
				break;
			}
			
			
			Carte c = joueur.partie.pioche.pioche.getFirst();
			joueur.cartes.add(c);
			c.setPossesseur(joueur);
			joueur.partie.pioche.pioche.removeFirst();
			joueur.afficherCartes();
			
			break;

		case 6:
			
			int ncarte = rd.nextInt(joueur.cartes.size() + 1);

			if (ncarte == 0) {
				break;
			}
			if (ncarte > joueur.cartes.size() + 1) {
				ncarte = rd.nextInt(joueur.cartes.size() + 1);
			}

			else {
				joueur.cartes.get(ncarte - 1).Jouer();
			}
			break;

		case 7:
			return;

		}
	}
	
	
	
	
	
	
	
	
	
// Pour les fonctions qui demandent un entier et le convertissent elle-mêmes en ressource
	public int demandeRessourceI() {
		return rd.nextInt(6); 
	}

	public Paysage demandeRessource() {
		int scan = rd.nextInt(6);
		switch (scan) {
		case 1:
			return Paysage.FORET;

		case 2:
			return Paysage.MONTAGNE;

		case 3:
			return Paysage.COLLINE;

		case 4:
			return Paysage.PRE;

		case 5:
			return Paysage.CHAMP;

		default:
			System.out.println(" IA fait des choses bizarres.");
			return Paysage.DESERT;
		}
	}

	public int[] getCoord() {
		int x = rd.nextInt(5);
		int y = rd.nextInt(5);
		int tab[] = { x, y };
		return tab;
	}

	// si on demande le changement du voleur qui va sur une case et pas un sommet (un indice de moins)
	public int[] getCoord(boolean siCase) {
		int x = rd.nextInt(4);
		int y = rd.nextInt(4);
		int tab[] = { x, y };
		return tab;
	}

}
