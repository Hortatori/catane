package catane;

import java.util.LinkedList;

public class Case {
Sommet NO ;
Sommet NE ;
Sommet SO ;
Sommet SE ;
Paysage type ;
int numero ;
private boolean voleur;
LinkedList<Sommet> Sommets  = new LinkedList<Sommet>() ;
// pas utile conceptuellement mais permet un parcourt rapide de chaque sommet pour l'attribution des ressources
// faire une simple liste des sommets sans NO, SO... aurait faire perdre l'information géographique 




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
		System.out.println (" x : " + s.largeur + "  y :  "+ s.hauteur );
	}
}


public void checkCornerIncrement(Joueur joueur) {
//    if (joueur.getColonies().contains(this.NE)) {
//
//        joueur.getR().incrementRessource(this.type, 1);
//    }
//    if (joueur.getColonies().contains(this.NO)) {
//
//        joueur.getR().incrementRessource(this.type, 1);
//    }
//    if (joueur.getColonies().contains(this.SO)) {
//
//        joueur.getR().incrementRessource(this.type, 1);
//    }
//    if (joueur.getColonies().contains(this.SE)) {
//
//        joueur.getR().incrementRessource(this.type, 1);
//    }
// VERSION COMPACTE 
for (Sommet s : this.Sommets ) {
	if (joueur.getColonies().contains(s)) {
		
		        joueur.getR().incrementRessource(this.type, 1);
		    }
}
}

public boolean getStatutVoleur() {
	return voleur;
}

public void setStatutVoleur(boolean vol) {
	this.voleur = vol;
}



}
