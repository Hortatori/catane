package catane;

public class CarteVictoire extends Carte {
	public CarteVictoire() {
		this.effet = "accorde un point de victoire sans rien faire" ;
	}
	
	public void  setJoueur(Joueur j) {
		super.setPossesseur(j);
		this.Jouer();
		
	
	}
	
	@Override
	protected void actionCarte() {
		this.getPossesseur().incrementeVictoire(); }

}