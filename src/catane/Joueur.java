package catane;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Joueur {
    private String nom;
    private boolean ia;
    private Inventaire inventaire = new Inventaire();
    public int de;

    private ArrayList<Sommet> colonies;
    private ArrayList<Route> routes;
    private ArrayList<Carte> cartes;
    private int pts_victoire = 0;

    Joueur(String nom) {
        super();
        this.nom = nom;
        this.colonies = new ArrayList<Sommet>();
        this.routes = new ArrayList<Route>();
        this.cartes = new ArrayList<Carte>();

    }

    public ArrayList<Route> getListRoute() {
        return this.routes;
    }

    public ArrayList<Sommet> getListColonies() {
        return this.colonies;
    }

    public String getNom() {
        return this.nom;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public String toString() {
        return "JOUEUR : " + this.nom + "\n" + "RESSOURCES : " + "Argile : " + this.inventaire.argile + " Ble : " +
                this.inventaire.ble + " Bois : " + this.inventaire.bois + " Fer : " + this.inventaire.fer + " Laine : "
                + this.inventaire.laine;
    }

    private void setIA() {
        this.ia = true;
    }

    public int getPts() {
        return this.pts_victoire;
    }

    public void Tour() {
        Random rd = new Random();
        this.de = rd.nextInt(11) + 2;
        System.out.println("Les dés ont donné " + de + " ! ");
        if (de == 7) {

        }

    }

    public void placerColonieInit(Sommet s) {

        if (this.inventaire.compteurColonies == 0) {
            System.out.println("vous n'avez plus de colonies � poser!");
            return;
        }
        s.colonie = true;
        s.joueur = this;
        this.colonies.add(s);
        this.inventaire.compteurColonies--;
        this.pts_victoire++;
        System.out.println("colonie créée!");
    }

    public void placerVilleInit(Sommet s) {
        if (this.inventaire.compteurVilles == 0) {
            System.out.println("vous n'avez plus de villes � poser!");
            return;
        }
        if ((this.colonies.contains(s)) && (!s.ville)) {
            s.ville = true;
            this.inventaire.compteurVilles--;
            this.pts_victoire++;
            System.out.println("ville créée!");
        } else {
            System.out.println("Il vous faut déjà avoir une colonie à cet endroit pour la faire �voluer en ville");
        }

    }

    public void placerRoute(Sommet s1, Sommet s2, Plateau plateau) {
        boolean flag = false;

        // verifie qu'il existe une colonie sur un des 2 sommets
        if (this.colonies.contains(s1) || this.colonies.contains(s2)) {
            flag = true;

        }

        // verifie s'il existe des routes qui ont un des points de départ
        Sommet[] boucle = { s1, s2 };
        for (Sommet s : boucle) {
            for (Route route : this.routes) {
                if (route.depart.equals(s) || route.arrivee.equals(s)) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {

            // norme les routes pour que départ soit tjrs plus à gauche ou plus haut.
            Sommet dep = s2;
            Sommet arr = s1;
            if (s1.plusPetit(s2)) {
                dep = s1;
                arr = s2;
            }
            Route routeTouteNeuve = new Route(dep, arr);
            this.routes.add(routeTouteNeuve);
            this.inventaire.compteurRoutes--;
            plateau.routes.add(routeTouteNeuve);

            // TODO sera remplacee par un boolean dans Route
            if (routeTouteNeuve.depart.hauteur == routeTouteNeuve.arrivee.hauteur) {
                int largeur = Math.max(routeTouteNeuve.depart.largeur, routeTouteNeuve.arrivee.largeur);

                plateau.setRouteHorizontale(routeTouteNeuve.depart.hauteur, largeur);
            }
            if (routeTouteNeuve.depart.largeur == routeTouteNeuve.arrivee.largeur) {
                int hauteur = Math.max(routeTouteNeuve.depart.hauteur, routeTouteNeuve.arrivee.hauteur);

                plateau.setRouteVerticale(hauteur, routeTouteNeuve.arrivee.largeur);
            }
            // fin du remplacement par boolean Route
            System.out.println("route créée!");
        } else {
            System.out.println(
                    "Une route doit avoir une de vos colonies ou une de vos routes sur l'une de ses extrémité");
        }
    }

    public boolean routeDejaConstruite(Sommet s1, Sommet s2) {
        Sommet depart = s2;
        Sommet arrivee = s1;
        if (s1.plusPetit(s2)) {
            depart = s1;
            arrivee = s2;
        }

        for (Route route : this.routes) {
            if (route.depart == depart && route.arrivee == arrivee) {
                return true;
            }
        }
        return false;

    }

}