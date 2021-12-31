package catane;

import java.util.LinkedList;

public class Case {
    Sommet NO;
    Sommet NE;
    Sommet SO;
    Sommet SE;
    Paysage type;
    int numero;
    LinkedList<Sommet> Sommets  = new LinkedList<Sommet>() ;
 
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
        this.Sommets.add(nO);
        this.Sommets.add(nE);
        this.Sommets.add(sO);
        this.Sommets.add(nE);
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



