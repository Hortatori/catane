package catane;

public class Route {
    Sommet depart;
    Sommet arrivee;
    boolean horizontal;

    Route(Sommet depart, Sommet arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.horizontal = this.depart.routeHorizontale(this.arrivee);

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
        return String.valueOf(depart.hauteur) + String.valueOf(depart.largeur) + String.valueOf(depart.largeur)
                + String.valueOf(arrivee.hauteur);
    }

}