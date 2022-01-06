/**Voleur contient les méthodes d'actions du voleur dans le jeu :
 * le déplacement du voleur, 
 * l'arrivée du voleur : les echanges avec les joueurs pour le choix des défausses de ressources
 * la fuite du voleur : l'échange avec le joueur leader pour changer le placement du voleur
 * le vol par le joueur leader d'un joueur sur la case d'arrivée du voleur si c'est possible
 */

package catane;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Voleur {
	private Case caseVoleur;
	private Partie partie;

	public Voleur(Partie p) {
		this.partie = p;
		VoleurPanel voleur = new VoleurPanel(p.plateau);
		for (Case[] ct : p.plateau.plateauC) {
			for (Case c : ct) {
				if (c.type == Paysage.DESERT) {
					c.setStatutVoleur(true);
					this.caseVoleur = c;
				}
			}

		}

	}

	public void setCase(Case c) {
		this.caseVoleur = c;
	}

	public Case getCase() {
		return this.caseVoleur;
	}

	public void deplaceVoleur(Case c) {
		this.caseVoleur.setStatutVoleur(false);
		c.setStatutVoleur(true);
		this.caseVoleur = c;
		if (this.partie.plateau.graphique) {
			this.partie.view.vp.voleur.moveVoleur(c);
			this.partie.view.vp.repaint();

		}

	}

	// si dé == 7 dans Partie /
	public void VoleurArrive(ArrayList<Joueur> joueurs, Joueur leader) {

		leader.partie.Communicate(
				"le voleur arrive! personne ne profite des ressources \ntous les joueurs qui ont plus de 6 ressources doivent defausser la moitie de leur main");
		for (Joueur joueur : joueurs) {
			if (joueur.getR().total() >= 7) {
				int aDefausser = joueur.getR().total() / 2;
				leader.partie.Communicate(
						joueur.getNom() + ", vous êtes trop riche, vous avez "
								+ joueur.getR().total() + " ressources",
						false);
				
				if (partie.plateau.graphique) {voleurGraphique(leader);}
				else {
				while (aDefausser > 0) {
					leader.partie.Communicate(
							"vous devez defausser " + aDefausser + " parmi vos ressources : " + joueur.toString());

					
						demanderRessource(joueur);
						aDefausser--;
					

					
						
//						joueur.vj.removeAll();
//						VueJoueur.RessourcePanel rp = joueur.vj.new RessourcePanel(
//								"Choisissez une ressource à défausser", this);
//						rp.addOptionListener( e -> {
//						if ( joueur.getR().prelevable(rp.paysage )) {partie.Communicate("vous défaussez :  "+ joueur.getR().paysagetoString( rp.paysage));
//						joueur.getR().incrementRessource(rp.paysage, -1) ;
//						}
//						
//						});
//						joueur.vj.add(rp);
//						aDefausser--;

					

				}
				}
			} else {
				leader.partie
						.Communicate(joueur.getNom() + " vous n'êtes pas assez riche pour que le voleur vous frappe");
			}

		}
		Voleurfuite(leader);
		for (Sommet s : this.caseVoleur.Sommets) {
			for (Joueur victime : this.partie.joueurs) {
				if (victime.colonies.contains(s)) {
					int ressource = new Random().nextInt(5);
					Paysage p = victime.getR().Convertir(ressource);
					if (victime.getR().prelevable(p)) {
						PayeRessource(ressource, victime);
						leader.getR().incrementRessource(p, 1);
					}
				}
			}
		}

	}

	public void voleurGraphique(Joueur j) {
		System.out.println("on tente ça");
		this.partie.view.vj.setVisible(false);
		for (Joueur joueur : this.partie.joueurs ) {
			
		
		if (joueur.getR().total() >7)
		{
		VueJoueur vvo = new VueJoueur(joueur, this);
		this.partie.view.add(vvo);
		this.partie.view.repaint();
		this.partie.Communicate("vue voleur lancée");
		
		
		}
		else {
			System.out.println ("lui est pas trop roche");
		}	
		}
		
		

		
	}

	void Voleurfuite(Joueur leader) {
		int[] coord;

		if (leader.isIa()) {
			coord = leader.matrix4.getCoord(true);
			VoleurPart(coord);
		} else {

			if (!partie.plateau.graphique) {
				coord = this.partie.ij.getCoord("Joueur " + leader.getNom()
						+ ", vous decidez quelle sera la prochaine place du Voleur! Donnez des coordonnées (entre 0 et 3)");
				VoleurPart(coord);
			} else {
				VueJoueur.FuiteVoleurPanel cp = leader.vj.new FuiteVoleurPanel("Déplacez le voleur où bon vous semble");
				leader.vj.add(cp);
				leader.vj.repaint();
				cp.addCoordListener(e -> {
					VueJoueur vj = leader.vj;
					int wid = vj.x;
					int hau = vj.y;
					int[] tab = { wid, hau };

					System.out.println(tab);
					VoleurPart(tab);
					cp.setVisible(false);
					if (vj.ap == null) {
						vj.add(vj.new ActionPanel());
					} else {
						vj.ap.setVisible(true);
						vj.repaint();
					}

				});
			}

		}
	}

	public void VoleurPart(int[] coord) {
		int x = coord[0];
		int y = coord[1];

		this.deplaceVoleur(partie.plateau.plateauC[y][x]);
	}

	public void demanderRessource(Joueur joueur) {
		int ressource;
		if (joueur.isIa()) {
			ressource = joueur.matrix4.rd.nextInt(5);
		} else {
			partie.Communicate(
					" Sélectionnez une ressource :\n 0 : Bois\n 1 : Pierre \n 2 : Argile \n 3 : Mouton \n 4 : Ble");
			Scanner sc = partie.ij.sc;
			ressource = sc.nextInt();
		}

		if (joueur.getR().prelevable(joueur.getR().Convertir(ressource))) {
			PayeRessource(ressource, joueur);
		} else {
			demanderRessource(joueur);
		}
	}

	private void PayeRessource(int ressource, Joueur joueur) {

		switch (ressource) {

			case 0:
				joueur.getR().payBois(1);
				break;

			case 1:
				joueur.getR().payPierre(1);
				break;

			case 2:
				joueur.getR().payArgile(1);
				break;

			case 3:
				joueur.getR().payMouton(1);
				break;

			case 4:
				joueur.getR().payBle(1);
				break;

			default:
				System.out.println("Payé");
				// demanderRessource(joueur);

		}

	}

}
