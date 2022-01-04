import java.util.LinkedList;

package catane;

public class Voleur {
    private Case caseVoleur;
    private Plateau plateau;
    private Partie partie;
    private boolean ig;
    private int[] coord = new int[2];

    public Voleur(Partie p) {
        this.partie = p;
        // this.plateau = p.plateau;
        this.ig = p.plateau.graphique;

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
    }

    // si dé == 7 dans Partie / IG
    public void VoleurArrive(LinkedList<Joueur> joueurs, Joueur leader) {
        if (!ig) {
            System.out.println(
                    "le voleur arrive! personne ne profite des ressources \ntous les joueur.euses qui ont plus de 6 ressources doivent defausser la moitie de leur main");
            // gestion ressource, recyclage Commerce?
            this.partie.ig.getcoord("Joue u r"+leader.getNom()+", vous decidez quelle sera la prochaine place du Voleur! Donnez des coordonnées");
        } else {
            // affichage
        }
    }

    public void VoleurPart(int[] coord) {
        int x = coord[0];
        int y = coord[1];

        this.deplaceVoleur(partie.plateau.plateauC[x][y]);
    }
}
