package catane;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Graphics ;
import java.awt.Color ;

class PortPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Port p;
	
	   public PortPanel(Port p) {
		   this.p = p;
		   int x = 100* p.getS1().largeur ;
		   int y = 100* p.getS1().hauteur ;
		   this.setOpaque(false);
		 // on oriente le cercle différement selon l orientation du port
		   if ((p.getOrientation() == Cardinal.S)|| (p.getOrientation() == Cardinal.N)) {
			   x += 100;
		   }
		   
		   if (p.getOrientation() == Cardinal.N) {
			   y-=100;
		   }
		   
		   if (p.getOrientation() == Cardinal.O) {
			   x+=00;
		   }
		   
		   if (p.getOrientation() == Cardinal.E) {
			   x+=100;
		   }
		   this.setBounds(50+x, 150 +   y, 100, 100);
		   JLabel label = new JLabel(p.afficherTaux());
		   label.setVerticalTextPosition(SwingConstants.CENTER);
		   label. setHorizontalAlignment(SwingConstants.CENTER) ;
		   label. setVerticalAlignment(SwingConstants.BOTTOM) ;
		   label.setAlignmentY(SwingConstants.CENTER);
		   label.setAlignmentX(SwingConstants.CENTER);
		   
		   label.setForeground(Color.WHITE);
		   this.add(label);
	
	}

	protected void paintComponent(Graphics g) {
	 
		 super.paintComponent(g); // n'enlève pas la gestion standard du dessin du composant
	 
		 g.setColor(Color.BLUE);  
		 int x ;
		 int y ;
		 switch(this.p.getOrientation()) {
		  case S :
		    x = 0 ;
		    y = -50 ;
		    break;
		  case O :
		    x = 50;
		    y = 0 ;
		    //g.setColor(Color.GREEN);
		    break;
		  case E:
			    x =  - 50 ;
			    y = 0 ;   
			    //g.setColor(Color.RED);
			    break ;
		  case N :
		    x= 0;
		    y = 50;
		    break;
		  default :
			  return ;
		}
		 
		 
		 g.fillOval(x,y,100,100);
	 
	   }
	}
