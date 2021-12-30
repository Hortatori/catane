package catane;

public class Inventaire {

    public int ble = 0;
    public int fer = 0;
    public int argile = 0;
    public int bois = 0;
    public int laine = 0;

    public int compteurColonies = 5;
    public int compteurVilles = 4;
    public int compteurRoutes = 15;

    public Inventaire() {

    }

    public void incrementInventaire(Case.Paysage paysage) {
        switch (paysage) {
            case FORET:
                this.bois++;
                break;
            case MONTAGNE:
                this.fer++;
                break;

            case COLLINE:
                this.argile++;
                break;

            case PRE:
                this.laine++;
                break;

            case CHAMP:
                this.ble++;
                break;

            case DESERT:
                break;
        }

    }

}