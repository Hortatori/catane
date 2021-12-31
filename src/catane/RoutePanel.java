package catane;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RoutePanel extends JPanel {
	Joueur j ;
	Route r;
	
	public RoutePanel(Joueur j, Route r ) {
		this.j = j;
		this.r = r;
		this.setBounds(0, 0, 700, 700);
		this.setOpaque(false);
	}
	
	
	
	
	protected void paintComponent(Graphics g) {
		 
		 super.paintComponent(g); // n'enl�ve pas la gestion standard du dessin du composant
	 
		 g.setColor(this.j.getCouleur());  
		 int width ;
		 int height ;
		 int  x = 150 + 100 * this.r.depart.largeur ;
			int  y = 150 + 100 * this.r.depart.hauteur ;
			
		 if (this.r.horizontale) {
			  width = 100 ;
			  height = 20 ;
			  y -= 10 ;
		 }
		 else {  width = 15; 
		 height = 100 ; 
		 x -= 10 ;
		 
		 }
		
		 g.fillRect(x,y,width ,height);
	 
	   }
	

}
