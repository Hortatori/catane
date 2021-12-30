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

}

