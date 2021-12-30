package catane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class VueJoueur extends JPanel {

    VueJoueur() {
        super();
        this.setBackground(new Color(050, 000, 000));
        this.setBounds(700, 0, 300, 700);
        this.setLayout(new GridLayout(3, 1));

        JPanel tour = new JPanel();

        tour.add(new JLabel("tour"));

        tour.setOpaque(false);
        tour.setMaximumSize(new Dimension(400, 100));
        this.add(tour);
        this.add(new JButton("�chec"));

    }

    VueJoueur(Joueur j) {
        this();
        VueRessources r = new VueRessources(j);
        // JButton r = new JButton(j.getNom());
        this.add(r);
    }

}