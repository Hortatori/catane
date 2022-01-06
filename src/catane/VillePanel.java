package catane;

import java.awt.Graphics;
import java.awt.Color ;

public class VillePanel extends ColoniePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public VillePanel(Joueur j, Sommet s) {
		super(j, s);
	}
	
	
	
	protected void paintComponent(Graphics g) {
		 
		 super.paintComponent(g); 
	 
		   
		 int  x = 125 + 100 * this.s.largeur ;
			int  y = 125 + 100 * this.s.hauteur ;
		
		 g.setColor(Color.BLACK);
		 g.fillOval(x,y,50, 50);
		 
	   }
	

}
