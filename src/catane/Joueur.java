package catane;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Joueur {
private String nom ;
private boolean ia ;
private int ble ;
private int fer ;
private int argile ;
private int bois ;
private int pierre ;
ArrayList<Sommet> colonies ;
ArrayList<Route> routes ;
ArrayList<Carte> cartes ;
private int pts_victoire = 0 ;


 Joueur(String nom) {
	super();
	this.nom = nom;
	
}
 
private void setIA() { this.ia = true ;} 

public int getPts() {return this.pts_victoire ; }



public void Tour() {
RandomGenerator rd = null ;
int de = rd.nextInt(2, 13) ;
System.out.println("Les d�s ont donn� "+ de + " ! ") ;
if (de == 7 ) {
	
}

}


private void placerColonieInit (Sommet s) {
	if (s.colonie) {System.out.println("il y a d�j� une colonie ici ! ");  return ;}
	s.colonie = true ; 
	this.colonies.add(s);
	this.pts_victoire ++ ;
	}


private void placerVilleInit (Sommet s) {
	if ( ( this.colonies.contains(s)) && (s.ville = false) ) {
		s.ville = true ;
	}
	else { System.out.println("Il vous faut d�j� avoir une colonie � cet endroit p�ur la faire �voluer en ville");
	this.pts_victoire ++ ;
	}

}

private void placerRoute (Sommet s1, Sommet s2) {
	// 1er �t
	
	
}



public String toString() {
	String st = "JOUEUR : " +this.nom + "\n"+ "RESSOURCES : " + "Argile : " + this.argile + " Bl� : " +
this.ble + " Bois : "+ this.bois + " Fer : " +this.fer +" Pierre : "+this.pierre ;
	
	return st ;
	
}























}