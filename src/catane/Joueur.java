package catane;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Color ;


public class Joueur {
    private String nom;
    private boolean ia;
    private Ressources ressources ;
    private Color couleur ;

    private Inventaire inventaire = new Inventaire();
    public int de;

    ArrayList<Sommet> colonies;
    ArrayList<Route> routes;
    ArrayList<Carte> cartes;
    private int pts_victoire = 0;

    Joueur(String nom) {
        super();
        this.nom = nom;
        this.ressources = new Ressources();
        this.colonies = new ArrayList<Sommet>();
        this.routes = new ArrayList<Route>();
        this.cartes = new ArrayList<Carte>();

    }

    public void setCouleur (Color c ) {
        this.couleur = c ;}
    
    
    public Ressources getR() {
        return ressources;
    }
    
    public void setR(Ressources r) {
        this.ressources = r;
    }

    public ArrayList<Route> getListRoute() {
        return this.routes;
    }

    public ArrayList<Sommet> getListColonies() {
        return this.colonies;
    }

    public ArrayList<Sommet> getColonies() {
        return colonies;
    }
    
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public String getNom() {
        return this.nom;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    
    public void afficherColonies() { 
        for (Sommet s : this.colonies ) { System.out.println (s.AfficherCoord() ) ;} }

    public void afficherRoutes() { 
        for (Route r : this.routes ) {System.out.println( r.toString()) ;} }

    public String toString() {
        return "JOUEUR : " + this.nom + "\n" + "RESSOURCES : " + "Argile : " + this.inventaire.argile + " Ble : " +
                this.inventaire.ble + " Bois : " + this.inventaire.bois + " Fer : " + this.inventaire.fer + " Laine : "
                + this.inventaire.laine;
    }

    public void setIA() {
        this.ia = true;
    }

    public boolean isIa() {
        return ia;
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
        this.ressources.placeColonie() ;
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
        if ((flag) && s1.routeLegale(s2)) {

            // norme les routes pour que départ soit tjrs plus à gauche ou plus haut.
            Sommet dep = s2;
            Sommet arr = s1;
            if (s1.plusPetit(s2)) {
                dep = s1;
                arr = s2;
            }
            Route routeTouteNeuve = new Route(dep, arr);
            if (plateau.routes.contains(routeTouteNeuve)    ) { System.out.println("La route existe d�j�") ;
        }else{
            this.routes.add(routeTouteNeuve);
            this.inventaire.compteurRoutes--;
            plateau.routes.add(routeTouteNeuve);

            Sommet d = routeTouteNeuve.depart ;
            System.out.println(d.hauteur + "   " + d.largeur) ;
            if (routeTouteNeuve.horizontale) {
         
                plateau.setRouteHorizontale(d.hauteur +2, d.largeur+2);
            }
            else {
                System.out.println("vertical");
                plateau.setRouteVerticale(d.hauteur +2, d.largeur+2);
            }

            System.out.println("route créée!");}
        } 
            System.out.println(
                    "Une route doit avoir une de vos colonies ou une de vos routes sur l'une de ses extrémité");
        
    }



    
    public void PossedeCase (Case elue) {
        System.out.println(this.getNom() + "   "+elue.type.name() ) ;
        for (Sommet c : this.colonies) {
            if (elue.Sommets.contains(c)) {
                int coef = 1 ;
                if (c.ville == true) { coef = 2 ; }
                switch (elue.type) {
                
            
                case MONTAGNE :
                    this.ressources.addPierre(coef);
                    
                    
                case PRE :
                    this.ressources.addMouton(coef);
                
                    
                case FORET :
                    this.ressources.addBois(coef);
                    
                    
                case COLLINE :
                    this.ressources.addArgile(coef);
                    
                    
                case CHAMP :
                    this.ressources.addBle(coef);
                    
                    
                default :
                    return ;
                
                    
                }
            }
            
        }
    }

}