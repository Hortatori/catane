package catane;

public class Sommet {
    int hauteur;
    int largeur;
    boolean colonie;
    boolean ville;
    Joueur joueur;

    public Sommet(int hauteur, int largeur) {
        super();
        this.hauteur = hauteur;
        this.largeur = largeur;

    }

    public String toString() {
        if (this.colonie == true) {
            // return "col " + this.joueur.getNom();
            return "MMU";
        } else if (this.ville == true) {
            // return "vil " + this.joueur.getNom();
            return "UUM";
        }
        return " * ";
    }

    public boolean routeLegale(Sommet s) {
        if ((Math.abs(this.hauteur - s.hauteur) != 1) && (Math.abs(this.largeur - s.largeur) != 0)) {
            return true;
        } else if ((Math.abs(this.hauteur - s.hauteur) != 0) && (Math.abs(this.largeur - s.largeur) != 1)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean plusPetit(Sommet s) {
        return (this.hauteur < s.hauteur || this.largeur < s.largeur);

    }

    public boolean routeHorizontale(Sommet s) {
        return (this.hauteur == s.hauteur);
    }

}