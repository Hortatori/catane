package catane;

import catane.VueJoueur.CoordPanel;
import catane.VueJoueur.RouteAPanel;
import catane.VueJoueur.RouteDPanel;

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
			
			
			j.partie.view.ResetCommunicator();
			j.partie.view.Communicate(j.getNom());
			j.partie.view.Communicate("On vous offre deux routes gratuites");
			VueJoueur.RouteDPanel pb = j.vj.new RouteDPanel(" Donnez la coordonnée du départ ");
			
			j.vj.add(pb);
			j.vj.repaint();
			pb.addCoordListener(event2 -> {

				j.vj.xbuffer = j.vj.x;
				j.vj.ybuffer = j.vj.y;
				int stox = j.vj.x;
				int stoy = j.vj.y;
				pb.setVisible(false);
				j.vj.repaint();

				RouteAPanel pc = j.vj.new RouteAPanel(" Donnez la coordonnée de l'arrivée ", stox, stoy);
				j.vj.add(pc);
				j.vj.repaint();
				pc.addCoordListener(event3 -> {
					Plateau pl = j.partie.plateau;
					j.vj.joueur.partie.view.Communicate(stox + "buffer" + stoy);
					Route r = j.placerRoute(pl.plateauS[j.vj.ybuffer + 1][j.vj.xbuffer + 1],
							pl.plateauS[j.vj.y + 1][j.vj.x + 1], pl);
					j.partie.view.vp.drawRoute(j, r);
					pc.setVisible(false);
					j.vj.add(j.vj.new ActionPanel());
					j.vj.repaint();

				});
				
				
				
				
				
				
				
				
				
				
				
				///// fin du listerner  englobant
							;});
			//j.vj.ap.setVisible(false);
			// je ne comprends pas pourquoi il ne veut pas s afficher
			j.vj.setVisible(true); ;
			j.vj.removeAll();
			j.vj.add(pb);
			j.vj.repaint();
			
			j.partie.view.vj.setVisible(true);
			j.partie.view.vj.add(pb);
			j.partie.view.repaint();
			j.partie.view.vj.setVisible(false);
			VueJoueur vjlasthope = new VueJoueur (j, 0);
			vjlasthope.removeAll() ;  // on enleve le ap 
			vjlasthope.add (pb);
			j.partie.view.add (vjlasthope);
			vjlasthope.repaint();
				
			   
			
			
			
		}
		else { 
			System.out.println("vous avez le droit à deux routes offertes sans payer de ressource");
			 p.ij.construireRoute(j, p.ij.getCoord (j.getNom() + ", pour placer la  première , donnez les coordonnées du départ"), p.ij.getCoord ("et celle de l'arrivée"));
	          
			 p.ij.construireRoute(j, p.ij.getCoord (j.getNom() + ", pour placer la seconde , donnez les coordonnées du départ"), p.ij.getCoord ("et celle de l'arrivée"));
			
		}
		
	}
	}
}