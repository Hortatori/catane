package catane;

import catane.VueJoueur.CoordPanel;

public class CarteRoute extends Carte {

	@Override
	protected void actionCarte() {
		Joueur j = this.getPossesseur();
		Partie p = j.partie ;
		if (this.getPossesseur().partie.plateau.graphique) {
			
			j.vj.setVisible(false) ;
			j.partie.view.ResetCommunicator();
			j.partie.view.Communicate(j.getNom());
			j.partie.view.Communicate("On vous offre deux colonies gratuites");
			CoordPanel cp= j.vj.new CoordPanel("Cliquez sur le bouton correspondant à l 'endroit ou vous voulez installer une colonie" , true);
			cp.addCoordListener( event -> {
				 j.placerColonieInit(j.partie.plateau.plateauS[j.vj.y][j.vj.x]);
				cp.drawColonie(j.vj.x,j.vj.y) ;
				cp.setVisible(false);
												
				CoordPanel cp2= j.vj.new CoordPanel("Cliquez sur le bouton correspondant à l 'endroit ou vous voulez installer une colonie" , true);
				cp2.addCoordListener( event2 -> {
					 j.placerColonieInit(j.partie.plateau.plateauS[j.vj.y][j.vj.x]);
					cp2.drawColonie(j.vj.x,j.vj.y) ;
					cp2.setVisible(false);
					j.vj.setVisible(true);
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