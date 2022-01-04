package catane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.awt.Color ;


public class Joueur {
private String nom ;
private boolean ia ;
private Ressources ressources ;
private Color couleur ;
public static int totalj  = 0;

ArrayList<Sommet> colonies  = new ArrayList<Sommet> () ;
ArrayList<Port> ports = new ArrayList <Port>() ;

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



public void afficherRoutes() { 
	for (Route r : this.routes ) {System.out.println( r.toString()) ;} }









private int pts_victoire = 0 ;
public Partie partie;
private int longueurRoute;


 Joueur(String nom, Partie p) {
	super();
	this.partie = p ;
	this.nom = nom;
	this.ressources = new Ressources();
	totalj ++; 
	
}
 
void setIA() { this.ia = true ;} 

public int getPts() {return this.pts_victoire ; }











public String toString() {
	String st = "JOUEUR : " +this.nom + "\n"+ "RESSOURCES : " + "Argile : " + this.ressources.getArgile() + " Blé : " +
this.ressources.getBle() + " Bois : "+ this.ressources.getBois() + " Mouton : " +this.ressources.getMouton() +" Pierre : "+this.ressources.getPierre() ;
	
	return st ;
	
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
    
  // on stocke l'info si une colonie est associée à un port pour faciliter l implémentation du commerce.
    for (Port p : this.partie.plateau.ports) {
    	if ( (p.getS1() == s) || (p.getS2() == s) ){
    		this.ports.add(p) ;
    	}
    }
    
    
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
    
    this.afficherColonies();
    this.afficherRoutes();

        Route routeTouteNeuve = new Route(s1, s2);

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
    
    
    

}




//public void PossedeCase (Case elue) {
//	
//	for (Sommet c : this.colonies) {
//		
//		
//		
//		
//		for (Sommet som : elue.Sommets) {
//		if (som == c) {
//			System.out.println (" Le joueur "+ this.nom + "touche des  ressources");
//			int coef = 1 ;
//			if (c.ville == true) { coef = 2 ; }
//			switch (elue.type) {
//			
//		
//			case MONTAGNE :
//				this.ressources.addPierre(coef);
//				
//				
//			case PRE :
//				this.ressources.addMouton(coef);
//			
//				
//			case FORET :
//				this.ressources.addBois(coef);
//				
//				
//			case COLLINE :
//				this.ressources.addArgile(coef);
//				
//				
//			case CHAMP :
//				this.ressources.addBle(coef);
//				
//				
//			default :
//				return ;
//			
//				
//			}
//		}
//		}
////		else { System.out.println ( " voici les sommets" );
////		elue.afficheSommets();
////		this.afficherColonies() ;
////		}
//		
//		
//	}
//}
//
//

public void afficherColonies() {
	
	System.out.println( this.nom + " possède les colonies suivantes : " );

	for (Sommet s : this.colonies) {
		
		System.out.println ( "["+  s.largeur +" ; "+ s.hauteur);
}
}

public void afficherPort () {
	for (Port p : this.ports) { 
		System.out.println(p.toString()) ;
	}
}



// méthode ne fonctionne pas très bien pour l'instant , il faudrait la faire récursive avec comme entrée un sommet et une liste de routes
public void longueurRoute() {
	int nbRoute = this.routes.size();
	
	ArrayList<Integer> result = new ArrayList<Integer> () ;
	
	for (Route depart : this.routes) {
		// on parcourt toutes les routes dans tous les sens et ce en partant des deux bouts. 
		
		int lenR = 1 ;
		ArrayList<Route> routesencours = new ArrayList<Route>() ;
		routesencours.addAll(this.routes) ;
		routesencours.remove(depart) ;
		
		String s = depart.toString() ;
		boolean encore = true ;
		while (encore) {
			encore = false ;
			for (int i = 0 ; i < routesencours.size() ; i++) {
				Route route = routesencours.get(i) ;
				if ( ( route.depart == depart.arrivee) ||( route.depart == depart.depart) || ( route.arrivee == depart.arrivee) || ( route.arrivee == depart.arrivee)) {
				lenR ++ ;
				routesencours.remove(route) ;
				depart = route ;
				s+= depart.toString();
				encore = true ;
				break ;
				}
			}
		
		}
		result.add(lenR) ;
		System.out.println(s) ;
		System.out.println (Arrays.toString(result.toArray())) ;
		
		
	}
	int max = Collections.max(result) ;
	System.out.println( " La route la plus longue de "+ this.nom + " est longue de "+max ) ;
	this.longueurRoute = max;
}


public void afficherCartes() {
	if (this.partie.plateau.graphique == false ) {
	System.out.println(" Voici les cartes que vous pouvez jouer :" );
	int i = 0 ;
	for (Carte c : this.cartes  ) {
		if (c.getJouee() == false ) {
			i ++ ;
			System.out.println ( i + " : " + c.toString() ) ;
		}
		
		
	}
	}
}





}