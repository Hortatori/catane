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
			System.out.println("r�pondez � la question pos�e. ");
			scan = this.sc.next();
		}
		if (scan.equals("oui")) {
			return true;
		}
		return false;
	}

	// on v�rifie que la r�ponse rentre dans les coordon�es du tableau (et nous
	// �vite un INdexoutof bounds...
	public int answerInPlateau(int scan, int dimension) {
		while (scan > 4 || scan < 0) {
			System.out.println("cette coordonnee n'est pas valide");
			scan = this.sc.nextInt();
		}
		return scan;
	}

	public void actions(Joueur joueur) {

		System.out.println(joueur.getNom()
				+ ", souhaitez-vous :\n 1 : Construire une colonie? \n 2 : Construire une ville?  \n 3 : Construire une route? \n 4 : Faire du commerce? \n 5 Acheter une carte \n 6 : Jouer une carte ? \n 7 : Finir votre tour");

		int scan = this.sc.nextInt();
		switch (scan) {
			case 1:

				construireColonie(joueur,
						getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonn�es"));

				break;
			case 2:

				construireVille(joueur, getCoord(joueur.getNom() + ", pour placer une ville , donnez ses coordonn�es"));
				break;

			case 3:
				construireRoute(joueur,
						getCoord(joueur.getNom() + ", pour placer une route , donnez les coordonn�es du d�part"),
						getCoord("et celle de l'arriv�e"));
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
				Carte c = this.partie.pioche.pioche.getFirst();
				joueur.cartes.add(c);
				c.setPossesseur(joueur);
				this.partie.pioche.pioche.removeFirst();
				joueur.afficherCartes();
				break;

			case 6:
				if (joueur.cartetour == true) {
					break;
				}
				joueur.afficherCartes();
				System.out.println("Donnez nous le num�ro de la carte que vous voulez jouer (0 = annuler) ");
				int rep = sc.nextInt();
				if (rep == 0) {
					break;
				}
				if (rep > joueur.cartes.size() + 1) {
					System.out.println("votre indice d�passe le nombre de carte");
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
		// pb de l'indice donn� par rapport au tableau des sommets sont le premier
		// �l�ment est n�gatif. on rajoute 1 � cet endroit (et � d'autres dans le code
		// pour bien commencer le compte avec le sommet en haut � gauche du plateau.

		boolean flag = true;

		if (plateau.plateauS[Y][X].colonie) {
			System.out.println("Conditions non respect�es ");
			System.out.println("il y a d�j� une colonie ici ! ");
			flag = false;

		}
		if (X > 0) {
			if (plateau.plateauS[Y - 1][X].colonie) {
				System.out.println("Conditions non respect�es ");
				System.out.println("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
				flag = false;
			}
		}
		if (Y > 0) {
			if ((plateau.plateauS[Y][X - 1].colonie)) {
				System.out.println("Conditions non respect�es ");
				System.out.println("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
				flag = false;
			}
		}

		if ((plateau.plateauS[Y + 1][X].colonie) || (plateau.plateauS[Y][X + 1].colonie)) {
			System.out.println("Conditions non respect��es ");
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
							getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonn�es"));
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
			System.out.println("Vous devez poss�der une colonie pour l am�liorer en ville");
			if (joueur.isIa()) {
				construireVille(joueur, joueur.matrix4.getCoord());
			} else {

				construireVille(joueur,
						getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonn�ees"));
			}
		}
	}

	public void construireRoute(Joueur joueur, int[] tabd, int[] tabf) {
		System.out.println(
				joueur.getNom() + ", pour placer une Route, donnez les coordonn�es de d�but et de fin de la route");

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
			System.out.println("une route doit �tre de longueur 1 et relier deux sommets adjacents");
		}

		for (Route r : this.plateau.routes) {
			if ((s1 == r.depart) && (s2 == r.arrivee)) {
				System.out.println("La route existe d�j�");
				flag = false;
			}
			if ((s2 == r.depart) && (s1 == r.arrivee)) {
				System.out.println("La route existe d�j�");
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

			System.out.println("les conditions ne sont pas r�unies");

			if (joueur.isIa()) {
				construireRoute(joueur, joueur.matrix4.getCoord(), joueur.matrix4.getCoord());
			} else {
				construireRoute(joueur,
						getCoord(joueur.getNom() + ", pour placer une route , donnez les coordonn�es du d�part"),
						getCoord("et celle de l'arriv�e"));
			}
		}

	}

	public Paysage demandeRessource() {
		System.out.println(
				" S�lectionnez une ressource :\n 1 : Bois\n 2 : Pierre \n 3 : Argile \n 4 : Mouton \n 5 :bl�");

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
