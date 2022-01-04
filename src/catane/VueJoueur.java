package catane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import java.awt.GridLayout ;
import java.awt.GridBagLayout ;
import java.awt.GridBagConstraints ;
import java.awt.* ;
import javax.swing.JLabel ;

public class VueJoueur extends JPanel {
	
Joueur joueur ;
	
	VueJoueur() {
		super();
		this.setBackground(new Color(050, 000, 000));
		this.setBounds(700,200, 300, 500);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		GridBagConstraints c = new GridBagConstraints();
//		c.gridx = 0;
//		c.gridy = 0;
//		c.anchor = GridBagConstraints.NORTH ;
//		//c.weighty = 0.2 ;
//		JPanel tour = new JPanel ();
//	
//		tour.add(new JLabel ("TOUR "));
//		
//		tour.setOpaque(false);
//		//tour.setMaximumSize(new Dimension(400, 100));
//		this.add(tour, c);
		
		}
	
	VueJoueur(Joueur j){
		this() ;
		this.joueur = j ;
		CoordPanel pa = new CoordPanel("Placez une première colonie gratuitement ! ") ;
		this.add(pa);
		
		// problème, j'ai besoin ici de la coordonnée renvoyée par le coord Panel, mais je ne vois pas comment dire à l'ordinateur d'attendre 
		// qu'on me renvoie la coordonnée avant de passer à la suite 
//		int[] result = pa.getResult();
//		Sommet s = j.partie.plateau.plateauS[result[0]][result[1]] ;
//		j.placerColonieInit(s);
//		j.partie.view.drawColonie(j, result[0], result[1]);
//		
		
		
		
	}
	
	VueJoueur(Joueur j, int de) {
		
		this();
		this.joueur = j ;
		
		this.joueur.partie.view.ResetCommunicator();
		this.joueur.partie.view.Communicate("C'est le tour de "+ this.joueur.getNom());
		
		this.joueur.partie.view.Communicate("Les dés ont donné " + de);
		
//		if (de != 42) {
//		this.add (new JLabel ( " Le dï¿½ a donnï¿½"+ de)) ; }
		
		VueRessources r = new VueRessources(j);
		
//		GridBagConstraints c = new GridBagConstraints();
//		c.gridx = 0;
//		c.gridy = 1;
//		c.anchor = GridBagConstraints.CENTER ;
		
		this.add(r);
		
		ActionPanel ap = new ActionPanel()  ;
		this.add(ap);
		}
		
		
		
	
	
	
	
public class ActionPanel extends JPanel {
	public ActionPanel() {
		super();
		this.setSize(300, 400) ;
		this.setLayout ( new GridLayout (6, 1)) ;
		this.setOpaque(false);
		VueJoueur.this.joueur.partie.view.Communicate("Choisissez une action ");
		
		
		JButton colonie = new JButton("Placer une nouvelle colonie") ;
		// colonie . addActionListener( e -> { this.setVisible(false) ;
		// 								CoordPanel p= new CoordPanel("Cliquez sur le bouton correspondant ï¿½ l 'endroit ou vous voulez installer une colonie" );
		// 								VueJoueur.this.add (p);
		// 								int [] result = p.getResult();
		// 								ConstruireColonie(VueJoueur.this.joueur, result [0] , result [1]);
										
			// ;} ) ;
		this.add(colonie);
		
		
		
		JButton ville = new JButton("Placer une nouvelle villee") ;
		// colonie . addActionListener( e -> {    ;} ) ;
		this.add(ville);
		
		
		
		JButton route = new JButton("Placer une nouvelle route") ;
		// colonie . addActionListener( e -> {    ;} ) ;
		this.add(route);
		
		
		JButton acheterCarte = new JButton("Acheter une carte développement") ;
		// colonie . addActionListener( e -> {    ;} ) ;
		this.add(route);
		
		JButton utiliserCarte = new JButton("Utiliser une carte développement") ;
		// colonie . addActionListener( e -> {    ;} ) ;
		this.add(route);
		
		JButton commerce = new JButton("Faire du commerce") ;
		// colonie . addActionListener( e -> {    ;} ) ;
		this.add(commerce);
		
		JButton finTour = new JButton("Finir votre tour") ;
		// colonie . addActionListener( e -> {    ;} ) ;
		this.add(finTour);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}


public class CoordPanel extends JPanel {
	
	int resultx = 42 ;
	int resulty = 42 ;
	
	CoordPanel(String question) {
		
	
		super() ;
		this.setSize(300 , 400);
		this.setLayout(new FlowLayout()) ;
		VueJoueur.this.joueur.partie.view.Communicate (question) ;
		JPanel grille = new JPanel();
		grille.setLayout(new GridLayout(5,5));
		
		for (int h = 0 ; h<5; h++ ) {
			for (int w = 0 ; w<5; w++ ) {
				
				String nom = "[" + w+ ";"+ h +"]" ;
				RadioCoord radio = new RadioCoord(nom, h, w) ;
				int X = w ;
				int Y = h;
				radio.addActionListener( e ->  {
				this.resultx = X;
				this.resulty = Y;
				this.setVisible(false) ;
				
				}
				);
				grille.add(radio);
				
			}
		}
		this.add(grille);
	}
	
	public int[] getResult() {
		int [] result = { this.resultx , this.resulty } ;
		return result;
	}
	
}
	

}
