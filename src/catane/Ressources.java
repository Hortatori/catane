/** les ressources qui servent d'argent dans le jeu
 *  des methodes d'acces,
 *  de modification,
 * 	de conversion Paysage <--> Ressource
 */

package catane;

public class Ressources {

	private int ble;
	private int mouton;
	private int argile;
	private int bois;
	private int pierre;

	private int compteurColonies = 5;
	private int compteurVilles = 4;
	private int compteurRoutes = 15;

	private int nb_chevaliers;

	public void incrementeChevalier() {
		this.nb_chevaliers++;
	}

	public int getChevalier() {
		return this.nb_chevaliers;
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
		ble += i;
	}

	public void addMouton(int i) {
		mouton += i;
	}

	public void addArgile(int i) {
		argile += i;
	}

	public void addBois(int i) {
		bois += i;
	}

	public void addPierre(int i) {
		pierre += i;
	}

	public void payBle(int i) {
		ble -= i;
	}

	public void payMouton(int i) {
		mouton -= i;
	}

	public void payArgile(int i) {
		argile -= i;
	}

	public void payBois(int i) {
		bois -= i;
	}

	public void payPierre(int i) {
		pierre -= i;
	}

	@Override
	public String toString() {
		return "Ressources [ble=" + ble + ", mouton=" + mouton + ", argile=" + argile + ", bois=" + bois + ", pierre="
				+ pierre + "]";
	}

	public void placeColonie() {
		this.compteurColonies--;
	}

	public void placeVille() {
		this.compteurVilles--;
		this.compteurColonies++;
	}

	public void placeRoute() {
		this.compteurRoutes--;
	}

	public void incrementRessource(Paysage paysage, int quantite) {
		switch (paysage) {
			case FORET:
				this.bois += quantite;
				break;
			case MONTAGNE:
				this.pierre += quantite;
				break;

			case COLLINE:
				this.argile += quantite;
				break;

			case PRE:
				this.mouton += quantite;
				break;

			case CHAMP:
				this.ble += quantite;
				break;

			case DESERT:
				break;
		}

	}

	public int total() {
		return ble + mouton + argile + bois + pierre;
	}

	public boolean prelevable(Paysage paysage) {

		switch (paysage) {
			case FORET:
				return this.bois > 0;

			case MONTAGNE:
				return this.pierre > 0;

			case COLLINE:
				return this.argile > 0;

			case PRE:
				return this.mouton > 0;

			case CHAMP:
				return this.ble > 0;

			default:
				return false;
		}

	}

	public Paysage Convertir(int i) {

		switch (i) {
			case 0:
				return Paysage.FORET;

			case 1:
				return Paysage.MONTAGNE;

			case 2:
				return Paysage.COLLINE;

			case 3:
				return Paysage.PRE;

			case 4:
				return Paysage.CHAMP;

			default:
				return Paysage.DESERT;
		}

	}

	public String paysagetoString(Paysage paysage) {

		switch (paysage) {
			case FORET:
				return "bois";

			case MONTAGNE:
				return "pierre";

			case COLLINE:
				return "argile";

			case PRE:
				return "mmouton";

			case CHAMP:
				return "ble";

			default:
				return "sable et larmes";
		}
	}

}
