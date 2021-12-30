package catane;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import java.awt.Dimension;

public class DrawComponent extends JComponent {
	
	private static final int DEFAULT_WIDTH = 400 ;
	private static final int DEFAULT_HEIGHT = 400 ;
	
	
	public void paintComponent( Graphics g) {
		
		//Graphics g = this.vp.ile.getGraphics();
		Graphics2D g2 = (Graphics2D) g ;
		
		Rectangle2D rect = new Rectangle2D.Double(100,100,100,100);
		g2.draw(rect);
		Ellipse2D.Double e = new Ellipse2D.Double ( 50, 50, 50, 50) ;
		
		g2.draw(e) ;
		
		
		
	}
	
	
	public Dimension getPreferredSize() {return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT) ; }
	
	

}
