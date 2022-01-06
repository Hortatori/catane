package catane;

public class CarteMonopole extends Carte{

	
	public CarteMonopole() {
		this.effet = "Permet de s'approprier toutes les ressources d'un type donné " ;
	}
	
	@Override
	protected void actionCarte() {
		Joueur j = this.getPossesseur();
		Partie p = j.partie ;
		Paysage paysage ;
		
		if (j.isIa()) {
		 paysage = j.matrix4.demandeRessource() ;
		 Piller(paysage, j);
		}
		else {
		if (!this.getPossesseur().partie.plateau.graphique) {
		System.out.println("Tous les joueurs vont vous donner leur ressource d'un type de votre choix");
		paysage = j.partie.ij.demandeRessource();
		Piller(paysage, j);
		
			
		}
		else {
			p.view.ResetCommunicator();
			p.view.Communicate("Tous les joueurs vont vous donner leur ressource d'un type de votre choix");
			j.vj.removeAll();
			
			VueJoueur.RessourcePanel rp = j.vj.new RessourcePanel(" Quelle ressource voulez vous piller ?" , this) ;
			rp.addOptionListener(e -> Piller (rp.paysage, j));
			j.vj.add(rp ) ;
			
		}
		}
	}
	
	
	
public void Piller (Paysage paysage , Joueur j) {
	int i = 0;
	switch (paysage)  {
	


	
	case MONTAGNE :
		 i = 0 ;
		for (Joueur former : j.partie.joueurs ) {
			int nressource = former.getR().getPierre() ;
			i = i+nressource ;
			former.getR().payPierre(nressource);
		}
		j.getR().addPierre(i);
		
		
	case PRE :
			
			i = 0 ;
			for (Joueur former : j.partie.joueurs ) {
				int nressource = former.getR().getMouton() ;
				i = i+nressource ;
				former.getR().payMouton(nressource);
			}
			j.getR().addMouton(i);
		
	case FORET :
		i = 0 ;
		for (Joueur former : j.partie.joueurs ) {
			int nressource = former.getR().getBois() ;
			i = i+nressource ;
			former.getR().payBois(nressource);
		}
		j.getR().addBois(i);
	
		
		
	case COLLINE :
			
		i = 0 ;
		for (Joueur former : j.partie.joueurs ) {
			int nressource = former.getR().getArgile() ;
			i = i+nressource ;
			former.getR().payArgile(nressource);
		}
		j.getR().addArgile(i);
	
		
		
	case CHAMP :
		i = 0 ;
		for (Joueur former : j.partie.joueurs ) {
			int nressource = former.getR().getBle() ;
			i = i+nressource ;
			former.getR().payBle(nressource);
		}
		j.getR().addBle(i);
	
		
		
	default :
		return ;}
	
		}
}