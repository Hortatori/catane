package catane;

public class CarteChevalier extends Carte {
	
	public CarteChevalier() {
		this.effet = "Permet de faire fuir le voleur et de renforcer son armée" ;
		
	}
	
	@Override
	protected void actionCarte() {
		Joueur j = this.getPossesseur();
		j.getR().incrementeChevalier();
		j.partie.vo.Voleurfuite(j);
	
	}

}