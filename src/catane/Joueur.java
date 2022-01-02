package catane;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color ;


public class Joueur {
private String nom ;
private boolean ia ;
// private Ressources ressources ;
private Color couleur ;
public static int totalj  = 0;
private int pts_victoire = 0 ;
private Partie partie;
private Inventaire inventaire = new Inventaire();

ArrayList<Sommet> colonies  = new ArrayList<Sommet> () ;
ArrayList<Port> ports = new ArrayList <Port>() ;

ArrayList<Route> routes  = new ArrayList<Route>() ;
ArrayList<Carte> cartes = new ArrayList<Carte>() ;

public void setCouleur (Color c ) {
	this.couleur = c ;}


// public Ressources getR() {
// 	return ressources;
// }

// public void setR(Ressources r) {
// 	this.ressources = r;
// }

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

public Inventaire getInventaire() {
    return this.inventaire;
}



public void afficherRoutes() { 
	for (Route r : this.routes ) {System.out.println( r.toString()) ;} }












 Joueur(String nom, Partie p) {
	super();
	this.partie = p ;
	this.nom = nom;
	// this.ressources = new Ressources();
	totalj ++; 
	
}
 
void setIA() { this.ia = true ;} 

public int getPts() {return this.pts_victoire ; }











public String toString() {
	String st = "JOUEUR : " +this.nom + "\n"+ "RESSOURCES : " + "Argile : " + this.inventaire.getArgile() + " Bl� : " +
this.inventaire.getBle() + " Bois : "+ this.inventaire.getBois() + " Fer : " +this.inventaire.getFer() +" Pierre : "+this.inventaire.getPierre() ;
	
	return st ;
	
}












public void placerColonieInit(Sommet s) {

    if (this.inventaire.getCompteurColonies() == 0) {
        System.out.println("vous n'avez plus de colonies � poser!");
        return;
    }
    if (s.colonie == true) {
    	
    	System.out.println("Il y a d�j� une colonie ici") ;
    	return ;
    }
    s.colonie = true;
    this.colonies.add(s);
    
  // on stocke l'info si une colonie est associ�e � un port pour faciliter l impl�mentation du commerce.
    for (Port p : this.partie.plateau.ports) {
    	if ( (p.getS1() == s) || (p.getS2() == s) ){
    		this.ports.add(p) ;
    	}
    }
    
    
    this.inventaire.placeColonie() ;
    this.pts_victoire++;
    System.out.println("colonie cr��e !");
}

public void placerVilleInit(Sommet s) {
    if (this.inventaire.getCompteurVilles() == 0) {
        System.out.println("Vous n'avez plus de ville � poser !");
        return;
    }
    if ((this.colonies.contains(s)) && (!s.ville)) {
        s.ville = true;
        
        
        
        this.inventaire.placeVille();
        this.pts_victoire++;
        System.out.println("ville cr��e !");
        
    } else {
        System.out.println("Il vous faut d�j� avoir une colonie � cet endroit pour la faire �voluer en ville !");
        return;
    }

}

public void placerRoute(Sommet s1, Sommet s2, Plateau plateau) {
    
    this.afficherColonies();
    this.afficherRoutes();

        Route routeTouteNeuve = new Route(s1, s2);

        this.routes.add(routeTouteNeuve);
        plateau.routes.add(routeTouteNeuve);
        this.inventaire.placeRoute();
       
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
        System.out.println("route cr��e!");
    
    
    

}




public void PossedeCase (Case elue) {
	
	for (Sommet c : this.colonies) {
		
		
		
		
		for (Sommet som : elue.Sommets) {
		if (som == c) {
			System.out.println (" Le joueur "+ this.nom + "touche des  ressources");
			int coef = 1 ;
			if (c.ville == true) { coef = 2 ; }
			switch (elue.type) {
			
		
			case MONTAGNE :
				this.inventaire.addPierre(coef);
				
				
			case PRE :
				this.inventaire.addMouton(coef);
			
				
			case FORET :
				this.inventaire.addBois(coef);
				
				
			case COLLINE :
				this.inventaire.addArgile(coef);
				
				
			case CHAMP :
				this.inventaire.addBle(coef);
				
				
			default :
				return ;
			
				
			}
		}
		}
//		else { System.out.println ( " voici les sommets" );
//		elue.afficheSommets();
//		this.afficherColonies() ;
//		}
		
		
	}
}



public void afficherColonies() {
	
	System.out.println( this.nom + " poss�de les colonies suivantes : " );

	for (Sommet s : this.colonies) {
		
		System.out.println ( "["+  s.largeur +" ; "+ s.hauteur);
}
}

public void afficherPort () {
	for (Port p : this.ports) { 
		System.out.println(p.toString()) ;
	}
}






}