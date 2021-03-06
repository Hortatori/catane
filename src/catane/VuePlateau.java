package catane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class VuePlateau extends JPanel {

	Plateau p;
	JPanel ile;
	VoleurPanel voleur;

	VuePlateau(Plateau p) {

		super();
		this.p = p;
		this.setLayout(null);
		this.setBackground(new Color(150, 200, 255));
		this.setBounds(0, 0, 700, 700);

		JPanel ile = new JPanel();
		this.ile = ile;
		this.setLayout(new BorderLayout());

		ile.setLayout(new GridLayout(4, 4));

		ile.setBackground(new Color(200, 250, 200));
		ile.setOpaque(true);

		ile.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
		ile.setMinimumSize(new Dimension(400, 400));
		ile.setPreferredSize(new Dimension(400, 400));
		ile.setMaximumSize(new Dimension(400, 400));
		ile.setOpaque(false);
		VoleurPanel voleur = new VoleurPanel(p);
		this.voleur = voleur;
		this.add(voleur);
		Graphics g = ile.getGraphics();

		this.add(ile, BorderLayout.CENTER);

		this.repaint();

	}

	public void drawPort(Port p) {
		PortPanel pp = new PortPanel(p);

		this.add(pp);
		this.add(this.ile);
	}

	public void drawRoute(Joueur j, Route r) {
		RoutePanel rp = new RoutePanel(j, r);
		this.add(rp);
		this.add(this.ile);
		this.ile.repaint();
	}

	public void drawColonie(Joueur j, Sommet s) {
		ColoniePanel cp = new ColoniePanel(j, s);
		this.add(cp);
		this.add(this.ile);
		this.ile.repaint();
	}

	public void drawVille(Joueur j, Sommet s) {
		VillePanel vp = new VillePanel(j, s);
		this.add(vp);
		this.add(this.ile);
		this.ile.repaint();
	}

}
