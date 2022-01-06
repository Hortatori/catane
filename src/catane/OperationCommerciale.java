/**contient les contrôle des contraintes de ressources pour chaque échange,
 * les taux des échanges et toutes les méthodes de commerce
 */

package catane;

import java.awt.Color;
import java.util.Scanner;

public class OperationCommerciale {
	boolean graphique;
	int taux = 4;
	Joueur j;

	public OperationCommerciale(Joueur j) {

		this.j = j;
		graphique = this.j.partie.plateau.graphique;
		{
			if (j.ports.size() > 0) {
				// on n'abaisse le taux de base à 3 seulement s'il y a un port général
				for (Port p : j.ports) {
					if (p instanceof PortSpecial) {
						taux = 3;

					}

				}
			}
		}
	}

	public boolean effectuer() {

		System.out.println("Sélectionnez d'abord la ressource que vous voulez obtenir ");
		if (graphique) {
			CommerceGraphique();
			return false;
		}

		Paysage p = selectP();
		if (p == null) {
			return false;
		}

		j.partie.Communicate("Le taux de base est de " + taux + " contre 1 .");
		for (Port port : j.ports) {
			if (port instanceof PortSpecial) {
				System.out.println(" Grâce au port, vous bénéficiez d'un taux de 2 contre 1 en échangeant du "
						+ ((PortSpecial) port).Ressource.name());
			}
		}

		j.partie.Communicate("Et ensuite celle que vous voulez céder");
		Paysage p2;

		if (j.isIa()) {
			p2 = j.matrix4.demandeRessource();
		} else {

			p2 = demanderRessource();

		}
		for (Port port : j.ports) {
			if (port instanceof PortSpecial) {
				if (((PortSpecial) port).Ressource == p2) {
					this.taux = 2;
				}

			}

		}
		boolean legal = Echancelegal(p2);

		// et si c est légal on verse la ressource achetée

		if (legal) {
			j.partie.Communicate("la transaction peut avoir lieu");

			Verser(p);
		} else {
			j.partie.Communicate("transaction illégale");
		}

		return legal;

		// return false;

	}

	private Paysage selectP() {

		if (j.isIa()) {
			return j.matrix4.demandeRessource();
		} else {
			if (!graphique) {
				return demanderRessource();
			} else {
				CommerceGraphique();
				return null;
			}
		}
	}

	public void CommerceGraphique() {

		VueJoueur vj = this.j.vj;
		VueJoueur.RessourcePanel rp = vj.new RessourcePanel("Quelle ressource voulez- vous acquérrir ? ");
		rp.addOptionListener(e ->

		{
			rp.setVisible(false);

			VueJoueur.RessourcePanel rp1 = vj.new RessourcePanel(
					"Quelle ressource voulez- vous échanger contre celle là ? ");
			rp1.addOptionListener(e2 -> {
				rp1.setVisible(false);
				Paysage p1 = rp.paysage;
				Paysage p2 = rp.paysage;
				boolean legal = Echancelegal(p2);

				// et si c est légal on verse la ressource achetée

				if (legal) {
					j.partie.Communicate("la transaction peut avoir lieu");

					Verser(p1);
				} else {
					j.partie.Communicate("transaction illégale");
				}
				if (vj.ap == null) {
					VueJoueur.ActionPanel ap = vj.new ActionPanel();
					vj.add(ap);
					vj.ap = ap;
				}

				vj.ap.setVisible(true);

			});
			vj.add(rp1);

		});
		vj.add(rp);

	}

	private void Verser(Paysage p) {
		switch (p) {

			case MONTAGNE:

				j.getR().addPierre(1);

			case PRE:

				j.getR().addMouton(1);

			case FORET:

				j.getR().addBois(1);

			case COLLINE:

				j.getR().addArgile(1);

			case CHAMP:
				j.getR().addBle(1);

			default:
				return;

		}

	}

	private boolean Echancelegal(Paysage p2) {
		boolean legal = false;
		// conversion paysage ressource
		switch (p2) {

			case MONTAGNE:
				if (j.getR().getPierre() >= taux) {

					j.getR().payPierre(taux);
					legal = true;
				}

			case PRE:
				if (j.getR().getMouton() >= taux) {

					j.getR().payMouton(taux);
					legal = true;
				}

			case FORET:
				if (j.getR().getBois() >= taux) {

					j.getR().payBois(taux);
					legal = true;
				}

			case COLLINE:
				if (j.getR().getArgile() >= taux) {

					j.getR().payArgile(taux);
					legal = true;
				}

			case CHAMP:
				if (j.getR().getBle() >= taux) {

					j.getR().payBle(taux);
					legal = true;
				}

			default:
				break;

		}
		return false;
	}

	public Paysage demanderRessource() {
		System.out.println(
				" Sélectionnez une ressource :\n 1 : Bois\n 2 : Mouton \n 3 : Pierre \n 4 : Champ \n 5 : Argile");
		Scanner sc = this.j.partie.ij.sc;
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