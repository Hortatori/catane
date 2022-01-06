package catane;

import catane.VueJoueur.CoordPanel;

public class CarteRoute extends Carte {

	
	public CarteRoute() {
		this.effet = "permet de construire deux routes gratuitement" ;
	}
	@Override
	protected void actionCarte() {
		Joueur j = this.getPossesseur();
		Partie p = j.partie ;
		
		
		if(j.isIa()) {
			j.matrix4.ij.construireRoute(j, j.matrix4.getCoord(), j.matrix4.getCoord());
			j.matrix4.ij.construireRoute(j, j.matrix4.getCoord(), j.matrix4.getCoord());
		}
		else {
		if (this.getPossesseur().partie.plateau.graphique) {
			
			j.vj.setVisible(true); ;
			j.partie.view.ResetCommunicator();
			j.partie.view.Communicate(j.getNom());
			j.partie.view.Communicate("On vous offre deux colonies gratuites");
			CoordPanel cp= j.vj.new CoordPanel("Cliquez sur le bouton correspondant à l 'endroit ou vous voulez installer une colonie" , true);
//			j.partie.view.add(cp);
//			cp.setBounds(700, 200, 300, 380);
			j.vj.add(cp);
			cp.setVisible(true);
			cp.addCoordListener( event -> {
				 j.placerColonieInit(j.partie.plateau.plateauS[j.vj.y][j.vj.x]);
				cp.drawColonie(j.vj.x,j.vj.y) ;
				cp.setVisible(false);
												
				CoordPanel cp2= j.vj.new CoordPanel("Cliquez sur le bouton correspondant à l 'endroit ou vous voulez installer une colonie" , true);
				j.vj.add(cp2);
				cp2.addCoordListener( event2 -> {
					 j.placerColonieInit(j.partie.plateau.plateauS[j.vj.y][j.vj.x]);
					cp2.drawColonie(j.vj.x,j.vj.y) ;
					cp2.setVisible(false);
					j.vj.ap.setVisible(true);
				});
				
				
				
				
				
				
				
				
				
				///// fin du listerner  englobant
							;});
				
			   
			
			
			
		}
		else { 
			System.out.println("vous avez le droit à deux routes offertes sans payer de ressource");
			 p.ij.construireRoute(j, p.ij.getCoord (j.getNom() + ", pour placer la  première , donnez les coordonnées du départ"), p.ij.getCoord ("et celle de l'arrivée"));
	          
			 p.ij.construireRoute(j, p.ij.getCoord (j.getNom() + ", pour placer la seconde , donnez les coordonnées du départ"), p.ij.getCoord ("et celle de l'arrivée"));
			
		}
		
	}
	}
}