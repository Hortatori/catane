/**contient les deux sommets des routes et une construction
 * qui v�rifie que les routes soient toujours 
 * orient�e dans le m�me sens.
 */

package catane;

public class Route {

	Sommet depart;
	Sommet arrivee;
	boolean horizontale;

	Route(Sommet depart, Sommet arrivee) {
		// on impose un ordre des routes selon les coordonn�es du sommet :
		if ((depart.hauteur <= arrivee.hauteur) && (depart.largeur <= arrivee.largeur)) {
			this.depart = depart;
			this.arrivee = arrivee;
		} else {
			this.depart = arrivee;
			this.arrivee = depart;

		}
		int x = Math.abs(this.depart.largeur - this.arrivee.largeur);
		this.horizontale = (x == 1);

	}

	public String toString() {
		return "route allant de " + this.depart.AfficherCoord() + " a " + this.arrivee.AfficherCoord();
	}

}