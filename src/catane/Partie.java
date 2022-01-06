
package catane;

import java.io.InputStream;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JLabel;

import catane.VueJoueur.CoordPanel;

public class Partie {

	Plateau plateau = new Plateau();
	InterfaceJoueur ij = new InterfaceJoueur(new Scanner(System.in), plateau, this);
	private int n_joueur = -1;
	ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	Pioche pioche = new Pioche();
	Vue view;
	Voleur vo = new Voleur(this);

	public Partie() {

		System.out.println("Bienvenue pour une nouvelle partie des Colons de Catane !");
		System.out.println("Voulez vous jouer avec notre merveilleuse interface graphique ? (oui / non) !");
		String reponse = ij.sc.next();
		if (!ij.answerYesNo(reponse)) {
			AccueilTexte();
			Jouer();
		} else {
			this.plateau.graphique = true;

			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {

					Vue vue = new Vue(Partie.this);
					Partie.this.view = vue;
					AcceuilGraphique();

				}
			});

		}

	}

	public void Jouer() {
		initialiser();
		int j = 0;
		if (this.plateau.graphique == false) {
			while (Victoire() == false) {
				System.out.println(this.joueurs);
				System.out.println(j);
				if (j == Joueur.totalj) {
					j = 0;
				}
				;
				Tour(this.joueurs.get(j));
				plateau.afficherPlateau();
				j++;
				if (j == this.n_joueur) {
					j = 0;
				}
			}
		} else { // le programme graphique se charge lui m�me de lancer le tour suivant quand le
					// joueur clique sur fin de tour
			int de = LanceDe();
			this.view.updateJoueur(joueurs.get(0), de);
		}
	}

	protected void AcceuilGraphique() {
		VueAccueil vj = new VueAccueil(this);
		this.view.leftpanel = vj;
		this.view.getContentPane().add(vj);
	}

	public void AccueilTexte() {
		// this.sc = new Scanner(System.in);

		System.out.println("Veuillez indiquer si vous preferez jouer à trois ou quatre joueur");
		String scan = ij.sc.next();

		while (((scan.equals("3")) || (scan.equals("4"))) == false) {
			System.out.println("r�pondez � la question pos�e. ");
			scan = ij.sc.next();
		}
		// int n_joueur;
		if (scan.equals("3")) {
			this.n_joueur = 3;
			this.setN_joueur(3);
		} else {
			this.n_joueur = 4;
			this.setN_joueur(4);
		}

		System.out.println("Vous serez donc " + this.n_joueur + " joueurs a jouer !");

		String bienvenue = "Bienvenue dans le jeu, ";
		for (int i = 1; i < (this.n_joueur + 1); i++) {
			System.out.println("Entrez le nom du joueur " + (i));
			scan = ij.sc.next();
			for (Joueur former : this.joueurs) {
				if (former.getNom().equals(scan)) {
					System.out.println("nom d�j� pris");
					scan = ij.sc.next();
				}
			}
			Joueur j = new Joueur(scan, this);
			bienvenue += scan + ", ";
			this.joueurs.add(j);

			System.out.println(j.getNom() + " sera-t-il une IA?   (oui/non)");
			String rep = ij.sc.next();
			while ((!rep.equals("oui") && !rep.equals("non"))) {
				System.out.println("R�pondez oui ou non !");
				rep = ij.sc.next();
			}
			if (rep.equals("oui")) {
				j.setIA();
			}

		}

		System.out.println(bienvenue + " !");

		plateau.afficherPlateau();

	}

	public void initialiser() {
		if (this.plateau.graphique) {
			System.out.println("graph");

		}

		else {
			System.out.println(
					"Chaque joueur peut placer une route et une colonie en debut de partie! (elles genereront des ressources nulles jusqu'au début de la partie");
			for (Joueur joueur : joueurs) {

				int[] tab;
				if (joueur.isIa()) {
					tab = joueur.matrix4.getCoord();
				}

				else {
					tab = ij.getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonnees");
				}
				while (joueur.colonies.size() == 0) {
					ij.construireColonie(joueur, tab);
					if (joueur.isIa()) {
						tab = joueur.matrix4.getCoord();
					}
				}

				// on attribue une ressource initiale
				Sommet etoile = joueur.colonies.get(0);
				AttribuerRessourceInitiale(joueur, etoile);
				if (joueur.isIa()) {
					ij.construireRoute(joueur, joueur.matrix4.getCoord(), joueur.matrix4.getCoord());
				} else {
					//
					ij.construireRoute(joueur,
							ij.getCoord(joueur.getNom() + ", pour placer une route , donnez les coordonnees du depart"),
							ij.getCoord("et celle de l'arriv�ee"));
				}
				System.out.println(etoile.AfficherCoord());
			}

		}

		System.out.println("Debut de la partie !");
		if (this.plateau.graphique) {
			this.view.Communicate("D�but de la partie !");
		}

	}

	public void AttribuerRessourceInitiale(Joueur joueur, Sommet etoile) {
		for (Case[] tc : this.plateau.plateauC) {
			for (Case c : tc) {
				if ((c.NE == etoile) || (c.SE == etoile) || (c.NO == etoile) || (c.SO == etoile)) {
					String message = "Grace a sa colonie situee en " + etoile.AfficherCoord() + " , " + joueur.getNom()
							+ " touche une ressource";
					System.out.println(message);
					// joueur.getR().incrementRessource(c.type, 1);
					joueur.getR().incrementRessource(c.type, 7);
					// WARNING !!!! corriger debug

				}
			}

		}
	}

	public boolean Victoire() {
		for (Joueur j : this.joueurs) {
			if (j.getPts() > 9) {
				return true;

			}
		}
		return false;
	}

	public void printTables() {
		for (String[] ts : this.plateau.routesHorizontales) {
			String r = "";
			for (String s : ts) {
				r += s;
			}
			System.out.println(r);
		}
		for (String[] ts : this.plateau.routesVerticales) {
			String r = "";
			for (String s : ts) {
				r += s;
			}
			System.out.println(r);
		}
	}

	public int LanceDe() {
		Random rd = new Random();
		int de1 = rd.nextInt(6) + 1;
		int de2 = rd.nextInt(6) + 1;
		int de = de1 + de2;
		System.out.println("Les des ont donne " + de + " ! ");
		return de;

	}

	public void Tour(Joueur leader) {
		// int de = LanceDe();

		// WARNING!!!! debug
		System.out.println("give dés");
		int de = ij.sc.nextInt();
		// WARNNG!! debug

		leader.cartetour = false;
		if (de == 7) {
			vo.VoleurArrive(joueurs, leader);
		}

		LinkedList<Case> elues = plateau.getCase(de);
		for (Case c : elues) {
			for (Joueur j : this.joueurs)
				if (c.getStatutVoleur()) {
					Communicate("La case " + c.toString() + " est occup�e par le voleur et n'a rien produit");
				} else {
					c.checkCornerIncrement(j);
				}
		}
		if (leader.isIa()) {
			leader.matrix4.takeDecision();
		} else {
			if (this.plateau.graphique == false) {
				System.out.println("C'est a " + leader.getNom() + " de jouer");
				System.out.println(leader.getR());

				leader.afficherColonies();
				leader.afficherPort();
				this.ij.actions(leader);
			} else {
				this.view.updateJoueur(leader, de);

			}
		}
	}

	public int getN_joueur() {
		return n_joueur;
	}

	public void setN_joueur(int n_joueur) {
		this.n_joueur = n_joueur;
	}

	public void Communicate(String s) {
		if (this.plateau.graphique) {
			this.view.Communicate(s);
		} else {
			System.out.println(s);
		}
	}

	public void Communicate(String s, boolean efface) {
		if (efface) {
			this.view.ResetCommunicator();
		}
		Communicate(s);
	}

}
