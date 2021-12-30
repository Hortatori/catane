package catane;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import java.util.Random;

public class Partie {

    int n_joueur;
    LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
    Plateau plateau;
    Scanner sc;

    public Partie() {
        this.sc = new Scanner(System.in);
        this.plateau = new Plateau();
        System.out.println("Bienvenue pour une nouvelle partie des Colons de Catane !");

        System.out.println("Veuillez indiquer si vous préférez jouer à trois ou quatre joueur");
        String scan = sc.next();

        while (((scan.equals("3")) || (scan.equals("4"))) == false) {
            System.out.println("répondez à la question posée. ");
            scan = sc.next();
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
            scan = sc.next();
            Joueur j = new Joueur(scan);
            bienvenue += scan + ", ";
            this.joueurs.add(j);
        }

        System.out.println(bienvenue + " !");

        plateau.afficherPlateau();

        // System.out.println("Chaque joueur peut placer une route et une colonie en
        // début de partie!");
        // for (Joueur joueur : joueurs) {
        // construireColonieVille(joueur, true);
        // construireRoute(joueur);
        // }

        System.out.println("Début de la partie !");
        Jouer();

        sc.close();

    }

    public void Cavalier() {
        System.out.println("voilà le cavalier ! ");
    }

    public boolean Victoire() {
        for (Joueur j : this.joueurs) {
            if (j.getPts() > 9) {
                return true;

            }
        }
        return false;
    }

    public void Jouer() {
        int ind_joueur = 0;

        while (Victoire() == false) {
            Joueur joueur = this.joueurs.get(ind_joueur);
            joueur.Tour();
            this.incrementRessource(joueur.de);
            System.out.println(joueur);
            actions(joueur);
            this.plateau.afficherPlateau();
            ind_joueur++;
            if (ind_joueur == this.n_joueur) {
                ind_joueur = 0;
            }

        }

    }

