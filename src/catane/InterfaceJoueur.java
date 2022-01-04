package catane;

import java.util.Scanner;

public class InterfaceJoueur {

    Scanner sc;
    Plateau plateau;
    Partie partie;

    public InterfaceJoueur(Scanner sc, Plateau p, Partie partie) {
        super();
        this.sc = sc;
        this.plateau = p;
        this.partie = partie;
    }

    public boolean answerYesNo(String scan) {

        while (!scan.equals("oui") && !scan.equals("non")) {
            System.out.println("repondez a la question posee. ");
            scan = this.sc.next();
        }
        if (scan.equals("oui")) {
            return true;
        }
        return false;
    }

    // *************réponse tableau*************
    public int answerInPlateau(int scan, int dimension) {
        while (scan > 4 || scan < 0) {
            System.out.println("cette coordonnee n'est pas valide");
            scan = this.sc.nextInt();
        }
        return scan;
    }

    public void actions(Joueur joueur) {
        // a fignoler plus tard boolean droitdejouercarte = true ; // on ne peut en
        // jouer qu'une par tour
        System.out.println(
                joueur.getNom() +
                        ", souhaitez-vous :\n 1 : Construire une colonie?\n 2 : Construire une ville? \n 3 : Construire une route? \n 4 : Faire du commerce?\n 5 : Finir votre tour");

        int scan = this.sc.nextInt();
        switch (scan) {
            case 1:

                construireColonie(joueur,
                        getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonnees"));

                break;
            case 2:

                construireVille(joueur, getCoord(joueur.getNom() + ", pour placer une ville , donnez ses coordonnees"));
                break;

            case 3:
                construireRoute(joueur,
                        getCoord(joueur.getNom() + ", pour placer une route , donnez les coordonnees du depart"),
                        getCoord("et celle de l'arriv�e"));
                break;

            case 4:
                OperationCommerciale tc = new OperationCommerciale(joueur);
                tc.effectuer(false);
                break;
            case 5:
                if (this.partie.pioche.pioche.isEmpty()) {
                    System.out.println("la pioche est vide ! ");
                    break;
                }
                Carte c = this.partie.pioche.pioche.getFirst();
                joueur.cartes.add(c);
                this.partie.pioche.pioche.removeFirst();
                joueur.afficherCartes();
                break;

            case 6:
                joueur.afficherCartes();
                System.out.println("Donnez nous le numero de la carte que vous voulez jouer (0 = annuler) ");
                int rep = sc.nextInt();
                if (rep == 0) {
                    break;
                }
                if (rep > joueur.cartes.size() + 1) {
                    System.out.println("votre indice depasse le nombre de carte");
                    rep = sc.nextInt();
                }

                else {
                    joueur.cartes.get(rep - 1).Jouer();
                }
                break;

            case 7:
                return;

            default:
                System.out.println("vous devez donner un entier entre 1 et 5");
                actions(joueur);

        }
        actions(joueur);

    }

    // ************construction ville et colonie, choix bool*************

    public int[] getCoord(String question) {
        System.out.println(question);
        int scan = this.sc.nextInt();
        int X = answerInPlateau(scan, 0);
        scan = this.sc.nextInt();
        int Y = answerInPlateau(scan, 1);
        int[] result = { X, Y };
        return result;
    }

    public void construireColonie(Joueur joueur, int[] tab) {

        int X = tab[0] + 1;
        int Y = tab[1] + 1; // pb de l indice est ici +1 a jouter ou pas
        boolean flag = true;

        if (plateau.plateauS[X][Y].colonie) {
            System.out.println("Conditions non respectees, ");
            System.out.println("il y a deja une colonie ici ! ");
            flag = false;

        }
        if (X > 0) {
            if (plateau.plateauS[X - 1][Y].colonie) {
                System.out.println("Conditions non respectees, ");
                System.out.println("il ne peut pas y avoir deux colonies voisines, voyons ! ");
                flag = false;
            }
        }
        if (Y > 0) {
            if ((plateau.plateauS[X][Y - 1].colonie)) {
                System.out.println("Conditions non respectees, ");
                System.out.println("il ne peut pas y avoir deux colonies voisines, voyons ! ");
                flag = false;
            }
        }

        if ((plateau.plateauS[X + 1][Y].colonie) || (plateau.plateauS[X][Y + 1].colonie)) {
            System.out.println("Conditions non respectees ");
            System.out.println("Il ne peut pas y avoir deux colonies voisines, voyons ! ");
            flag = false;
        }

        if (flag) {
            joueur.placerColonieInit(plateau.plateauS[Y][X]);

            if (this.plateau.graphique == true) {
                this.partie.view.drawColonie(joueur, X, Y);

            }
            plateau.afficherPlateau();

        } else {
            if (this.plateau.graphique == false) {

                construireColonie(joueur,
                        getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonn�es"));
            }
        }

    }

    public void construireVille(Joueur joueur, int[] tab) {

        int X = tab[0] + 1;
        int Y = tab[1] + 1;
        Sommet considere = plateau.plateauS[Y][X];

        if ((considere.colonie) && (joueur.colonies.contains(considere)))
        // verifier si pas d�j� colonie
        {
            joueur.placerColonieInit(plateau.plateauS[Y][X]);
            plateau.afficherPlateau();
        } else {
            System.out.println("Vous devez posseder une colonie pour l ameliorer en ville");
            construireVille(joueur, getCoord(joueur.getNom() + ", pour placer une colonie , donnez ses coordonnees"));
        }
    }

    // ************construction route***************
    public void construireRoute(Joueur joueur, int[] tabd, int[] tabf) {
        System.out.println(
                joueur.getNom() + ", pour placer une Route, donnez les coordonnées de debut et de fin de la route")
        int debutX = tabd[0] + 1;
        int debutY = tabd[1] + 1;
        int finX = tabf[0] + 1;
        int finY = tabf[1] + 1;

        // Attention, dans le tableau le premier �l�ment renvoie � la hauteur, le second
        // � la l abcisse
        Sommet s1 = plateau.plateauS[debutY][debutX];
        Sommet s2 = plateau.plateauS[finY][finX];
        s1.AfficherCoord();
        s2.AfficherCoord();
        boolean flag = false;
        if ((joueur.colonies.contains(s1)) || (joueur.colonies.contains(s2))) {
            flag = true;
        }
        for (Route r : joueur.routes) {
            if ((r.depart == s1) || (r.depart == s2) || (r.arrivee == s1) || (r.arrivee == s1)) {
                flag = true;
            }

        }

        if (s1.routeLegale(s2) == false) {
            flag = false;
            System.out.println("une route doit etre de longueur 1 et relier deux sommets adjacents");
        }

        for (Route r : this.plateau.routes) {
            if ((s1 == r.depart) && (s2 == r.arrivee)) {
                System.out.println("La route existe d�j�");
                flag = false;
            }
            if ((s2 == r.depart) && (s1 == r.arrivee)) {
                System.out.println("La route existe d�j�");
                flag = false;
            }
        }

        if (flag) {

            joueur.placerRoute(s1, s2, this.plateau);
            this.plateau.afficherPlateau();
        }

        else {

            System.out.println(
                    "les conditions ne sont pas reunies, votre route ne touche ni une de vos colonies ni une de vos routes");
            construireRoute(joueur,
                    getCoord(joueur.getNom() + ", pour placer une route , donnez les coordonnees du depart"),
                    getCoord("et celle de l'arriv�e"));
        }
        // this.printTables();
    }

    //
    public Paysage demandeRessource() {
        System.out.println(
                " S�lectionnez une ressource :\n 1 : Bois\n 2 : Laine \n 3 : Pierre \n 4 : Champ \n 5 : Argile");

        int scan = this.sc.nextInt();
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
                return demandeRessource();

        }
    }

}
