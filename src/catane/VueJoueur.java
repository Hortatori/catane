package catane;

import javax.swing.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import catane.VueAccueil.NameBox;
import catane.VueJoueur.RessourcePanel;

public class VueJoueur extends JPanel {

	Joueur joueur;
	int x;
	int xbuffer;
	int y;
	int ybuffer;
	static int init = 0;
	ActionPanel ap ;
	public Paysage paysage;
	public Paysage paysage2;

	VueJoueur() {
		super();
		this.setBackground(new Color(050, 000, 000));
		this.setBounds(700, 200, 300, 380);
		
		this.setLayout(null);

	}

	// il reste à gérer les tests de légalité de l opération, se servir des
	// fonctions deja la
	VueJoueur(Joueur j, VueAccueil va, NameBox nameBox) {

		this();
		this.joueur = j;
		j.vj = this;
		j.partie.view.ResetCommunicator();
		CoordPanel pa = new CoordPanel("Bienvenue " + j.getNom() + " ! Placez une première colonie gratuitement ! ");
		pa.addCoordListener(event -> {
			Sommet s = j.partie.plateau.plateauS[y + 1][x + 1]; /// problème de decalage d un cran
			j.partie.AttribuerRessourceInitiale(j, s);
			j.placerColonieInit(s);
			pa.drawColonie(x + 1, y + 1);

			pa.setVisible(false);

			// on crée la route

			RouteDPanel pb = new RouteDPanel(" On vous offre aussi une route. Donnez la coordonnée du départ ");

			pb.addCoordListener(event2 -> {

				VueJoueur.this.xbuffer = x;
				VueJoueur.this.ybuffer = y;
				int stox = x;
				int stoy = y;
				pb.setVisible(false);

				RouteAPanel pc = new RouteAPanel(" Donnez la coordonnée de l'arrivée ", stox, stoy);

				pc.addCoordListener(event3 -> {
					Plateau p = j.partie.plateau;
					VueJoueur.this.joueur.partie.view.Communicate(stox + "buffer" + stoy);
					Route r = j.placerRoute(p.plateauS[VueJoueur.this.ybuffer + 1][VueJoueur.this.xbuffer + 1],
							p.plateauS[y + 1][x + 1], p);
					j.partie.view.vp.drawRoute(j, r);
					pc.setVisible(false);

					// si succès
					nameBox.setVisible(true);
					incrementeInit();
					if (this.init == (j.partie.getN_joueur())) {
						nameBox.finAccueil();
						System.out.println("initialisation terminée");
					}
					va.setVisible(true);

				});
				VueJoueur.this.add(pc);
				VueJoueur.this.repaint();

			});
			VueJoueur.this.add(pb);
			VueJoueur.this.repaint();
		});
		this.add(pa);
		this.repaint();

	}

	private void incrementeInit() {
		this.init++;

	}

	VueJoueur(Joueur j, int de) {

		this();
		this.joueur = j;

		this.joueur.partie.view.ResetCommunicator();
		this.joueur.partie.view.Communicate("C'est le tour de " + this.joueur.getNom());

		this.joueur.partie.view.Communicate("Les dés ont donné " + de);

		

		ActionPanel ap = new ActionPanel();
		this.add(ap);
		this.ap = ap;
		this.repaint();
	}

	public class ActionPanel extends JPanel {

		Joueur j = VueJoueur.this.joueur;

