package catane;


public class Route {

    Sommet depart;
    Sommet arrivee;
    boolean horizontale;

    Route(Sommet depart, Sommet arrivee) {
        // this.depart = depart;
        // this.arrivee = arrivee;
        // this.horizontale = this.depart.routeHorizontale(this.arrivee);
        if ((depart.hauteur <= arrivee.hauteur) && (depart.largeur <= arrivee.largeur)) {
            System.out.println("bon ordre");
            this.depart = depart;
            this.arrivee = arrivee;}
            else { this.depart = arrivee ; this.arrivee = depart; 
            System.out.println("maivaos ordre");}
            int x = Math.abs (this.depart.largeur - this.arrivee.largeur );
            System.out.println(x);
            System.out.println("dep ! " + this.depart.largeur + this.depart.hauteur) ;
            System.out.println("arr ! " + this.arrivee.largeur + this.arrivee.hauteur) ;
            this.horizontale = (x == 1);

    }

    public boolean memeRoute(Route route) {
        return ((route.arrivee.equals(this.arrivee) && route.depart.equals(this.depart))
                || (route.arrivee.equals(this.depart) && route.depart.equals(this.arrivee)));

    }

    // ********* compare dans le cas où la route n'a pas encore été créée ********
    public boolean memeSommetRoute(Sommet s1, Sommet s2) {
        System.out.println("herememe");

        return ((s1.equals(this.arrivee) && s2.equals(this.depart))
                || (s2.equals(this.depart) && s1.equals(this.arrivee)));

    }

	public String toString() {
		return "route allant de " + this.depart.AfficherCoord() + " a " + this.arrivee.AfficherCoord() ;
	}

}