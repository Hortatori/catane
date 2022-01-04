package catane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import java.awt.GridLayout ;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.GridBagLayout ;
import java.awt.GridBagConstraints ;
import java.awt.* ;
import javax.swing.JLabel ;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.FlowLayout ;
import catane.VueAccueil.NameBox;

public class VueJoueur extends JPanel {
	
Joueur joueur ;
int x ;
int xbuffer ;
int y ;
int ybuffer ;
static int init = 0 ;
ActionPanel ap;
public Paysage paysage;
public Paysage paysage2;



	VueJoueur() {
		super();
		this.setBackground(new Color(050, 000, 000));
		this.setBounds(700,200, 300, 500);
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new FlowLayout());
//		GridBagConstraints c = new GridBagConstraints();
//		c.gridx = 0;
//		c.gridy = 0;
//		c.anchor = GridBagConstraints.NORTH ;
//		//c.weighty = 0.2 ;
//		JPanel tour = new JPanel ();
//	
//		tour.add(new JLabel ("TOUR "));
//		
//		tour.setOpaque(false);
//		//tour.setMaximumSize(new Dimension(400, 100));
//		this.add(tour, c);
		
		}
	// il reste à gérer les tests de légalité de l opération, se servir des fonctions deja la
	VueJoueur(Joueur j, VueAccueil va, NameBox nameBox){
		
		this() ;
		this.joueur = j ;
		j.vj = this;
		j.partie.view.ResetCommunicator();
		CoordPanel pa = new CoordPanel("Bienvenue "+ j.getNom()+" ! Placez une première colonie gratuitement ! ",  true) ;
		pa.addCoordListener( event ->  {
			 j.placerColonieInit(j.partie.plateau.plateauS[y][x]);
			pa. drawColonie(x,y) ;
			 
			 pa.setVisible(false) ;
			 CoordPanel pb = new CoordPanel(" On vous offre aussi une route. Donnez la coordonnée du départ ") ;
			 pb.addCoordListener( event2 ->  { 
				
				 VueJoueur.this.xbuffer = x ; 
				 VueJoueur.this.ybuffer = y ; 
				 pb.setVisible(false);
				 
				 CoordPanel pc = new CoordPanel(" Donnez la coordonnée de l'arrivée ") ;
					
				 pc.addCoordListener( event3 ->  { 
					 Plateau p = j.partie.plateau ;
					 Route r = j.placerRoute(p.plateauS[VueJoueur.this.ybuffer][VueJoueur.this.xbuffer]  , p.plateauS[y][x], p);
					 j.partie.view.vp.drawRoute(j, r);
					 pc.setVisible(false);
					 
					 
					 // si succès
					 nameBox.setVisible(true);
					 incrementeInit();
					 if (this.init == (j.partie.getN_joueur() ) ) {
						 nameBox.finAccueil();
						 System.out.println("initialisation terminée");
					 }
					 va.setVisible(true);
					 
					
					 
					 
					 
					 
					 } );
				 VueJoueur.this.add(pc);
			 
			 } );
			 VueJoueur.this.add(pb);
		 });  
		this.add(pa);
		
	
		
		// problème, j'ai besoin ici de la coordonnée renvoyée par le coord Panel, mais je ne vois pas comment dire à l'ordinateur d'attendre 
		// qu'on me renvoie la coordonnée avant de passer à la suite 
//		int[] result = pa.getResult();
//		Sommet s = j.partie.plateau.plateauS[result[0]][result[1]] ;
//		j.placerColonieInit(s);
//		j.partie.view.drawColonie(j, result[0], result[1]);
//		
		
		
		
	}
	
	private void incrementeInit() {
		this.init ++ ;
		
	}
	VueJoueur(Joueur j, int de) {
		
		this();
		this.joueur = j ;
		
		
		this.joueur.partie.view.ResetCommunicator();
		this.joueur.partie.view.Communicate("C'est le tour de "+ this.joueur.getNom());
		
		this.joueur.partie.view.Communicate("Les dés ont donné " + de);
		

		VueRessources r = new VueRessources(j);
		this.add(r);
		
		ActionPanel ap = new ActionPanel()  ;
		this.add(ap);
		this.ap = ap ;
		}
		
		
		
	
	
	
	
public class ActionPanel extends JPanel {
	
Joueur j = VueJoueur.this.joueur ;

