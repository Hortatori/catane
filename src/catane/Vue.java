package catane;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vue extends JFrame{

//JPanel CentralPanel ;
Partie p ;
VuePlateau vp ;
JPanel leftpanel ;



public Vue(Partie p) {
	super();
	this.p = p ;
	this.vp = p.plateau.vp ;
	this.setResizable(false);
	this.getContentPane().setLayout(null) ;
	this.setSize(1010,722);
	this.setTitle("Les colons de Cata");
	this.setVisible(true) ;
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	//CentralPanel = new JPanel() ;
	//VuePlateau vp = new VuePlateau();
	this.vp = vp ;
	this.getContentPane().add(vp ) ;
	
	VueAccueil vj = new VueAccueil(p);
	this.leftpanel = vj ;
	this.getContentPane().add(vj ) ;
	this.add(new DrawComponent()) ;
	

}
	
public void updateJoueur(Joueur j, int de ) {
	VueJoueur v = new VueJoueur(j, d) ;
		this.leftpanel = v;
		this.getContentPane().add(v);
		this.repaint();
	}

public void drawRoute(Joueur j, Route r) {
	this.add(new RoutePanel(j, r) ) ;
	this.repaint();
	
}


	
}
