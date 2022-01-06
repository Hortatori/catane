package catane;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class VoleurPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Case caseVoleur;
	private Plateau p ;
	private int x ;
	private int y ;
	
	public VoleurPanel(Plateau p ) {
		this.p = p ;
		this.setBounds(0, 0, 700, 700);
		this.setOpaque(false);
		for (Case[] ct : this.p.plateauC) {
			for (Case c : ct)
			{
			if (c.type == Paysage.DESERT) {
				x = 190 + 100 * c.NO.largeur ;
				y = 230 + 100 * c.NO.hauteur ;
			}
			}
			
		}
	}
	
	
	
	
	protected void paintComponent(Graphics g) {
		 
		 super.paintComponent(g); 
		 
		 g.setColor(Color.BLACK);  
		 
		
		 g.fillRect(x,y,20 ,20);
		 g.fillOval(x+3,y-15,15 ,15);
	 
	   }
	
	public void moveVoleur(Case c) {
		x = 190 + 100 * c.NO.largeur ;
		y = 230 + 100 * c.NO.hauteur ;
		
        c.setStatutVoleur(true);
        this.caseVoleur = c;
        
	}
	

}
