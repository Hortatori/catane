/**
 * InterfaceJoueur rassemble les méthodes qui récoltent les réponses du joueur et 
 * les méthodes qui contrôlent les actions du jeu. (avec leurs methodes de vérification de légalité des actions ou réponses)
 */

package catane;

import java.util.Scanner;

public class InterfaceJoueur {

	Scanner sc;
	Plateau plateau;
	Partie partie;

	public InterfaceJoueur(Scanner sc, Plateau p, Partie partie) {
		super();
		this.sc = sc;
		this.plateau = p;
		this.partie = partie;
	}

	public boolean answerYesNo(String scan) {

		while (!scan.equals("oui") && !scan.equals("non")) {
			System.out.println("répondez à la question posée. ");
			scan = this.sc.next();
		}
		if (scan.equals("oui")) {
			return true;
		}
		return false;
	}

	// on vérifie que la réponse rentre dans les coordonées du tableau (et nous
	// évite un INdexoutof bounds...
	public int answerInPlateau(int scan, int dimension) {
		while (scan > 4 || scan < 0) {
			System.out.println("cette coordonnee n'est pas valide");
			scan = this.sc.nextInt();
		}
		return scan;
	}

	public void actions(Joueur joueur) {

		System.out.println(joueur.getNom()
				+ ", souhaitez-vous :\n 1 : Construire une colonie? \n 2 : Construire une ville?  \n 3 : Construire une route? \n 4 : Faire du commerce? \n 5 : Acheter une carte? \n 6 : Jouer une carte ? \n 7 : Finir votre tour.");

		int scan = this.sc.nextInt();
		Ressources r = joueur.getR();
		switch (scan) {
			case 1:
				if ((r.getBle() > 0) && (r.getBois() > 0) && (r.getMouton() > 0) && (r.getArgile() > 0)) {

					r.payArgile(1);
					r.payBle(1);
					r.payBois(1);
					r.payMouton(1);
					construireColonie(joueur,
							getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonnées"));
				} else {
					System.out.println("vous n'avez pas assez de ressources");
				}
				break;
			case 2:
				if ((r.getBle() > 1) && (r.getPierre() > 2)) {

					r.payPierre(3);
					r.payBle(2);
					construireVille(joueur,
							getCoord(joueur.getNom() + ", pour placer une ville , donnez ses coordonnées"));
				} else {
					System.out.println("vous n'avez pas assez de ressources");
				}
				break;

			case 3:
				if ((r.getBois() > 1) && (r.getArgile() > 1)) {

					r.payArgile(1);
					r.payBois(1);

					construireRoute(joueur,
							getCoord(joueur.getNom() + ", pour placer une route , donnez les coordonnées du départ"),
							getCoord("et celle de l'arrivée"));
				} else {
					System.out.println("vous n'avez pas assez de ressources");
				}
				break;

			case 4:
				OperationCommerciale tc = new OperationCommerciale(joueur);
				tc.effectuer();
				break;

			case 5:
				if (this.partie.pioche.pioche.isEmpty()) {
					System.out.println("la pioche est vide ! ");
					break;
				}
				if ((r.getPierre() > 1) && (r.getMouton() > 1) && (r.getBle() > 1)) {
					r.payBle(1);
					r.payMouton(1);
					r.payPierre(1);
					Carte c = this.partie.pioche.pioche.getFirst();
					joueur.cartes.add(c);
					c.setPossesseur(joueur);
					this.partie.pioche.pioche.removeFirst();
					joueur.afficherCartes();
				}
				break;

			case 6:
				if (joueur.cartetour == true) {
					break;
				}
				joueur.afficherCartes();
				System.out.println("Donnez nous le numéro de la carte que vous voulez jouer (0 = annuler) ");
				int rep = sc.nextInt();
				if (rep == 0) {
					break;
				}
				if (rep > joueur.cartes.size() + 1) {
					System.out.println("votre indice dépasse le nombre de carte");
					rep = sc.nextInt();
				}

				else {
					joueur.cartes.get(rep - 1).Jouer();
					joueur.cartetour = true;
				}
				break;

			case 7:
				return;

			default:
				System.out.println("Ca cloche chez vous");
				actions(joueur);

		}
		actions(joueur);

	}

	public int[] getCoord(String question) {

		System.out.println(question);
		int scan = this.sc.nextInt();
		int X = answerInPlateau(scan, 0);
		scan = this.sc.nextInt();
		int Y = answerInPlateau(scan, 1);
		int[] result = { X, Y };
		return result;
	}

	public boolean construireColonie(Joueur joueur, int[] tab) {

		int X = tab[0] + 1;
		int Y = tab[1] + 1;
		// pb de l'indice donné par rapport au tableau des sommets sont le premier
		// élément est négatif. on rajoute 1 à cet endroit (et à d'autres dans le code
		// pour bien commencer le compte avec le sommet en haut à gauche du plateau.

		boolean flag = true;

		if (plateau.plateauS[Y][X].colonie) {
			System.out.println("Conditions non respectées ");
			System.out.println("il y a déjà une colonie ici ! ");
			flag = false;

		}
		if (X > 0) {
			if (plateau.plateauS[Y - 1][X].colonie) {
				System.out.println("Conditions non respectées ");
				System.out.println("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
				flag = false;
			}
		}
		if (Y > 0) {
			if ((plateau.plateauS[Y][X - 1].colonie)) {
				System.out.println("Conditions non respectées ");
				System.out.println("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
				flag = false;
			}
		}

		if ((plateau.plateauS[Y + 1][X].colonie) || (plateau.plateauS[Y][X + 1].colonie)) {
			System.out.println("Conditions non respectées ");
			System.out.println("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
			flag = false;

		}

		if (flag) {
			joueur.placerColonieInit(plateau.plateauS[Y][X]);

			if (this.plateau.graphique == true) {

				partie.plateau.vp.drawColonie(joueur, plateau.plateauS[Y][X]);

				this.partie.view.repaint();

			}
			plateau.afficherPlateau();

		} else {
			if (this.plateau.graphique == false) {
				if (joueur.isIa()) {
					construireColonie(joueur, joueur.matrix4.getCoord());
				} else {
					construireColonie(joueur,
							getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonnées"));
				}
			}
		}
		return flag;
	}

	public void construireVille(Joueur joueur, int[] tab) {

		int X = tab[0] + 1;
		int Y = tab[1] + 1;
		Sommet considere = plateau.plateauS[Y][X];

		if ((considere.colonie) && (joueur.colonies.contains(considere)) && (considere.ville == false))

		{
			joueur.placerVilleInit(plateau.plateauS[Y][X]);

			if (plateau.graphique) {
				partie.plateau.vp.drawVille(joueur, considere);

			}

			plateau.afficherPlateau();
		} else {
			System.out.println("Vous devez posséder une colonie pour l'améliorer en ville");
			if (joueur.isIa()) {
				construireVille(joueur, joueur.matrix4.getCoord());
			} else {

				construireVille(joueur,
						getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonnées"));
			}
		}
	}

	public void construireRoute(Joueur joueur, int[] tabd, int[] tabf) {
		System.out.println(
				joueur.getNom() + ", pour placer une Route, donnez les coordonnées de début et de fin de la route");

		int debutX = tabd[0] + 1;
		int debutY = tabd[1] + 1;
		int finX = tabf[0] + 1;
		int finY = tabf[1] + 1;

		// dans le tableau , le premier indice est celui de la hauteur
		Sommet s1 = plateau.plateauS[debutY][debutX];
		Sommet s2 = plateau.plateauS[finY][finX];
		// s1.AfficherCoord();
		// s2.AfficherCoord();
		boolean flag = false;

		if ((joueur.colonies.contains(s1)) || (joueur.colonies.contains(s2))) {
			flag = true;
		}
		for (Route r : joueur.routes) {
			if ((r.depart == s1) || (r.depart == s2) || (r.arrivee == s1) || (r.arrivee == s1)) {
				flag = true;
			}

		}

		if (s1.routeLegale(s2) == false) {
			flag = false;
			System.out.println("une route doit être de longueur 1 et relier deux sommets adjacents");
		}

		for (Route r : this.plateau.routes) {
			if ((s1 == r.depart) && (s2 == r.arrivee)) {
				System.out.println("La route existe déjà");
				flag = false;
			}
			if ((s2 == r.depart) && (s1 == r.arrivee)) {
				System.out.println("La route existe déjà");
				flag = false;
			}
		}

		if (flag) {

			Route r = joueur.placerRoute(s1, s2, this.plateau);
			if (plateau.graphique) {
				joueur.partie.view.vp.drawRoute(joueur, r);
			}
			this.plateau.afficherPlateau();

		}

		else {

			System.out.println("les conditions ne sont pas réunies");

			if (joueur.isIa()) {
				construireRoute(joueur, joueur.matrix4.getCoord(), joueur.matrix4.getCoord());
			} else {
				construireRoute(joueur,
						getCoord(joueur.getNom() + ", pour placer une route , donnez les coordonnées du départ"),
						getCoord("et celle de l'arrivée"));
			}
		}

	}

	public Paysage demandeRessource() {
		System.out.println(
				" Sélectionnez une ressource :\n 1 : Bois\n 2 : Pierre \n 3 : Argile \n 4 : Mouton \n 5 : Blé");

		int scan = this.sc.nextInt();
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
				System.out.println("vous devez donner un entier entre 1 et 5");
				return demandeRessource();

		}
	}

}