	public ActionPanel() {
		super();
		this.setSize(300, 400) ;
		this.setLayout ( new GridLayout (8, 1)) ;
		this.setOpaque(false);
		j.partie.view.Communicate("Choisissez une action ");
		
		
		JButton colonie = new JButton("Placer une nouvelle colonie") ;
		
		colonie . addActionListener( e -> { this.setVisible(false) ;
										j.partie.view.ResetCommunicator();
										j.partie.view.Communicate(j.getNom());
		 								CoordPanel p= new CoordPanel("Cliquez sur le bouton correspondant à l 'endroit ou vous voulez installer une colonie" , true);
		 								p.addCoordListener( event -> {
		 								j.placerColonieInit(j.partie.plateau.plateauS[y][x]);
		 								p.drawColonie(x,y) ;
		 								p.setVisible(false);
		 								this.setVisible(true) ;  }) ;
			
		 	VueJoueur.this.add (p);
		 	repaint();
			});
		 	this.add(colonie);
//			
		
		
	
		JButton ville = new JButton("Placer une nouvelle villee") ;
		ville . addActionListener( e2 -> { 
				this.setVisible(false) ;
				j.partie.view.ResetCommunicator();
				j.partie.view.Communicate(j.getNom());
				CoordPanel panel= new CoordPanel("Cliquez sur le bouton correspondant à l 'endroit ou vous voulez installer une ville" );
				panel.addCoordListener( event -> {
					j.placerColonieInit(j.partie.plateau.plateauS[y][x]);
					panel.drawVille(x,y) ;
					panel.setVisible(false);
					this.setVisible(true) ;  }) ;
				VueJoueur.this.add (panel); 
		});
		this.add(ville);
		
		JButton route = new JButton("Placer une nouvelle route") ;
		// colonie . addActionListener( e -> {    ;} ) ;
		this.add(route);
		
		
		JButton acheterCarte = new JButton("Acheter une carte développement") ;
		if (( this.j.getR().getPierre() == 0) || ( this.j.getR().getMouton() == 0)||( this.j.getR().getBle() == 0))
		{acheterCarte.setEnabled(false);}
		acheterCarte . addActionListener( e -> {  this.j.getR().payBle(1)  ;
		this.j.getR().payMouton(1)  ;
		this.j.getR().payPierre(1)  ;	
		this.j.partie.view.ResetCommunicator();
		this.j.partie.view.Communicate("carte achetée avec succès") ;
		Carte c = this.j.partie.pioche.pioche.getFirst() ;
    	joueur.cartes.add(c);
        c.setPossesseur(joueur);
    	this.j.partie.pioche.pioche.removeFirst() ; 
    	// on désactive le bouton pour empêcher tout achat frauduleux
    	if (( this.j.getR().getPierre() == 0) || ( this.j.getR().getMouton() == 0)||( this.j.getR().getBle() == 0))
		{acheterCarte.setEnabled(false);}
    	
			;} ) ;
		this.add(acheterCarte);
		
		JButton utiliserCarte = new JButton("Utiliser une carte développement") ;
		utiliserCarte.addActionListener( e ->  {
			CartePanel cp = new CartePanel ("Quelle carte souhaitez - vous jouer ?");
			cp.addOptionListener( event ->  
			{ cp.setVisible(false);
			VueJoueur.this.setVisible(true);
			utiliserCarte.setEnabled(false); // on ne peut jouer qu'une carte par tour 
			}
					);
			
			
			
			
			
			VueJoueur.this.add(cp);
			
				
				
			});
		this.add(utiliserCarte);
		
		
		
		
		
		JButton commerce = new JButton("Faire du commerce") ;
		commerce.addActionListener( e ->  {
			CommercePanel cp = new CommercePanel ();
			this.setVisible(false);
			VueJoueur.this.add(cp);
			});
		this.add(commerce);
		
		
		JButton finir = new JButton("Finir votre tour") ;
		finir.addActionListener( e ->  {
			this.setVisible(false);
			
			VueJoueur.this.setVisible(false);
			// lancer le tour suivant
			int de = j.partie.LanceDe();
			j.partie.view.updateJoueur(j.nextJoueur(), de);
			j.partie.plateau.afficherPlateau() ;
			
			});
		this.add(finir);
	}
		
		
		
		
		
		
		
		
		
	

}

////////////////////////////////////FIN ACTION PANEL ///////////////////////////////////////////////////////////



///////////////////////////////////DEBUT CHOIX PANEL////////////////////////////////////////////////////////////



public class CoordPanel extends JPanel {	
	
	ChangeListener listener ;
	boolean desactiverColonies = false ;
	
	
	CoordPanel(String question) {
	
	
		super() ;
		this.setSize(300 , 400);
		this.setLayout(new FlowLayout()) ;
		VueJoueur.this.joueur.partie.view.Communicate (question) ;
		JPanel grille = new JPanel();
		grille.setLayout(new GridLayout(5,5));
		
		
		
		
		for (int h = 0 ; h<5; h++ ) {
			for (int w = 0 ; w<5; w++ ) {
				
				String nom = "[" + w+ ";"+ h +"]" ;
				RadioCoord radio = new RadioCoord(nom, h, w) ;
				int X = w ;
				int Y = h;
				radio.addActionListener( e ->  {
				VueJoueur.this.x = X;
				VueJoueur. this.y = Y;
				
				ChangeEvent event = new ChangeEvent(this);
				listener.stateChanged(event);
				System.out.println (X + " "+ Y) ;
				}
				);
				if (desactiverColonies ) {
					
					if (VueJoueur.this.joueur.partie.plateau.plateauS[h][w].colonie ) {
						radio.setEnabled(false);
					}
				}
				grille.add(radio);
				
			}
		}
		this.add(grille);
	}
	
