package catane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout ;






//
//public class VueCase  extends JPanel {
//
//	public VueCase ( Case c) {
//		super();
//		int x =  100* c.NO.largeur ; 
//		int y = 100 * c.NO.hauteur ;
//		this.setBounds(x, y, 100, 100);
//		this.setLayout(new FlowLayout()) ;
//		this.setBackground(new Color (133, 122, 123));
//		
//	}



public class VueCase  extends JLabel {

	public Color selectColor(Paysage p) {
		
		switch (p) {
		
		case DESERT :
			return Color.YELLOW ;
			
		case MONTAGNE :
			return Color.GRAY ;
			
			
		case PRE :
			return Color.RED ;
		
			
		case FORET :
			return Color.GREEN ;
			
			
		case COLLINE :
			return Color.MAGENTA ;
			
			
		case CHAMP :
			return Color.WHITE ;
			
			
		default :
			return Color.BLACK ;
		
			
		}
		
	}
	
	
	public VueCase ( Case c) {
		super();
		int x =  100* c.NO.largeur ; 
		int y = 100 * c.NO.hauteur ;
		this.setBounds(x, y, 100, 100);
		this.setMinimumSize(new Dimension(50, 50));
		//this.setLayout(new FlowLayout()) ;
		this.setBackground(new Color (133, 122, 123));
		Color col = this.selectColor( c.type);
		this.setBackground(col);
		this.setOpaque(true);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
	}
	
	
	
}


