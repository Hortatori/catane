package catane;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;

public class VueRessources extends JPanel {
	Joueur j;

	public VueRessources(Joueur j) {
		super();
		this.j = j;
		this.setBounds(700, 580, 300, 100);
		this.setOpaque(true);
		this.setBackground(new Color(200, 200, 250));
		this.setLayout(new GridLayout(2, 5));

		this.add(new JLabel("Argile"));
		this.add(new JLabel("Bois"));
		this.add(new JLabel("Ble"));
		this.add(new JLabel("Pierre"));
		this.add(new JLabel("Mouton"));
		this.add(new JLabel(String.valueOf(this.j.getR().getArgile())));
		this.add(new JLabel(String.valueOf(this.j.getR().getBois())));
		this.add(new JLabel(String.valueOf(this.j.getR().getBle())));
		this.add(new JLabel(String.valueOf(this.j.getR().getPierre())));
		this.add(new JLabel(String.valueOf(this.j.getR().getMouton())));
		this.setOpaque(true);

		this.setAlignmentX(SwingConstants.CENTER);
		this.setAlignmentY(SwingConstants.CENTER);

		for (Component lab : this.getComponents()) {
			((JLabel) lab).setHorizontalAlignment(SwingConstants.CENTER);
			((JLabel) lab).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

	}

}
