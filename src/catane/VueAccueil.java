package catane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout ;
import java.awt.GridBagConstraints ;

import java.awt.GridLayout ;
import java.awt.FlowLayout;

public class VueAccueil extends JPanel {
	Partie p ;
	int nbj ;
	VueAccueil(Partie p) {
		super();
		this.p = p ;
		this.setBackground(new Color(050, 000, 000));
		this.setBounds(700,200, 300, 500);
		this.setLayout(new GridLayout(3,1));
		
		this.p.view.Communicate("Bienvenue Dans les Colons de Catane ! ");
		this.p.view.Communicate("Choisissez le nombre de Joueurs");

		
		JButton b3 	=  new JButton ("3") ;
		JButton b4 	=  new JButton ("4") ;
		b3.addActionListener(e  -> {
		b3.setVisible(false);
		b4.setVisible(false);
	
		this.setLayout(new GridLayout(3, 1));
	
		this.remove(b4);
		this.remove(b3);
		setJoueurs(3) ;
		}       ); 
		
		b4.addActionListener(e  -> {
		//bienvenue.setVisible(false);
		b3.setVisible(false);
		b4.setVisible(false);
		this.setLayout(new GridLayout(4, 1));
		this.removeAll();
		setJoueurs(4) ;}       );
		this.add(b3);
		this.add(b4);
		
		
		
		
		
		
	}
	
	
	public void setJoueurs(int nbj ) {
		this.nbj = nbj ;
		this.p.setN_joueur(nbj);
		System.out.println(nbj);
		for (int i = 0 ; i<nbj; i++) {
		System.out.println("ariv");
		NameBox nb = new NameBox(i) ;
		nb.setVisible(true);
		VueAccueil.this.add(nb);
		
		repaint();
		
		}
		
		
		
	}
	
	private static int iaset = 0 ;
	public static void IAset() {
		iaset ++ ;
	}
	public class NameBox extends JPanel {
		int i ;
		JTextField tf ;
		public NameBox(int i) {
			
		super();
		this.i = i ;
		this.setLayout(new GridLayout (2,1));
		this.add (new JLabel (" Joueur "+ i + " : Quel est votre nom")) ;
		JTextField tf = new JTextField(" Tappez votre nom ici ") ;
		this.tf = tf ;
		this.setVisible(true);
		repaint();
		tf.addActionListener(e -> {String nom = getNom(); 
		for (Joueur jou : p.joueurs) {
			if (jou.getNom().equals(nom)) { nom+="2";}
		}
		Joueur j = new Joueur(nom, p);
		
		p.joueurs.add(j);
		
		IABox iab = new IABox( i, j);
		this.setVisible(false);
		
		VueAccueil.this.add(iab);
		VueAccueil.this.remove(this);
		
		
		
		
		
		}
		
				
				
				
				
				
				
				);
		this.add(tf);
		
		}
		
		public String getNom() {
			return tf.getText() ;
		}

		
		public void finAccueil() {
			
			if(iaset == nbj) {
				
				VueAccueil.this.setVisible(false);
				
				System.out.println("lets gooo");
				p.view.getContentPane().remove(VueAccueil.this);
				p.Communicate("tous les joueurs sont pr�ts ! lets go !", true);
				p.Jouer();
			}
			else {   System.out.println( iaset + "est diff de " + i) ;}
		}
	
	
	
	public class IABox extends JPanel {
		int i ;
		Joueur j ;
		
		public IABox(int i, Joueur j) {
			
		super();
		this.i = i ;
		this.j = j;
		this.setLayout(new GridBagLayout ());
		JLabel label = new JLabel (j.getNom() + " est il une IA ?") ;
		GridBagConstraints c = new GridBagConstraints();
		c.gridx =0 ;
		c.gridy = 0 ;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(label, c);
		
		JButton oui = new JButton("oui") ;
		oui.addActionListener( e -> {turnIntoIA();
			j.matrix4.ij.construireColonie(j,j.matrix4.getCoord()) ;
			j.matrix4.ij.construireRoute(j,j.matrix4.getCoord(),j.matrix4.getCoord()    ) ;
			VueJoueur vj = new VueJoueur ();
			vj.init ++;
			if (vj.init == (j.partie.getN_joueur())) {
				NameBox.this.finAccueil();
				System.out.println("initialisation termin�e");
				p.Jouer();
			}
		
		
			close();} );
		GridBagConstraints co = new GridBagConstraints();
		co.gridx =0 ;
		co.gridy = 1 ;
		co.fill = GridBagConstraints.HORIZONTAL;
		this.add(oui, co);
		
		
		
		
		
		
		
		
		JButton non = new JButton("non") ;
		non.addActionListener( e -> { 
		VueJoueur vj = new VueJoueur(j, VueAccueil.this,NameBox.this );
		VueAccueil.this.setVisible(false);
		j.partie.view.add(vj);
		j.partie.view.repaint();
		close() ;
		}
				);
		GridBagConstraints cn = new GridBagConstraints();
		cn.gridx = 0 ;
		cn.gridy = 2 ;
		cn.weightx = 0.5 ;
		cn.fill = GridBagConstraints.HORIZONTAL;
		this.add(non, cn);
		
		
		}
	
		void turnIntoIA() {
			j.setIA();
		}
	
		void close() {
		IAset() ;
		this.setVisible(false);
		//finAccueil();
		
		}
		}
	
	}
	
	
	
	
}