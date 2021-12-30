
package catane;

import java.io.InputStream;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;






public class Partie {
	
	
	Plateau plateau = new Plateau();
	InterfaceJoueur ij = new InterfaceJoueur(new Scanner(System.in), plateau) ;
		
	int n_joueur ;
	LinkedList<Joueur> joueurs = new LinkedList<Joueur> () ;

	Vue view ;
	
public Partie() {
	
	
	
	System.out.println("Bienvenue pour une nouvelle partie des Colons de Catane !");
	System.out.println("Voulez vous jouer avec notre merveilleuse interface graphique ? (yes / no) !");
	String reponse = ij.sc.next();
	if (! ij.answerYesNo(reponse) ) {
	AccueilTexte(); }
	else {
		this.plateau.graphique = true ;
		
		
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() {
				public void run() { 
					
				Vue vue = new Vue(Partie.this);
				Partie.this.view = vue ;
			

				}
				}
				);
		
	}
		initialiser();
		int j = 0;
		while (Victoire() == false) {
			Tour(this.joueurs.get(j));
			j++ ;
			if (j == this.n_joueur) {j =0; } ;
			
			
		}
		
	
	
}


public void AccueilTexte() {
	// this.sc = new Scanner(System.in);
    
    
    System.out.println("Veuillez indiquer si vous préférez jouer Ã  trois ou quatre joueur");
    String scan = ij.sc.next();

    while (((scan.equals("3")) || (scan.equals("4"))) == false) {
        System.out.println("répondez Ã  la question posée. ");
        scan = ij.sc.next();
    }
    // int n_joueur;
    if (scan.equals("3")) {
        this.n_joueur = 3;
    } else {
        this.n_joueur = 4;
    }

    System.out.println("Vous serez donc " + this.n_joueur + " joueurs à jouer !");

    String bienvenue = "Bienvenue dans le jeu, ";
    for (int i = 1; i < (this.n_joueur + 1); i++) {
        System.out.println("Entrez le nom du joueur " + (i));
        scan = ij.sc.next();
        for (Joueur former : this.joueurs) { if (former.getNom().equals(scan) ) { System.out.println("nom déjà pris") ;
        scan = ij.sc.next() ; }} 
        Joueur j = new Joueur(scan);
        bienvenue += scan + ", ";
        Color c = new RandomColor().c ;
        
        for (Joueur  former : this.joueurs) { while(c.equals(former.getCouleur())) { c = new RandomColor().c ;}}
        
        
   
        this.joueurs.add(j);
        
    }
    

    
    

    System.out.println(bienvenue + " !");

    this.plateau = new Plateau();

    plateau.afficherPlateau();

   



	
	

}



public void initialiser() {
	if (this.plateau.graphique) {
	for (Joueur j : this.joueurs) {
		VueJoueur vj = new VueJoueur(j);
		this.view.add(vj);
	}
	}
	
	else {
		System.out.println("Chaque joueur peut placer une route et une colonie en début de partie!");
	     for (Joueur joueur : joueurs) {
	     ij.construireColonie(joueur);
	     // on attribue une ressource initiale
	     
	     for (Case [] tc : this.plateau.plateauC) {
	 		for (Case c : tc) {
	 			if (c.Sommets.contains(joueur))
	 				joueur.PossedeCase(c);
	 			}
	 			
	 		}
	 	
	     
	     ij.construireRoute(joueur);
	     }
	     System.out.println("Et on recommence dans l ordre inverse");
	     
	     for (int i = 0 ; i< this.n_joueur ; i++) {
	    	 Joueur joueur = this.joueurs.get(i) ;
	    	 ij.construireColonie(joueur);
	    	 ij.construireRoute(joueur);
	     }
	     
	    System.out.println("Début de la partie !");
	}
	
}


public void Jouer() {
    int ind_joueur = 0;

    while (Victoire() == false) {
        Joueur joueur = this.joueurs.get(ind_joueur);
        LanceDe();
        ij.actions(joueur);
        this.plateau.afficherPlateau();
        ind_joueur++;
        if (ind_joueur == this.n_joueur) {
            ind_joueur = 0;
        }

    }

}


public boolean Victoire() {
    for (Joueur j : this.joueurs) {
        if (j.getPts() > 9) {
            return true;

        }
    }
    return false;
}






public void Cavalier() {
	System.out.println("voilà le cavalier ! ");
}




	


//**************** commerce ********************
public void Commerce(Scanner sc, Joueur joueur) {
    System.out.println(joueur.getNom() + ", souhaitez-vous faire du commerce?");
    String scan = sc.next();

    if (ij.answerYesNo(scan)) {

        System.out.println("commerce maritime?");
        scan = sc.next();
        if (ij.answerYesNo(scan)) {
            System.out.println("le commerce n'est pas encore implementÃ©");
        }

        System.out.println("commerce interieur?");
        scan = sc.next();
        if (ij.answerYesNo(scan)) {
            System.out.println("le commerce n'est pas encore implementÃ©");
        }
    } else {
        return;
    }
}











// ************rÃ©ponse oui non************




public void printTables() {
	for ( String[]  ts : this.plateau.routesHorizontales) {
		String r = "" ;
		for ( String s  : ts ) {
			r += s ;
		}
		System.out.println(r);
	}
	for ( String[]  ts : this.plateau.routesVerticales) {
		String r = "" ;
		for ( String s  : ts ) {
			r += s ;
		}
		System.out.println(r);
	}
}
public int LanceDe() {
    Random rd = new Random();
    int de = rd.nextInt(11) + 2;
    System.out.println("Les dés ont donné " + de + " ! ");
    return de ;

}

public void Tour (Joueur leader) {
	int de = LanceDe()  ;
	// update les ressources par rapport au lancé de dé
	for (Case [] tc : this.plateau.plateauC) {
		for (Case c : tc) {
			for (Joueur j : this.joueurs) {
				j.PossedeCase(c);
			}
			
		}
	}
	
	if (this.plateau.graphique == false ) {
		System.out.println("C'est à "+ leader.getNom()+ " de jouer");
		System.out.println(leader.getR());
		this.ij.actions(leader);
	}
	else { 
		this.view.updateJoueur(leader, de);
	}
	
	
	
	
}



	
}
