package catane;

import javax.swing.JButton;

public class RadioCoord extends JButton {
String nom;
int h;
int w;
public RadioCoord(String nom, int h, int w) {
	super(nom);
	this.h = h;
	this.w = w;
}


int[] getCoord() {
	int[] tab  = {w , h} ;
	return  tab ;
}
}