	public void drawVille(int x, int y) {
		Plateau p = joueur.partie.plateau ;
		Sommet s = p.plateauS[y][x] ;
		if ((joueur.colonies.contains(s))||(s.ville == false)) {
		p.vp.drawVille(joueur, p.plateauS[y][x]);}
		else {joueur.partie.view.ResetCommunicator();
		joueur.partie.view.Communicate("Vous ne pouvez pas construire de colonie ici");
		}
	}

	CoordPanel(String q, boolean b) {
		this(q);
		this.desactiverColonies = b ;
	}
	
	public void addCoordListener(ChangeListener cl) {
		this.listener = cl ;
	}
//	public int[] getResult() {
//		int [] result = { this.x , this.y } ;
//		return result;
//	}
	

	
public void drawColonie ( int x, int y) 
{
	Plateau p = joueur.partie.plateau ;
	Sommet s = p.plateauS[y][x] ;
	if (s.colonie) {
	p.vp.drawColonie(joueur,s );}
	else {joueur.partie.view.ResetCommunicator();
	joueur.partie.view.Communicate("Vous ne pouvez pas construire de colonie ici");
	}
	
}



}



///////////////////////////////////FIN COORDPANEL ////////////////////////////////////////////////////////////////



///////////////////////////////////DEBUT OPTIONSPANEL/////////////////////////////////////////////////////////////


public abstract class OptionPanel extends JPanel {	
	
	ChangeListener listener ;
	Joueur  j = VueJoueur.this.joueur ;
	Iterable liste ; 
	
	
	OptionPanel(String question) {
		super() ;
		this.setSize(300 , 400); 
		
		}
	
	public void form() {
	int rang = 0 ;
	for (Object o : this.liste) {
		JButton b = new JButton (o.toString()  ) ;
		int ruse = rang ;
		// sans cette variable, rang n'était pas effectively final et ne permettait pas de récupérer l'indice dans le tableau de l'élément recherché
		b.addActionListener ( e -> { 
			ChangeEvent event = new ChangeEvent(this);
			listener.stateChanged(event);
			
			OptionPanelAction(ruse) ;
			
 });
	this.add(b) ;
	rang ++ ;
	}
	JButton retour = new JButton ("annuler l'action") ;
	retour.addActionListener ( e -> { 
		ChangeEvent event = new ChangeEvent(this);
		listener.stateChanged(event);
		this.setVisible(false);
		VueJoueur.this.ap.setVisible(true);
		
});
	this.add(retour);
	
	}
	
	
	
	
	


	protected abstract void OptionPanelAction(int rang);

	public void addOptionListener(ChangeListener cl) {
		this.listener = cl ;
	}
	
}
////////////////////////////////def des deux sous classes ///////////////////////////////////

public class CartePanel extends OptionPanel {
	
public CartePanel (String s ) {
	super(s);
	this.liste = this.j.cartes ;
}


@Override
public void form() {
int rang = 0 ;
for (Object o : this.liste) {
	JButton b = new JButton (o.toString()  ) ;
	Carte c = (Carte) o;
	// sans cette variable, rang n'était pas effectively final et ne permettait pas de récupérer l'indice dans le tableau de l'élément recherché
	b.addActionListener ( e -> { 
		ChangeEvent event = new ChangeEvent(this);
		listener.stateChanged(event);

		OptionPanelAction(c.getNumero()) ;
		
});
this.add(b) ;
rang ++ ;
}
JButton retour = new JButton ("annuler l'action") ;
retour.addActionListener ( e -> { 
	ChangeEvent event = new ChangeEvent(this);
	listener.stateChanged(event);
	this.setVisible(false);
	VueJoueur.this.ap.setVisible(true);
	
});
this.add(retour);

}







@Override
protected void OptionPanelAction(int rang) {
	j.cartes.get(rang).actionCarte();
}
	
}



////////////////////////////Commerce Panel //////////////////////////////////



// on va tenter d'avoir un panel qui indisue toutes les informations

public class CommercePanel extends JPanel {
	ChangeListener listener ;
	Paysage paysage = Paysage.COLLINE ;
	Paysage paysage2 = Paysage.COLLINE ; 
	
public CommercePanel() {
	this.setSize(300 , 400);
	this.setLayout(new FlowLayout()) ;
	
	
	JButton eff = new JButton ("Tenter la transaction") ;
	eff.addChangeListener(e -> { 
		OperationCommerciale oc = new OperationCommerciale (VueJoueur.this.joueur );
		boolean succes = oc.effectuer();
		if (succes) { this.setVisible (false);
		VueJoueur.this.joueur.vj.ap.setVisible(true); }
		else { VueJoueur.this.joueur.partie.view.ResetCommunicator() ;
		VueJoueur.this.joueur.partie.view.Communicate("La transaction était illégale, recommencez") ;	
		}
		}
			);
	JButton fin = new JButton ("Annuler l'action") ;
	eff.addChangeListener(e -> { this.setVisible (false);
	VueJoueur.this.joueur.vj.ap.setVisible(true); } ); 

}

//	public void addCommerceListener(ChangeListener cl) {
//		this.listener = cl ;
//	}
	
	
	
}





}
