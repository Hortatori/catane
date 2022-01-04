package catane;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color ;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Vue extends JFrame{

//JPanel CentralPanel ;
Partie p ;
VuePlateau vp ;
JPanel leftpanel ;
CommunicatorPanel cp = new CommunicatorPanel() ;


public Vue(Partie p) {
	super();
	this.p = p ;
	this.vp = p.plateau.vp ;
	this.vp = vp ;
	this.setResizable(false);
	this.getContentPane().setLayout(null) ;
	this.setSize(1010,722);
	this.setTitle("Les colons de Cata");
	this.setVisible(true) ;
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	//CentralPanel = new JPanel() ;
	//VuePlateau vp = new VuePlateau();
	
	this.getContentPane().add(vp ) ;
	
//	VueAccueil vj = new VueAccueil(p);
//	this.leftpanel = vj ;
//	this.getContentPane().add(vj ) ;
//	this.add(new DrawComponent()) ;
	

}
	
public void updateJoueur(Joueur j, int de ) {
	VueJoueur v = new VueJoueur(j, de) ;
		this.leftpanel = v;
		this.getContentPane().add(v);
		this.repaint();
	}

public void drawRoute(Joueur j, Route r) {
	this.add(new RoutePanel(j, r) ) ;
	this.repaint();
	
}

public void drawColonie (Joueur j, int x, int y) 
{
	ColoniePanel cp = new ColoniePanel(j, this.p.plateau.plateauS[y][x]) ;
	cp.setVisible(true);
	this.add(cp ) ;
	this.repaint();
	
}

public void drawVille(Joueur j, int x, int y) 
{
	this.add(new VillePanel(j, this.p.plateau.plateauS[y][x]) ) ;
	this.repaint();
	
}
public void ResetCommunicator () {
	this.cp.message = "" ;
	this.cp.texte.setText("");
}
public void Communicate (String s) {
	this.cp.message  +=System.lineSeparator() + s; 
	this.cp.texte.append(System.lineSeparator()+ s);
}





public class CommunicatorPanel extends JPanel {
	
	private String message  = "Je suis le Communicateur, lisez mes insctructions très attentivement car je donne la clé de tout ce que vous pouvez, devez et ne devez pas faire dans ce jeu ! ";
	private JTextArea texte = new JTextArea(message, 5, 5) ;
	
	
	CommunicatorPanel() {
		super() ;
		//this.setLayout (null);
		JLabel catane = new JLabel ("CATANE") ;
		catane.setPreferredSize(new Dimension(150, 50));
		catane.setBackground(new Color (200, 50, 50));
		catane.setOpaque(true);
		catane.setAlignmentX(SwingConstants.CENTER); 
		this.add(catane);
		this.setBounds(700,000, 300, 200);
		this.texte.setPreferredSize(new Dimension (290, 130));
		this.texte.setMaximumSize(new Dimension (290, 130));
		texte.setEditable(false);
		texte.setLineWrap(true);
		this.add(texte);
		
		this.setBackground (   new Color (150, 200, 150)) ;
		Vue.this.add(this);
		
	}
	
	
	
}


}
