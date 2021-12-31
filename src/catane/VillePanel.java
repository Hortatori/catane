package catane;

import java.awt.Graphics;
import java.awt.Color ;

public class VillePanel extends ColoniePanel {

	public VillePanel(Joueur j, Sommet s) {
		super(j, s);
	}
	
	
	
	protected void paintComponent(Graphics g) {
		 
		 super.paintComponent(g); // n'enlève pas la gestion standard du dessin du composant
	 
		   
		 int width ;
		 int height ;
		 int  x = 140 + 100 * this.s.largeur ;
			int  y = 140 + 100 * this.s.hauteur ;
		
		 g.setColor(Color.BLACK);
		 g.fillOval(x,y,20 ,20);
		 
	   }
	

}
