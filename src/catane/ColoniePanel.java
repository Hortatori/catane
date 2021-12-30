package catane;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ColoniePanel extends JPanel {
    Joueur j;
    Sommet s;

    public ColoniePanel(Joueur j, Sommet s) {
        this.j = j;
        this.s = s;
        this.setBounds(0, 0, 700, 700);
        this.setOpaque(false);
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g); // n'enl�ve pas la gestion standard du dessin du composant

        g.setColor(this.j.getCouleur());
        int width;
        int height;
        int x = 130 + 100 * this.s.largeur;
        int y = 130 + 100 * this.s.hauteur;

        g.fillOval(x, y, 40, 40);

    }

}