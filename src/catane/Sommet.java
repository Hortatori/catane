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

// public String toString() {
// 	if (this.colonie&&!this.ville == true) {
// 		// return "col " + this.joueur.getNom();
// 		return "MMU";
// 	} else if (this.colonie&&this.ville == true) {
// 		// return "vil " + this.joueur.getNom();
// 		return "UUM";
// 	}
// 	return " * ";
// }

public String AfficherCoord () 
{ return  " x : " + this.largeur + "   y : " + this.hauteur ; }


public boolean routeLegale(Sommet s) {
	if ((    Math.abs(this.hauteur - s.hauteur) == 1  ) && (    Math.abs(this.largeur - s.largeur) == 0  )) {
		System.out.println("route l�gale");
		return true ; }
	if ((    Math.abs(this.hauteur - s.hauteur) == 0  ) && (    Math.abs(this.largeur - s.largeur) == 1  )) {
		
		System.out.println("route l�gale"); 
		return true ; }
	System.out.println("Toute route ne doit avoir qu'une longueur de 1");
	return false ;	
}

public boolean plusPetit(Sommet s) {
	return (this.hauteur < s.hauteur || this.largeur < s.largeur);

}

public boolean routeHorizontale(Sommet s) {
	return (this.hauteur == s.hauteur);
}

}

