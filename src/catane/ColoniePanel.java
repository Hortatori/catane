package catane;


import java.awt.Graphics;

import javax.swing.JPanel;

public class ColoniePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Joueur j ;
	Sommet s;
	
	public ColoniePanel(Joueur j, Sommet s ) {
		this.j = j;
		this.s = s;
		this.setBounds(0, 0, 700, 700);
		this.setOpaque(false);
		
	}
	
	
	
	
	protected void paintComponent(Graphics g) {
		 
		 super.paintComponent(g); // n'enlève pas la gestion standard du dessin du composant
	 
		 g.setColor(this.j.getCouleur());  
		 int  x = 130 + 100 * this.s.largeur ;
		 int  y = 130 + 100 * this.s.hauteur ;
		// 150 pixels de mer -20 pour le rayon de la forme
		
		 g.fillOval(x,y,40 ,40);
	 
	   }
	

}