package catane;

public class Ressources {

	private int ble ;
	private int mouton ;
	private int argile ;
	private int bois ;
	private int pierre ;
	
	private int compteurColonies = 5;
    private int compteurVilles = 4;
    private int compteurRoutes = 15;

	
	
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
		return mouton;
	}
	public int getArgile() {
		return argile;
	}
	public int getBois() {
		return bois;
	}
	public int getPierre() {
		return pierre;
	}
	
	
	public void addBle(int i) {
		 ble+= i;
	}
	public void addMouton(int i) {
		 mouton+= i;
	}
	public void addArgile(int i) {
		 argile+= i;
	}
	public void addBois(int i) {
		 bois+= i;
	}
	public void addPierre(int i) {
		 pierre += i;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Ressources [ble=" + ble + ", mouton=" + mouton + ", argile=" + argile + ", bois=" + bois + ", pierre="
				+ pierre + "]";
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