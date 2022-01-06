package catane;

public class Route {

	Sommet depart;
	Sommet arrivee;
	boolean horizontale;

	Route(Sommet depart, Sommet arrivee) {
		// on impose un ordre des routes selon les coordonnées du sommet :
		if ((depart.hauteur <= arrivee.hauteur) && (depart.largeur <= arrivee.largeur)) {
			System.out.println("bon ordre");
			this.depart = depart;
			this.arrivee = arrivee;
		} else {
			this.depart = arrivee;
			this.arrivee = depart;
			System.out.println("mauvais ordre");
		}
		int x = Math.abs(this.depart.largeur - this.arrivee.largeur);
		System.out.println(x);
		System.out.println("dep ! " + this.depart.largeur + this.depart.hauteur);
		System.out.println("arr ! " + this.arrivee.largeur + this.arrivee.hauteur);
		this.horizontale = (x == 1);

	}

	public String toString() {
		return "route allant de " + this.depart.AfficherCoord() + " a " + this.arrivee.AfficherCoord();
	}

}