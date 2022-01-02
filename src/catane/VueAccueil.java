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
		this.setBounds(700,0, 300, 700);
		this.setLayout(new GridLayout(3,1));
		
		JPanel bienvenue = new JPanel ();
	
		bienvenue.add(new JLabel ("Bienvenue Dans les Colons de Catabe ! "));
		bienvenue.add(new JLabel( " ! " ));
		bienvenue.add(new JLabel( "Choisissez le nombre de Joueurs" ));
		bienvenue.setForeground(Color.WHITE);
		bienvenue.setOpaque(false);
		this.add(bienvenue);
		
		JButton b3 	=  new JButton ("3") ;
		JButton b4 	=  new JButton ("4") ;
		b3.addActionListener(e  -> {
		b3.setVisible(false);
		b4.setVisible(false);
		bienvenue.setVisible(false);
		this.setLayout(new GridLayout(3, 1));
		this.remove(bienvenue);
		this.remove(b4);
		this.remove(b3);
		setJoueurs(3) ;
		}       ); 
		
		b4.addActionListener(e  -> {setJoueurs(4) ;
		bienvenue.setVisible(false);
		b3.setVisible(false);
		b4.setVisible(false);
		this.setLayout(new GridLayout(4, 1));
		this.removeAll();}       );
		this.add(b3);
		this.add(b4);
		
		
		
		
		
		
	}
	
	
	public void setJoueurs(int nbj ) {
		this.nbj = nbj ;
		
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
				VueAccueil.this.p.initialiser();
				System.out.println("lets gooo");
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
			close();} );
		GridBagConstraints co = new GridBagConstraints();
		co.gridx =0 ;
		co.gridy = 1 ;
		co.fill = GridBagConstraints.HORIZONTAL;
		this.add(oui, co);
		
		JButton non = new JButton("non") ;
		non.addActionListener( e -> close() );
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
		finAccueil();
		
		}
		}
	
	}
	
	
	
}