    public void incrementRessource(int de) {
        for (int i = 0; i < plateau.plateauC[0].length; i++) {
            for (int j = 0; j < plateau.plateauC[1].length; j++) {
                if (plateau.plateauC[i][j].numero == de) {
                    for (Joueur joueur : joueurs) {
                        plateau.plateauC[i][j].checkCornerIncrement(joueur);

                    }
                }
            }
        }
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
                Commerce(this.sc, joueur);
                break;

            case 5:
                return;
            default:
                System.out.println("vous devez donner un entier entre 1 et 5");
                actions(joueur);

        }
        actions(joueur);

    }

    // ************construction ville et colonie, choix bool*************
    public void construireColonieVille(Joueur joueur, boolean colonie) {
        String construction = "ville";
        if (colonie) {
            construction = "colonie";
        }
        System.out.println(joueur.getNom() + ", pour placer une " + construction + ", donnez ses coordonnées");
        int scan = this.sc.nextInt();
        int X = answerInPlateau(scan, 0);
        scan = this.sc.nextInt();
        int Y = answerInPlateau(scan, 1);
        if (colonie) {
            if (plateau.plateauS[X][Y].colonie) {
                System.out.println("il y a déjà une colonie ici ! ");
                construireColonieVille(joueur, true);
            }
            joueur.placerColonieInit(plateau.plateauS[X][Y]);
            return;
        }
        joueur.placerVilleInit(plateau.plateauS[X][Y]);

    }

    // ************construction route***************
    public void construireRoute(Joueur joueur) {
        System.out.println(
                joueur.getNom() + ", pour placer une Route, donnez les coordonnées de dbut et de fin de la route");

        System.out.println("Donnez les coordonnées du début de la route");
        int scan = this.sc.nextInt();
        int debutX = answerInPlateau(scan, 0);
        scan = this.sc.nextInt();
        int debutY = answerInPlateau(scan, 1);
        System.out.println("Donner les coordonnées d'arrivée de la route");
        scan = this.sc.nextInt();
        int finX = answerInPlateau(scan, 0);
        scan = this.sc.nextInt();
        int finY = answerInPlateau(scan, 1);
        Sommet s1 = plateau.plateauS[debutX][debutY];
        Sommet s2 = plateau.plateauS[finX][finY];

        if (joueur.routeDejaConstruite(s1, s2)) {
            wrongWay(joueur, "votre route existe déjà! ");
        }
        if (s1.routeLegale(s2)) {
            joueur.placerRoute(s1, s2, this.plateau);
        } else {
            wrongWay(joueur,
                    "votre route est trop longue! ");
        }
    }

    // ************choix réponse en cas de faux, route************
    public void wrongWay(Joueur joueur, String warning) {
        System.out.println(
                warning + "Tapez :\n1 pour essayer d'en construire une autre,\n2 pour faire une autre action");
        int scanner = this.sc.nextInt();
        switch (scanner) {
            case 1:
                construireRoute(joueur);
                break;
            case 2:
                actions(joueur);
                break;
            default:
                System.out.println("réponse invalide, choisissez une action de jeu");
                actions(joueur);
        }
    }

    // ************réponse oui non************
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

    // *************réponse tableau*************
    public int answerInPlateau(int scan, int dimension) {
        while (scan >= plateau.plateauS[dimension].length - 1 || scan < 0) {
            System.out.println("cette coordonnee n'est pas valide");
            scan = this.sc.nextInt();
        }
        return scan;
    }

    // **************** commerce ********************
    public void Commerce(Scanner sc, Joueur joueur) {
        System.out.println(joueur.getNom() + ", souhaitez-vous faire du commerce?");
        String scan = sc.next();

        if (answerYesNo(scan)) {

            System.out.println("commerce maritime?");
            scan = sc.next();
            if (answerYesNo(scan)) {
                System.out.println("le commerce n'est pas encore implementé");
            }

            System.out.println("commerce interieur?");
            scan = sc.next();
            if (answerYesNo(scan)) {
                System.out.println("le commerce n'est pas encore implementé");
            }
        }
    }

    /// ************** donnees routes ********** */
    public LinkedList<Joueur[][]> constructionData() {
        Joueur[][] routesHJoueurs = new Joueur[this.plateau.plateauC[0].length + 1][this.plateau.plateauC[1].length
                + 1];
        Joueur[][] routesVJoueurs = new Joueur[this.plateau.plateauC[0].length + 1][this.plateau.plateauC[1].length
                + 1];

        for (Joueur joueur : this.joueurs) {
            for (Route route : joueur.getListRoute()) {

                if (route.arrivee.plusPetit(route.depart) && route.horizontal) {
                    routesHJoueurs[route.arrivee.hauteur][route.arrivee.largeur] = joueur;
                } else if (route.arrivee.plusPetit(route.depart) && !route.horizontal) {
                    routesVJoueurs[route.arrivee.hauteur][route.arrivee.largeur] = joueur;
                } else if (!route.arrivee.plusPetit(route.depart) && route.horizontal) {
                    routesHJoueurs[route.depart.hauteur][route.depart.largeur] = joueur;
                } else {
                    routesVJoueurs[route.depart.hauteur][route.depart.largeur] = joueur;
                }
            }
        }
        LinkedList<Joueur[][]> routeHrouteV = new LinkedList();
        routeHrouteV.add(routesHJoueurs);
        routeHrouteV.add(routesVJoueurs);

        return routeHrouteV;
    }

    // ************** pour 2 sommets, renvoie un joueur si la route existe, null
    // sinon *************
    public Joueur testProprietaire(Sommet s1, Sommet s2) {
        LinkedList<Joueur[][]> data = constructionData();
        Joueur[][] vertical = data.get(1);
        Joueur[][] horizontal = data.get(0);

        if (s1.plusPetit(s2)) {
            if (s1.routeHorizontale(s2)) {
                return horizontal[s1.hauteur - 1][s1.largeur - 1];
            } else {
                return vertical[s1.hauteur - 1][s1.largeur - 1];
            }
        } else {
            if (s1.routeHorizontale(s2)) {
                return horizontal[s2.hauteur - 1][s2.largeur - 1];
            } else {
                return vertical[s2.hauteur - 1][s2.largeur - 1];
            }
        }
    }

}
