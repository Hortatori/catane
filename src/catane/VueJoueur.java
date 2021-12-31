package catane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout ;
import java.awt.GridBagLayout ;
import java.awt.GridBagConstraints ;
import java.awt.* ;

public class VueJoueur extends JPanel {
	
	VueJoueur() {
		super();
		this.setBackground(new Color(050, 000, 000));
		this.setBounds(700,0, 300, 700);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.NORTH ;
		//c.weighty = 0.2 ;
		JPanel tour = new JPanel ();
	
		tour.add(new JLabel ("tour"));
		
		tour.setOpaque(false);
		//tour.setMaximumSize(new Dimension(400, 100));
		this.add(tour, c);
		
		}
	
	VueJoueur(Joueur j){
		this() ;
		this.add (new JLabel ("placez votre colonie de d�part")) ; 
		
	}
	
	VueJoueur(Joueur j, int de) {
		this();
		if (de != 42) {
		this.add (new JLabel ( " Le d� a donn�"+ de)) ; }
		
		VueRessources r = new VueRessources(j);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER ;
		
		
		this.add(r, c);
		
		CoordPanel pa = new CoordPanel("wtf ?") ;
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 0;
		c2.gridy = 2;
		c2.anchor = GridBagConstraints.SOUTH ;
		
		this.add(pa, c2);
		
	}
	
	
	
public class ActionPanel extends JPanel {
	public ActionPanel() {
		super();
		this.setSize(300, 400) ;
		this.setLayout ( new GridLayout (6, 1)) ;
		this.setOpaque(false);
		this.add(new JLabel ( " Choisissez une action :")) ;
		
		JButton colonie = new JButton("Placer une nouvelle colonie") ;
		// colonie . addActionListener( e -> {    ;} ) ;
		this.add(colonie);
		
		
		
		JButton ville = new JButton("Placer une nouvelle colonie") ;
		// colonie . addActionListener( e -> {    ;} ) ;
		this.add(ville);
		
		
		
		JButton route = new JButton("Placer une nouvelle colonie") ;
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
	CoordPanel(String question) {
		
		super() ;
		this.setSize(300 , 400);
		this.setLayout(new FlowLayout()) ;
		this.add(new JLabel(question)  );
		JPanel grille = new JPanel();
		grille.setLayout(new GridLayout(5,5));
		
		for (int h = 0 ; h<5; h++ ) {
			for (int w = 0 ; w<5; w++ ) {
				
				String nom = "[" + w+ ";"+ h +"]" ;
				RadioCoord radio = new RadioCoord(nom, h, w) ;
				//listener
				grille.add(radio);
				
			}
		}
		this.add(grille);
	}
	
}
	

}
