package catane;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color ;


public class Joueur {
private String nom ;
private boolean ia ;
private Ressources ressources ;
private Color couleur ;

ArrayList<Sommet> colonies  = new ArrayList<Sommet> () ;
ArrayList<Route> routes  = new ArrayList<Route>() ;
ArrayList<Carte> cartes = new ArrayList<Carte>() ;

public void setCouleur (Color c ) {
	this.couleur = c ;}


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


public void afficherColonies() { 
	for (Sommet s : this.colonies ) { System.out.println (s.AfficherCoord() ) ;} }

public void afficherRoutes() { 
	for (Route r : this.routes ) {System.out.println( r.toString()) ;} }









private int pts_victoire = 0 ;


 Joueur(String nom) {
	super();
	this.nom = nom;
	this.ressources = new Ressources();
	
}
 
void setIA() { this.ia = true ;} 

public int getPts() {return this.pts_victoire ; }











public String toString() {
	String st = "JOUEUR : " +this.nom + "\n"+ "RESSOURCES : " + "Argile : " + this.ressources.getArgile() + " Blé : " +
this.ressources.getBle() + " Bois : "+ this.ressources.getBois() + " Fer : " +this.ressources.getFer() +" Pierre : "+this.ressources.getPierre() ;
	
	return st ;
	
}









public void LanceDe() {
    Random rd = new Random();
    int de = rd.nextInt(11) + 2;
    System.out.println("Les dÃ©s ont donnÃ© " + de + " ! ");
    if (de == 7) {

    }

}

public void placerColonieInit(Sommet s) {

    if (this.ressources.getCompteurColonies() == 0) {
        System.out.println("vous n'avez plus de colonies à poser!");
        return;
    }
    if (s.colonie == true) {
    	
    	System.out.println("Il y a déjà une colonie ici") ;
    	return ;
    }
    s.colonie = true;
    this.colonies.add(s);
    this.ressources.placeColonie() ;
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

public void placerRoute(Sommet s1, Sommet s2, Plateau plateau) {
    boolean flag = false;
    Sommet[] boucle = { s1, s2 };
    this.afficherColonies();
    this.afficherRoutes();
    if (this.colonies.contains(s1) || this.colonies.contains(s2)) {
        flag = true;
    }
    for (Sommet s : boucle) {
        for (Route route : this.routes) {
            if (route.depart.equals(s) || route.arrivee.equals(s)) {
                flag = true;
                break;
            }
        }
    }
    
    if ((flag) && s1.routeLegale(s2)) {
    	
        Route routeTouteNeuve = new Route(s1, s2);
        
        
        if (plateau.routes.contains(routeTouteNeuve)    ) { System.out.println("La route existe déjà") ;
        }
        else {
        this.routes.add(routeTouteNeuve);
        plateau.routes.add(routeTouteNeuve);
        this.ressources.placeRoute();
       
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
        // TODO plateau maj routes
        System.out.println("route créée!");
    }}
    
    System.out.println("Une route doit avoir une de vos colonies ou une de vos routes sur l'une de ses extrÃ©mitÃ©");
    // 1er ?t

}
















}