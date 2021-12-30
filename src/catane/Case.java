package catane;

public class Case {
    Sommet NO;
    Sommet NE;
    Sommet SO;
    Sommet SE;
    Paysage type;
    int numero;

    public enum Paysage {
        DESERT, FORET, MONTAGNE, COLLINE, PRE, CHAMP
    }

    public void setType(Paysage t) {
        this.type = t;
    }

    public void setNumber(int i) {
        this.numero = i;
    }

    public Case(Sommet nO, Sommet nE, Sommet sO, Sommet sE) {
        super();
        NO = nO;
        NE = nE;
        SO = sO;
        SE = sE;
    }

    public String toString() {
        return this.type.toString() + " " + this.numero;
    }

    public void checkCornerIncrement(Joueur joueur) {
        if (joueur.getListColonies().contains(this.NE)) {

            joueur.getInventaire().incrementInventaire(this.type);
        }
        if (joueur.getListColonies().contains(this.NO)) {

            joueur.getInventaire().incrementInventaire(this.type);
        }
        if (joueur.getListColonies().contains(this.SO)) {

            joueur.getInventaire().incrementInventaire(this.type);
        }
        if (joueur.getListColonies().contains(this.SE)) {

            joueur.getInventaire().incrementInventaire(this.type);
        }
    }
}