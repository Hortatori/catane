package catane;

import java.awt.Color;
import java.util.Scanner;
import catane.Paysage;

public class OperationCommerciale {
	boolean legal = true;
	int taux = 4;
	Joueur j;
	{
		if (j.ports.size() > 0) {
			taux = 3;
		}
	}

	public OperationCommerciale(Joueur j) {

		this.j = j;
	}

	public void effectuer(boolean graphique) {
		// ruse pour �viter de demander un acc�s au plateau.
		if (!graphique) {
			System.out.println("Quelle ressource voulez vous obtenir ?");
			Paysage p = demanderRessource();
			System.out.println("Le taux de base est de " + taux + " contre 1 .");
			for (Port port : j.ports) {
				if (port instanceof PortSpecial) {
					System.out.println(" Gr�ce au port, vous b�n�ficiez d'un taux de 2 contre 1 en �changeant du "
							+ ((PortSpecial) port).Ressource.name());
				}
			}

			System.out.println("Quelle ressource voulez vous obtenir ?");
			Paysage p2 = demanderRessource();
			for (Port port : j.ports) {
				if (port instanceof PortSpecial) {
					if (((PortSpecial) port).Ressource == p2) {
						this.taux = 2;
					}

				}
			}
			boolean legal = false;
			// conversion paysage ressource
			switch (p) {

				case MONTAGNE:
					if (j.getInventaire().getPierre() >= taux) {

						j.getInventaire().payPierre(taux);
						break;
					}

				case PRE:
					if (j.getInventaire().getMouton() >= taux) {

						j.getInventaire().payMouton(taux);
						break;

					}

				case FORET:
					if (j.getInventaire().getBois() >= taux) {

						j.getInventaire().payBois(taux);
						break;

					}
				case COLLINE:
					if (j.getInventaire().getArgile() >= taux) {

						j.getInventaire().payArgile(taux);
						break;

					}

				case CHAMP:
					if (j.getInventaire().getBle() >= taux) {

						j.getInventaire().payBle(taux);
						break;

					}

				default:
					return;
			}

			// et si c est l�gal on verse la ressource achet�e

			if (legal) {
				switch (p) {

					case MONTAGNE:

						j.getInventaire().addPierre(1);

					case PRE:

						j.getInventaire().addMouton(1);

					case FORET:

						j.getInventaire().addBois(1);

					case COLLINE:

						j.getInventaire().addArgile(1);

					case CHAMP:
						j.getInventaire().addBle(1);

					default:
						return;

				}
			} else {
				System.out.println("transaction ill�gale");
			}

		}
	}

	public Paysage demanderRessource() {
		System.out.println(
				" S�lectionnez une ressource :\n 1 : Bois\n 2 : Laine \n 3 : Fer \n 4 : Ble \n 5 : Argile");
		Scanner sc = new Scanner(System.in);
		int scan = sc.nextInt();
		switch (scan) {
			case 1:
				return Paysage.FORET;

			case 2:
				return Paysage.PRE;

			case 3:
				return Paysage.MONTAGNE;

			case 4:
				return Paysage.CHAMP;

			case 5:
				return Paysage.COLLINE;

			default:
				System.out.println("vous devez donner un entier entre 1 et 5");
				demanderRessource();

		}
		return null;

	}

}