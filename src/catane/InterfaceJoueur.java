package catane;

import java.util.Scanner;

public class InterfaceJoueur  {
	
Scanner sc ;
Plateau plateau ;


public InterfaceJoueur(Scanner sc, Plateau p) {
	super();
	this.sc = sc;
	this.plateau = p ;
}

public boolean answerYesNo(String scan) {

    while (!scan.equals("oui") && !scan.equals("non")) {
        System.out.println("répondez à la question posée. ");
        scan = this.sc.next();
    }
    if (scan.equals("oui")) {
        return true;
    }
    return false;
}

// *************rÃ©ponse tableau*************
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
            construireColonieVille(joueur, true);
            break;
        case 2:
            construireColonieVille(joueur, false);
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
public void construireColonieVille(Joueur joueur, boolean colonie) {
  String construction = "ville";
  if (colonie) {
      construction = "colonie";
  }
  System.out.println(joueur.getNom() + ", pour placer une " + construction + ", donnez ses coordonnées");
  int scan = sc.nextInt();
  int X = answerInPlateau(scan, 0);
  scan = sc.nextInt();
  int Y = answerInPlateau(scan, 1);
 // this.plateau.printBoolean();
  if (colonie) {
      if (plateau.plateauS[Y][X].colonie) {
          System.out.println("il y a dÃ©jÃ  une colonie ici ! ");
          construireColonieVille(joueur, true);
      }
      joueur.placerColonieInit(plateau.plateauS[Y][X]);
      return;
  }
  joueur.placerVilleInit(plateau.plateauS[Y][X]);
  this.plateau.afficherPlateau();
}

//************construction route***************
public void construireRoute(Joueur joueur) {
  System.out.println(
          joueur.getNom() + ", pour placer une Route, donnez les coordonnÃ©es de dbut et de fin de la route");

  System.out.println("Donnez les coordonnÃ©es du début de la route");
  int scan = sc.nextInt();
  int debutX = answerInPlateau(scan, 0);
  scan = sc.nextInt();
  int debutY = answerInPlateau(scan, 1);
  System.out.println("Donner les coordonnées d'arrivée de la route");
  scan = sc.nextInt();
  int finX = answerInPlateau(scan, 0);
  scan = sc.nextInt();
  int finY = answerInPlateau(scan, 1);
  // Attention, dans le tableau le premier élément renvoie à la hauteur, le second à la l abcisse
  Sommet s1 = plateau.plateauS[debutY][debutX];
  Sommet s2 = plateau.plateauS[finY][finX];
  s1.AfficherCoord();
  s2.AfficherCoord();
  joueur.placerRoute(s1, s2, this.plateau);
  this.plateau.afficherPlateau();
  //this.printTables();
}


}
