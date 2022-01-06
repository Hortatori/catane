package catane;

import java.util.ArrayList;
import java.util.Random;

public class CarteInvention extends Carte {

	public CarteInvention() {
		this.effet = " Donne deux ressources aléatoirement" ;
	}
	
	
	protected void actionCarte() {
		// on génère deux ressources aléatoirement et on les attribue au possesseur 
		ArrayList<Paysage> ressources = new ArrayList<Paysage>() ;
		for (Paysage r : Paysage.values()) {ressources.add(r);}
		Random rd = new Random() ;
		int r1 = rd.nextInt(5);
		Paysage pr1 = ressources.get(r1);
		int r2 = rd.nextInt(5);
		Paysage pr2 = ressources.get(r2);
		this.getPossesseur().getR().incrementRessource(pr1, 1);
		this.getPossesseur().getR().incrementRessource(pr2, 1);
		this.getPossesseur().partie.Communicate("vous avez reçu deux nouvelles ressources");
		
		
		
		
		
		
		
	}

}