		public ActionPanel() {
			super();
			VueJoueur.this.ap = this ;
			this.setSize(300, 400);
			this.setLayout(new GridLayout(8, 1));
			this.setOpaque(false);
			j.partie.view.Communicate("Choisissez une action ");

			JButton colonie = new JButton("Placer une nouvelle colonie");

			colonie.addActionListener(e -> {
				this.setVisible(false);
				j.partie.view.ResetCommunicator();
				j.partie.view.Communicate(j.getNom());
				CoordPanel p = new CoordPanel(
						"Cliquez sur le bouton correspondant à l 'endroit ou vous voulez installer une colonie");
				p.addCoordListener(event -> {
					j.placerColonieInit(j.partie.plateau.plateauS[y + 1][x + 1]);
					p.drawColonie(x + 1, y + 1);
					p.setVisible(false);
					this.setVisible(true);
					VueJoueur.this.repaint();
				});

				VueJoueur.this.add(p);
				repaint();
			});
			this.add(colonie);
			this.repaint();
//			

			JButton ville = new JButton("Placer une nouvelle ville");
			ville.addActionListener(e2 -> {
				this.setVisible(false);
				j.partie.view.ResetCommunicator();
				j.partie.view.Communicate(j.getNom());
				VillePanel panel = new VillePanel(
						"Cliquez sur le bouton correspondant à l 'endroit ou vous voulez installer une ville");
				panel.addCoordListener(event -> {
					j.placerVilleInit(j.partie.plateau.plateauS[y + 1][x + 1]);
					panel.drawVille(x + 1, y + 1);
					panel.setVisible(false);
					this.setVisible(true);
					VueJoueur.this.repaint();
				});
				VueJoueur.this.add(panel);
			});
			this.add(ville);

			JButton route = new JButton("Placer une nouvelle route");
			route.addActionListener(e -> {
				this.setVisible(false);
				RouteDPanel pb = new RouteDPanel(" Donnez la coordonnée du départ ");
				VueJoueur.this.repaint();
				VueJoueur.this.add(pb);
				pb.addCoordListener(event2 -> {

					VueJoueur.this.xbuffer = x;
					VueJoueur.this.ybuffer = y;
					int stox = x;
					int stoy = y;
					pb.setVisible(false);
					VueJoueur.this.repaint();

					RouteAPanel pc = new RouteAPanel(" Donnez la coordonnée de l'arrivée ", stox, stoy);
					VueJoueur.this.add(pc);
					pc.addCoordListener(event3 -> {
						Plateau p = j.partie.plateau;
						VueJoueur.this.joueur.partie.view.Communicate(stox + "buffer" + stoy);
						Route r = j.placerRoute(p.plateauS[VueJoueur.this.ybuffer + 1][VueJoueur.this.xbuffer + 1],
								p.plateauS[y + 1][x + 1], p);
						j.partie.view.vp.drawRoute(j, r);
						pc.setVisible(false);
						VueJoueur.this.ap.setVisible(true);
						VueJoueur.this.repaint();

					});
					/// fin listener imbriqué1
				});

				// fin du 2

			});

			this.add(route);

			JButton acheterCarte = new JButton("Acheter une carte développement");
//		if (( this.j.getR().getPierre() == 0) || ( this.j.getR().getMouton() == 0)||( this.j.getR().getBle() == 0))
//		{acheterCarte.setEnabled(false);}
			acheterCarte.addActionListener(e -> {
				this.j.getR().payBle(1);
				this.j.getR().payMouton(1);
				this.j.getR().payPierre(1);
				this.j.partie.view.ResetCommunicator();
				this.j.partie.view.Communicate("carte achetée avec succès");
				Carte c = this.j.partie.pioche.pioche.getFirst();
				joueur.cartes.add(c);
				c.setPossesseur(joueur);
				this.j.partie.pioche.pioche.removeFirst();

				;
			});
			this.add(acheterCarte);

			JButton utiliserCarte = new JButton("Utiliser une carte développement");
			utiliserCarte.addActionListener(e -> {
				CartePanel cp = new CartePanel("Quelle carte souhaitez - vous jouer ?");
				this.setVisible(false);

				VueJoueur.this.add(cp);

				cp.addOptionListener(event -> {
					cp.setVisible(false);
					//VueJoueur.this.removeAll();
					
					//VueJoueur.this.setVisible(true);
					utiliserCarte.setEnabled(false); // on ne peut jouer qu'une carte par tour
					VueJoueur.this.repaint();
				});

			});
			this.add(utiliserCarte);

			JButton commerce = new JButton("Faire du commerce");
			commerce.addActionListener(e -> {
				//CommercePanel cp = new CommercePanel();
				this.setVisible(false);
				VueJoueur.this.remove(this);
				OperationCommerciale op = new OperationCommerciale(joueur);
				op.effectuer();
				VueJoueur.this.repaint();
				//VueJoueur.this.add(cp);
			});
			this.add(commerce);

			JButton finir = new JButton("Finir votre tour");
			finir.addActionListener(e -> {
				this.setVisible(false);

				VueJoueur.this.setVisible(false);

				// lancer le tour suivant
				Joueur next = j.nextJoueur();
				Joueur next2 = next.nextJoueur();

				j.partie.Tour(j.nextJoueur());
				j.partie.plateau.afficherPlateau();
				System.out.println(j.getNom());
				System.out.println(next.getNom());
				System.out.println(next2.getNom());

			});
			this.add(finir);
		}

	}

////////////////////////////////////FIN ACTION PANEL ///////////////////////////////////////////////////////////

	
	public class FuiteVoleurPanel extends CoordPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FuiteVoleurPanel(String question) {
		super(question);
		
	}
		
