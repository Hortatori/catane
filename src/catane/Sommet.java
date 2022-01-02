package catane;


public class Sommet {
int hauteur ;
int largeur ;
boolean colonie ;
boolean ville ;

public Sommet(int hauteur, int largeur) {
	super();
	this.hauteur = hauteur;
	this.largeur = largeur;
	
}

public String toString() {
	if (this.colonie == true) {return "MMU";}
	return " * ";
}

public String AfficherCoord () 
{ return  " x : " + this.largeur + "   y : " + this.hauteur ; }


public boolean routeLegale(Sommet s) {
	if ((    Math.abs(this.hauteur - s.hauteur) == 1  ) && (    Math.abs(this.largeur - s.largeur) == 0  )) {
		System.out.println("route légale");
		return true ; }
	if ((    Math.abs(this.hauteur - s.hauteur) == 0  ) && (    Math.abs(this.largeur - s.largeur) == 1  )) {
		
		System.out.println("route légale"); 
		return true ; }
	System.out.println("Toute route ne doit avoir qu'une longueur de 1");
	return false ;	
}

}

