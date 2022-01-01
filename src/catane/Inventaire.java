package catane;

public class Inventaire {

    private int ble = 0;
    private int fer = 0;
    private int argile = 0;
    private int bois = 0;
    private int laine = 0;

    private int compteurColonies = 5;
    private int compteurVilles = 4;
    private int compteurRoutes = 15;

    public Inventaire() {

    }

    public void incrementInventaire(Paysage paysage, int quantite) {
        switch (paysage) {
            case FORET:
                this.bois+=quantite;
                break;
            case MONTAGNE:
                this.fer+=quantite;
                break;

            case COLLINE:
                this.argile+=quantite;
                break;

            case PRE:
                this.laine+=quantite;
                break;

            case CHAMP:
                this.ble+=quantite;
                break;

            case DESERT:
                break;
        }

    }

    public int getCompteurColonies() {
		return compteurColonies;
	}
	public int getCompteurVilles() {
		return compteurVilles;
	}
	public int getCompteurRoutes() {
		return compteurRoutes;
	}
	public int getBle() {
		return ble;
	}
	public int getMouton() {
		return laine;
	}
	public int getArgile() {
		return argile;
	}
	public int getBois() {
		return bois;
	}
	public int getPierre() {
		return fer;
	}
	
	
	public void addBle(int i) {
		 ble+= i;
	}
	public void addMouton(int i) {
		 laine+= i;
	}
	public void addArgile(int i) {
		 argile+= i;
	}
	public void addBois(int i) {
		 bois+= i;
	}
	public void addPierre(int i) {
		 fer += i;
	}
	
	
	public void payBle(int i) {
		 ble+= i;
	}
	public void payMouton(int i) {
		 laine+= i;
	}
	public void payArgile(int i) {
		 argile+= i;
	}
	public void payBois(int i) {
		 bois+= i;
	}
	public void payPierre(int i) {
		 fer += i;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Ressources [ble=" + ble + ", laine=" + laine + ", argile=" + argile + ", bois=" + bois + ", fer="
				+ fer + "]";
	}
	public void placeColonie() {this.compteurColonies --; }
	
	public void placeVille() {this.compteurVilles --;
	this.compteurColonies ++ ; }
	
	public void placeRoute() {this.compteurRoutes --; }
	public String getFer() {
		// TODO Auto-generated method stub
		return null;
	}

}