	void setGrille() {
		JPanel grille = new JPanel();
		grille.setLayout(new GridLayout(4, 4));

		
		for (int h = 0; h < 4; h++) {
			for (int w = 0; w < 4; w++) {

				String nom = "[" + w + ";" + h + "]";
				RadioCoord radio = new RadioCoord(nom, h, w);
					int storex = w;
					int storey = h ;
				radio.addActionListener(e -> {
					VueJoueur.this.x = storex;
					VueJoueur.this.y = storey ;
				
					ChangeEvent event = new ChangeEvent(this);
					listener.stateChanged(event);
					
					
				});
				
				
				grille.add(radio);

			}
		}

		this.add(grille);
		

	}

	
		
		
	}
///////////////////////////////////DEBUT CHOIX PANEL////////////////////////////////////////////////////////////

	public class CoordPanel extends JPanel {

		ChangeListener listener;

		CoordPanel(String question, boolean nogrid) {

			super();
			this.setBackground(new Color(050, 000, 000));
			this.setSize(300, 400);
			this.setLayout(new FlowLayout());
			VueJoueur.this.joueur.partie.view.Communicate(question);
			this.setOpaque(false);

		}

		CoordPanel(String question) {

			super();

			this.setSize(300, 400);
			this.setLayout(new FlowLayout());
			VueJoueur.this.joueur.partie.view.Communicate(question);
			this.setGrille();
			this.repaint();
		}

		void setGrille() {
			JPanel grille = new JPanel();
			grille.setLayout(new GridLayout(5, 5));

			int nbbuttondesactive = 0;
			for (int h = 0; h < 5; h++) {
				for (int w = 0; w < 5; w++) {

					String nom = "[" + w + ";" + h + "]";
					RadioCoord radio = new RadioCoord(nom, h, w);
					int X = w;
					int Y = h;
					radio.addActionListener(e -> {
						VueJoueur.this.x = X;
						VueJoueur.this.y = Y;

						ChangeEvent event = new ChangeEvent(this);
						listener.stateChanged(event);
						System.out.println(X + " " + Y);
					});

					boolean desactiver = Activer(h, w);
					if (desactiver) {

						radio.setEnabled(false);
						nbbuttondesactive++;

					}
					grille.add(radio);

				}
			}

			this.add(grille);
			if (nbbuttondesactive == 25) {
				this.setVisible(false);
				VueJoueur.this.setVisible(true);
				VueJoueur.this.ap.setVisible(true);
			}

		}

		boolean Activer(int w, int h) {
			w++;
			h++;
			System.out.println("activer de C");
			Plateau plateau = VueJoueur.this.joueur.partie.plateau;
			boolean b = VueJoueur.this.joueur.partie.plateau.plateauS[w][h].colonie; // pb indice +1
			if (w > 0) {
				if (plateau.plateauS[w - 1][h].colonie) {
					System.out.println("Conditions non respectées ");
					System.out.println("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
					b = true;
				}
			}
			if (h > 0) {
				if ((plateau.plateauS[w][h - 1].colonie)) {
					System.out.println("Conditions non respectées ");
					System.out.println("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
					b = true;
				}
			}

			if ((plateau.plateauS[w + 1][h].colonie) || (plateau.plateauS[w][h + 1].colonie)) {
				System.out.println("Conditions non respectées ");
				System.out.println("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
				b = true;

			}
			plateau.afficherPlateau();
			if (b == false) {
				System.out.println(w + "  " + h);
			}

			return b;

		}

		public void drawVille(int x, int y) {
			Plateau p = joueur.partie.plateau;
			Sommet s = p.plateauS[y][x];
			if ((joueur.colonies.contains(s)) || (s.ville == false)) {
				p.vp.drawVille(joueur, p.plateauS[y][x]);
			} else {
				joueur.partie.view.ResetCommunicator();
				joueur.partie.view.Communicate("Vous ne pouvez pas construire de colonie ici");
			}
		}

//	
		public void addCoordListener(ChangeListener cl) {
			this.listener = cl;
		}

		public void drawColonie(int x, int y) {
			Plateau p = joueur.partie.plateau;
			Sommet s = p.plateauS[y][x];
			if (s.colonie) {
				p.vp.drawColonie(joueur, s);
			} else {
				joueur.partie.view.ResetCommunicator();
				joueur.partie.view.Communicate("Vous ne pouvez pas construire de colonie ici");
			}

		}

	}

///////////////////////////////////FIN COORDPANEL ////////////////////////////////////////////////////////////////

///////////////////////////////////DEBUT OPTIONSPANEL/////////////////////////////////////////////////////////////

	public abstract class OptionPanel extends JPanel {

		ChangeListener listener;
		Joueur j = VueJoueur.this.joueur;
		Iterable liste;

		OptionPanel(String question) {
			super();
			this.setSize(300, 400);
			this.setBackground( new Color (200,200, 250 ));
			this.j.partie.Communicate(question, true);

		}

		public void form() {
			int rang = 0;
			int len = ((ArrayList<?>) this.liste).size() +1;
			this.setLayout(new GridLayout(len, 1));
			for (Object o : this.liste) {
				JButton b = new JButton(o.toString());
				int ruse = rang;
				// sans cette variable, rang n'était pas effectively final et ne permettait pas
				// de récupérer l'indice dans le tableau de l'élément recherché
				b.addActionListener(e -> {
					ChangeEvent event = new ChangeEvent(this);
					listener.stateChanged(event);

					OptionPanelAction(ruse);

				});
				this.add(b);
				rang++;
			}
			

		}

		protected abstract void OptionPanelAction(int rang);

		public void addOptionListener(ChangeListener cl) {
			this.listener = cl;
		}

	}

////////////////////////////////def des deux sous classes ///////////////////////////////////
	public class RessourcePanel extends OptionPanel {

		Paysage paysage;
		CarteMonopole cm;
		Voleur vo;

		public RessourcePanel(String s) {
			super(s);
			
			ArrayList<Paysage> ressources = new ArrayList<Paysage>();
			for (Paysage r : Paysage.values()) {
				if ( r != Paysage.DESERT ) {
				ressources.add(r);}
			}
			this.liste = ressources;
			form();
		}

		public RessourcePanel(String s, CarteMonopole cm) {
			this(s);
			this.cm = cm;
		}

		public RessourcePanel(String s, Voleur vo) {
			this(s);
			this.cm = cm;
		}

		@Override
		public void form() {
			int rang = 0;
			int len = ((ArrayList<Carte>) this.liste).size() +1;
			this.setLayout (new GridLayout (len, 1)) ;
			for (Object o : this.liste) {
				Paysage p = ((Paysage) o);
				String nom = j.getR().paysagetoString(p);
				JButton b = new JButton(nom);

				// sans cette variable, rang n'était pas effectively final et ne permettait pas
				// de récupérer l'indice dans le tableau de l'élément recherché
				b.addActionListener(e -> {
					this.paysage = p;
					ChangeEvent event = new ChangeEvent(this);
					listener.stateChanged(event);
					this.paysage = p;
					this.setVisible(false);
					if (VueJoueur.this.ap != null ) {
					VueJoueur.this.ap.setVisible(true);}
					else {VueJoueur.this. add ( VueJoueur.this.new ActionPanel()) ;
						
					}
//					if (this.cm != null) {
//						cm.Piller(p, joueur);
//					}
					

				});
				if (this.vo != null) {
					if (!joueur.getR().prelevable(p)) 
					{
					b.setEnabled(false);
					}
				}
				this.add(b);
				rang++;
			}
	JButton retour = new JButton ("annuler l'action") ;
	retour.addActionListener ( e -> { 
		
		this.paysage = Paysage.DESERT ;
		ChangeEvent event = new ChangeEvent(this);
		
		listener.stateChanged(event);
		this.setVisible(false);
		VueJoueur.this.ap.setVisible(true);
		
	});
	if (this.vo != null) { //on force le joueur à se défausser 
	this.add(retour); }

		}

		protected void OptionPanelAction(int rang) {
			// TODO Auto-generated method stub

		}

	}

	public class CartePanel extends OptionPanel {

		public CartePanel(String s) {
			super(s);
			this.liste = this.j.cartes;
			form();
		}

		@Override
		public void form() {
			
			int rang = 0;
			int len = ((ArrayList<Carte>) this.liste).size() +1;
			this.setLayout (new GridLayout (len, 1)) ;
			for (Object o : this.liste) {
				JButton b = new JButton(o.toString());
				Carte c = (Carte) o;
				int rangruse = rang ;
				// sans cette variable, rang n'était pas effectively final et ne permettait pas
				// de récupérer l'indice dans le tableau de l'élément recherché
					b.addActionListener(e -> {
					ChangeEvent event = new ChangeEvent(this);
					listener.stateChanged(event);
					
					OptionPanelAction(rangruse);
					this.setVisible(false);
					//VueJoueur.this.ap.setVisible(true);

				});
				this.add(b);
				this.repaint();
				rang++;
			}

			JButton retour = new JButton("annuler l'action");
			retour.addActionListener(e -> {
				ChangeEvent event = new ChangeEvent(this);
				listener.stateChanged(event);
				this.setVisible(false);
				VueJoueur.this.ap.setVisible(true);

			});
			this.add(retour);

		}

		@Override
		protected void OptionPanelAction(int rang) {
			Carte carte = j.cartes.get(rang);
			if (carte instanceof CarteVictoire || carte instanceof CarteInvention ) {VueJoueur.this.ap.setVisible(true);}
			else {VueJoueur.this.ap.setVisible(false);}
			carte.actionCarte();
		}

	}

////////////////////////////Commerce Panel //////////////////////////////////

// on va tenter d'avoir un panel qui indisue toutes les informations

	public class CommercePanel extends JPanel {
		ChangeListener listener;
		Paysage paysage = Paysage.COLLINE;
		Paysage paysage2 = Paysage.COLLINE;

		public CommercePanel() {
			this.setSize(300, 400);
			this.setLayout(new FlowLayout());

			JButton eff = new JButton("Tenter la transaction");
			eff.addChangeListener(e -> {
				OperationCommerciale oc = new OperationCommerciale(VueJoueur.this.joueur);
				boolean succes = oc.effectuer();
				if (succes) {
					this.setVisible(false);
					VueJoueur.this.joueur.vj.ap.setVisible(true);
				} else {
					VueJoueur.this.joueur.partie.view.ResetCommunicator();
					VueJoueur.this.joueur.partie.view.Communicate("La transaction était illégale, recommencez");
				}
			});
			JButton fin = new JButton("Annuler l'action");
			eff.addChangeListener(e -> {
				this.setVisible(false);
				VueJoueur.this.joueur.vj.ap.setVisible(true);
			});

		}

//	public void addCommerceListener(ChangeListener cl) {
//		this.listener = cl ;
//	}

	}

	public class RouteAPanel extends CoordPanel {

		int xs;
		int ys;
// les coordonneés du point de départ ;

		RouteAPanel(String q, int xbuffer, int ybuffer) {

			super(q, false);
			// ruse

			this.xs = xbuffer;
			this.ys = ybuffer;
			this.setGrille();
			System.out.println(x + y);
			System.out.println("caribour");
			// TODO Auto-generated constructor stub
		}

		boolean Activer(int h, int w) {
			// System.out.println("activer de RA") ;
			VueJoueur.this.joueur.partie.plateau.afficherPlateau();
			Sommet s = VueJoueur.this.joueur.partie.plateau.plateauS[ys + 1][xs + 1];
			Sommet s2 = VueJoueur.this.joueur.partie.plateau.plateauS[h + 1][w + 1];
			for (Route r : VueJoueur.this.joueur.partie.plateau.routes) {
				if (r.depart.equals(s)) {
					if (r.arrivee.equals(s2)) {
						return true;
					}
				}
				if (r.depart.equals(s2)) {
					if (r.arrivee.equals(s)) {
						return true;
					}
				}

			}

			boolean b = true;
			System.out.println("bornes" + x + y + " versus " + h + w);
			if (h == ys) {
				if ((w == xs + 1) || (w == xs - 1)) {
					b = false;
				}
			}

			if (w == xs) {
				if ((h == ys + 1) || (h == ys - 1)) {
					b = false;
				}

				System.out.println(b);
			}

			return b;
		}

	}

	public class RouteDPanel extends CoordPanel {

		RouteDPanel(String q) {
			super(q);
			// TODO Auto-generated constructor stub
		}

		boolean Activer(int h, int w) {
			Joueur joueur = VueJoueur.this.joueur;
			System.out.println("activer de RD");
			Sommet s = joueur.partie.plateau.plateauS[h + 1][w + 1];

			boolean b = true;

			if ((joueur.colonies.contains(s))) {
				b = false;
			}
			for (Route r : joueur.routes) {
				if ((r.depart == s) || (r.arrivee == s)) {
					b = false;
				}

			}

			return b;
		}
	}

	public class VillePanel extends CoordPanel {

		VillePanel(String q) {
			super(q);
			// TODO Auto-generated constructor stub
		}

		boolean Activer(int h, int w) {
			// System.out.println("activer de V") ;

			for (Sommet s : VueJoueur.this.joueur.colonies) {

				if ((s.hauteur == h) && (s.largeur == w)) {
					if (s.ville) {
						return true;
					}
					return false;
				}
				System.out.println(s);
				System.out.println(s.hauteur + "   " + s.largeur);
				VueJoueur.this.joueur.partie.plateau.afficherPlateau();
			}

			return true;
		}
	}

VueJoueur (Joueur j , Voleur v) {
	
	
	super();
	this.joueur = j ;
	this.setBackground(new Color(050, 000, 000));
	this.setBounds(700, 200, 300, 380);
	
	this.setLayout(null);
	int nressource = j.getR().total() ;
	int ndef = j.getR().total()- nressource /2  ;
	for ( int i = 0 ; i< ndef ; i++ ) {
		RessourcePanel rp = this.new RessourcePanel(
				"Choisissez une ressource à défausser", v);
		rp.addOptionListener( e -> {
		if ( j.getR().prelevable(rp.paysage )) {j.partie.Communicate("vous défaussez :  "+ j.getR().paysagetoString( rp.paysage));
		j.getR().incrementRessource(rp.paysage, -1) ;
		}
		if (j.getR().total()  > nressource /2) {
			VueJoueur.this.setVisible(false);
			
		}
		boolean flag = true; 
		for (Joueur joueur2 : j.partie.joueurs ) {
			if (joueur2.getR().total()>7 ) {flag = false ; }
		}
		if (flag) {j.partie.view.vj.setVisible(true);
		}
		
		
		});
		this.add(rp);
		this.repaint();
	}
	
}
// probleme d affichage avec les layout, finir par régler ça avec un null layout et ça sera plus simple...

}
