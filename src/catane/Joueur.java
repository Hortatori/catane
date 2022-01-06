/**  Joueur 
 * 1/ la création des routes, colonies, villes du joueur
 * 2/ le statut des ressources du joueur 
 * 3/ l'affichage des colonies, villes et routes qui lui appartiennent 
 * 4/ le passage aux joueurs suivant, l'incrémentation des points de victoire
 * */

package catane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.Color;

public class Joueur {
	private String nom;
	private boolean ia;
	private Ressources ressources;
	private Color couleur;
	public static int totalj = 0;
	public VueJoueur vj;
	boolean cartetour;
	ArrayList<Sommet> colonies = new ArrayList<Sommet>();
	ArrayList<Port> ports = new ArrayList<Port>();

	ArrayList<Route> routes = new ArrayList<Route>();
	ArrayList<Carte> cartes = new ArrayList<Carte>();
	IA matrix4;
	private int pts_victoire = 0;
	public Partie partie;
	private int longueurRoute;
	public boolean maxcavalier;
	public boolean routelongue ;
	
	public void setCouleur() {
		Color coul = new RandomColor().c;
		this.couleur = coul;
	}

	public Ressources getR() {
		return ressources;
	}

	public void setR(Ressources r) {
		this.ressources = r;
	}

	public String getNom() {
		return nom;
	}

	public boolean isIa() {
		return ia;
	}

	public ArrayList<Sommet> getColonies() {
		return colonies;
	}

	public ArrayList<Route> getRoutes() {
		return routes;
	}

	public Color getCouleur() {
		return this.couleur;
	}

	public ArrayList<Carte> getCartes() {
		return cartes;
	}

	public int getPts_victoire() {
		return pts_victoire;
	}

	public void afficherRoutes() {
		for (Route r : this.routes) {
			System.out.println(r.toString());
		}
	}

	Joueur() {
		super();
	}

	Joueur(String nom, Partie p) {
		super();
		this.setCouleur();
		this.partie = p;
		this.nom = nom;
		this.ressources = new Ressources();
		totalj++;

	}

	void setIA() {
		this.ia = true;
		this.matrix4 = new IA(this);
	}

	public int getPts() {
		return this.pts_victoire;
	}

	public String toString() {
		String st = "JOUEUR : " + this.nom + " RESSOURCES : " + " Bois : " + this.ressources.getBois() + " ||  Pierre :"
				+ this.ressources.getPierre() + " || Argile : " + this.ressources.getArgile() + " || Mouton :"
				+ this.ressources.getMouton() + " ||  Blé : " + this.ressources.getBle() + "\n";

		return st;

	}

	public void placerColonieInit(Sommet s) {

		if (this.ressources.getCompteurColonies() == 0) {
			System.out.println("vous n'avez plus de colonies à poser!");
			return;
		}
		if (s.colonie == true) { /// redondant ?

			System.out.println("Il y a déjà une colonie ici");
			return;
		}
		s.colonie = true;
		this.colonies.add(s);

		// on stocke l'info si une colonie est associée à un port pour faciliter l
		// implémentation du commerce.
		for (Port p : this.partie.plateau.ports) {
			if ((p.getS1() == s) || (p.getS2() == s)) {
				this.ports.add(p);
			}
		}

		this.ressources.placeColonie();
		this.pts_victoire++;
		System.out.println("colonie créée !");
		
	}

	public void placerVilleInit(Sommet s) {
		if (this.ressources.getCompteurVilles() == 0) {
			System.out.println("Vous n'avez plus de ville à poser !");
			return;
		}
		if ((this.colonies.contains(s)) && (!s.ville)) {
			s.ville = true;

			this.ressources.placeVille();
			this.pts_victoire++;
			System.out.println("ville créée !");

		} else {
			System.out.println("Il vous faut déjà  avoir une colonie à cet endroit pour la faire évoluer en ville !");
			return;
		}

	}

	public Route placerRoute(Sommet s1, Sommet s2, Plateau plateau) {

		this.afficherColonies();
		this.afficherRoutes();

		Route routeTouteNeuve = new Route(s1, s2);

		this.routes.add(routeTouteNeuve);
		plateau.routes.add(routeTouteNeuve);
		this.ressources.placeRoute();

		plateau.routes.add(routeTouteNeuve);
		Sommet d = routeTouteNeuve.depart;
		System.out.println(d.hauteur + "   " + d.largeur);
		if (routeTouteNeuve.horizontale) {

			plateau.setRouteHorizontale(d.hauteur, d.largeur);
		} else {
			System.out.println("vertical");
			plateau.setRouteVerticale(d.hauteur, d.largeur);
		}

		System.out.println("route créée!");
		System.out.println ("ROUTE LA PLUS LONGUE") ;
		System.out.println(this.partie.longueurRoute(this.routes)) ;
		this.longueurRoute = this.partie.longueurRoute(routes);
		for (Joueur j : this.partie.joueurs ) {
			if (longueurRoute > j.longueurRoute) {
				this.routelongue = true ;
			}
			
		}
		return routeTouteNeuve;

	}

	public void afficherColonies() {

		System.out.println(this.nom + " possède les colonies suivantes : ");

		for (Sommet s : this.colonies) {

			System.out.println("[" + s.largeur + " ; " + s.hauteur + "]");
		}
	}

	public void afficherPort() {
		for (Port p : this.ports) {
			System.out.println(p.toString());
		}
	}

	public void afficherCartes() {
		if (this.partie.plateau.graphique == false) {
			this.partie.Communicate(" Voici les cartes dont  vous disposez :");
			int i = 0;
			for (Carte c : this.cartes) {

				i++;
				this.partie.Communicate(i + " : " + c.toString());
			}
		}
	}

	public void incrementeVictoire() {
		this.pts_victoire++;
	}

	public Joueur nextJoueur() {
		// System.out . println(this.getNom() + "FINIT SON TOUR") ;
		int rang = this.partie.joueurs.indexOf(this);
		if (rang == this.partie.getN_joueur() - 1) {
			return this.partie.joueurs.get(0);
		} else {
			return this.partie.joueurs.get(rang + 1);
		}
	}

}