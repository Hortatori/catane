package catane;

import java.util.LinkedList;

public class Case {
Sommet NO ;
Sommet NE ;
Sommet SO ;
Sommet SE ;
Paysage type ;
int numero ;
LinkedList<Sommet> Sommets  = new LinkedList<Sommet>() ;




public void setType(Paysage t) {this.type = t;}

public void setNumber(int i) {this.numero = i;}

public int getNumero() { return this.numero ;}




public Case(Sommet nO, Sommet nE, Sommet sO, Sommet sE) {
	super();
	NO = nO;
	NE = nE;
	SO = sO;
	SE = sE;
	this.Sommets.add(nO);
	this.Sommets.add(nE);
	this.Sommets.add(sO);
	this.Sommets.add(nE);
}

public String toString() {
	return this.type.toString() + " " + this.numero ;
}

public void debug() {
	System.out.println(this.toString());
	for (Sommet s : this.Sommets ) {
		System.out.println( s.AfficherCoord() ) ;
	}
}

public void afficheSommets() {
	for (Sommet s : this.Sommets ) {
		System.out.println (s.largeur + "  "+ s.hauteur );
	}
}


public void checkCornerIncrement(Joueur joueur) {
    if (joueur.getColonies().contains(this.NE)) {

        joueur.getR().incrementRessource(this.type, 1);
    }
    if (joueur.getColonies().contains(this.NO)) {

        joueur.getR().incrementRessource(this.type, 1);
    }
    if (joueur.getColonies().contains(this.SO)) {

        joueur.getR().incrementRessource(this.type, 1);
    }
    if (joueur.getColonies().contains(this.SE)) {

        joueur.getR().incrementRessource(this.type, 1);
    }
}





}
