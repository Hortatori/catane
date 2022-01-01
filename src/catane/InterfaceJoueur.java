package catane;

import java.util.Scanner;

public class InterfaceJoueur  {
	
Scanner sc ;
Plateau plateau ;
Partie partie  ;


public InterfaceJoueur(Scanner sc, Plateau p, Partie partie) {
	super();
	this.sc = sc;
	this.plateau = p ;
	this.partie = partie ;
}

public boolean answerYesNo(String scan) {

    while (!scan.equals("oui") && !scan.equals("non")) {
        System.out.println("r�pondez � la question pos�e. ");
        scan = this.sc.next();
    }
    if (scan.equals("oui")) {
        return true;
    }
    return false;
}

// *************réponse tableau*************
public int answerInPlateau(int scan, int dimension) {
    while (scan > 4|| scan < 0) {
        System.out.println("cette coordonnee n'est pas valide");
        scan = this.sc.nextInt();
    }
    return scan;
}

public void actions(Joueur joueur) {
    System.out.println(
            joueur.getNom() +
                    ", souhaitez-vous :\n 1 : Construire une colonie?\n 2 : Construire une ville? \n 3 : Construire une route? \n 4 : Faire du commerce?\n 5 : Finir votre tour");

    int scan = this.sc.nextInt();
    switch (scan) {
        case 1:
        	 
        		 int [] coord =  getCoord (joueur.getNom() + ", pour placer une colonie , donnez ses coordonn�es") ;
        		int X = coord [0];
        		int Y = coord [1];
        	 
            construireColonie(joueur, X , Y);
            break;
        case 2:
            construireVille(joueur);
            break;

        case 3:
            construireRoute(joueur);
            break;

        case 4:
          //  Commerce(this.sc, joueur);
            break;

        case 5:
            return;

        default:
            System.out.println("vous devez donner un entier entre 1 et 5");
            actions(joueur);

    }
    actions(joueur);

}



//************construction ville et colonie, choix bool*************


public int [] getCoord(String question ) {
	int scan = sc.nextInt();
	  int X = answerInPlateau(scan, 0);
	  scan = sc.nextInt();
	  int Y = answerInPlateau(scan, 1);
	 int [] result = {X , Y};
	 return result ;
}
public void construireColonie(Joueur joueur, int X, int Y) {
  
 
  boolean flag = true ;
  
  if (plateau.plateauS[X][Y].colonie) {
  				System.out.println("Conditions non respect�es ");
  				System.out.println("il y a d�j� une colonie ici ! ");
  				flag = false ; 

  				}
  if (X>0 ) {
  if ( plateau.plateauS[X-1][Y].colonie) {
		System.out.println("Conditions non respect�es ");
		System.out.println ("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
		flag = false ; 
		}}
  if (Y>0 ) {
  if ( (plateau.plateauS[X][Y-1].colonie)) {
		System.out.println("Conditions non respect�es ");
		System.out.println ("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
		flag = false ; 
		}}
  
  if ( (plateau.plateauS[X+1][Y].colonie)||(plateau.plateauS[X][Y+1].colonie)) {
		System.out.println("Conditions non respect�es ");
		System.out.println ("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
		flag = false ; 
		}
  
		
  
  				
  				
  				
  			
  	
    if (flag) {
      joueur.placerColonieInit(plateau.plateauS[Y][X]);
      
      if (this.plateau.graphique == true) {
    	  this.partie.view.drawColonie(joueur, X, Y);
    	  
      }
      plateau.afficherPlateau();
      
    
    
    }
    else {
    	 if (this.plateau.graphique == false) {
    		 int [] coord =  getCoord (joueur.getNom() + ", pour placer une colonie , donnez ses coordonn�es") ;
    		X = coord [0];
    		Y = coord [1];
    	 
    	  
    	construireColonie(joueur, X, Y)  ; }
    }
    
      }
	
    

public void construireVille(Joueur joueur) {
	 
	  System.out.println(joueur.getNom() + ", pour placer une ville , donnez ses coordonn�es");
	  int scan = sc.nextInt();
	  int X = answerInPlateau(scan, 0);
	  scan = sc.nextInt();
	  int Y = answerInPlateau(scan, 1);
	  Sommet considere = plateau.plateauS[Y][X] ;

	      if ((considere.colonie) && (joueur.colonies.contains(considere)))
	      {
	      joueur.placerColonieInit(plateau.plateauS[Y][X]);
	      plateau.afficherPlateau();
	      }
	      else  {System.out.println("Vous devez poss�der une colonie pour l am�liorer en ville") ; }
}
	     



  
 

//************construction route***************
public void construireRoute(Joueur joueur) {
  System.out.println(
          joueur.getNom() + ", pour placer une Route, donnez les coordonnées de dbut et de fin de la route");

  System.out.println("Donnez les coordonnées du d�but de la route");
  int scan = sc.nextInt();
  int debutX = answerInPlateau(scan, 0);
  scan = sc.nextInt();
  int debutY = answerInPlateau(scan, 1);
  System.out.println("Donner les coordonn�es d'arriv�e de la route");
  scan = sc.nextInt();
  int finX = answerInPlateau(scan, 0);
  scan = sc.nextInt();
  int finY = answerInPlateau(scan, 1);
  // Attention, dans le tableau le premier �l�ment renvoie � la hauteur, le second � la l abcisse
  Sommet s1 = plateau.plateauS[debutY][debutX];
  Sommet s2 = plateau.plateauS[finY][finX];
  s1.AfficherCoord();
  s2.AfficherCoord();
  boolean flag = false ;
  if ((joueur.colonies.contains(s1)) || (joueur.colonies.contains(s2))) { flag = true ;}
  for (Route r : joueur.routes) {
	  if  ( (r.depart == s1) || (r.depart == s2) || (r.arrivee == s1) || (r.arrivee == s1) ) { flag = true;}
		  
  }
  
  if (s1.routeLegale(s2)== false ) {
	  flag = false;
	  System.out.println("une route doit �tre de longueur 1 et relier deux sommets adjacents");
  }
  
  for (Route r : this.plateau.routes) {
	  if (( s1 == r.depart )&&( s2 == r.arrivee)) {
		  System.out.println("La route existe d�j�") ;
		  flag = false ;
	  }
	  if (( s2 == r.depart )&&( s1 == r.arrivee)) {
		  System.out.println("La route existe d�j�") ;
		  flag = false ;
	  }
  }
  
  if (flag) {
	  
  
  joueur.placerRoute(s1, s2, this.plateau);
  this.plateau.afficherPlateau();}
  
 
  
  
  
  else { 
	  
	  System.out.println("les conditions ne sont pas r�unies");
	  construireRoute(joueur);}
  //this.printTables();
}



public Paysage demandeRessource() {
    System.out.println(
           " S�lectionnez une ressource :\n 1 : Bois\n 2 : Laine \n 3 : Pierre \n 4 : Champ \n 5 : Argile");
    int scan = this.sc.nextInt();
    switch (scan) {
        case 1:
        	return Paysage.FORET ;
            
            
        case 2:
            return Paysage.PRE ;

        case 3:
            return Paysage.MONTAGNE ;

        case 4:
        	return Paysage.CHAMP ;

        case 5:
            return Paysage.COLLINE ;

        default:
            System.out.println("vous devez donner un entier entre 1 et 5");
            return demandeRessource();
            

    }
   
	

}








}
