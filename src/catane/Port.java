package catane;


public class Port {
private Sommet s1 ;
private Sommet s2 ;
private int taux = 3 ;
private Cardinal orientation ;

// un port non spécial correspond au type de base, donc pas besoin d'ajouter une nouvelle classe port général.

public Port() {
	super();
	
	
}
public String afficherPort() {return "~|Port Général|~" ; }

public String afficherTaux() { return this.taux + " : 1"  ;}

public Sommet getS1() {
	return s1;
}
public Sommet getS2() {
	return s2;
}
public String toString() { 
	return  this.getClass().getName() + "  : taux d'échange de " + this.taux +" contre 1" ; }

protected void  setTaux (int i) {this.taux = i;} 
public void setSommets (Sommet s1, Sommet s2) { 
	this.s1 = s1 ;
	this.s2 = s2 ;
}

public void setOrientation(Cardinal o) {this.orientation= o ;}

public Cardinal getOrientation() {return this.orientation ;}

}
