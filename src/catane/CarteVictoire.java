package catane;

public class CarteVictoire extends Carte {
	
	
	public void  setJoueur(Joueur j) {
		super.setPossesseur(j);
		this.jouerCarte();
		
	
	}
	
	@Override
	protected void actionCarte() {
		this.getPossesseur().incrementeVictoire(); }

}