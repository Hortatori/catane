import java.util.LinkedList;

package catane;

public class Voleur {
    private Case caseVoleur;
    private Plateau plateau;
    private Partie partie;

    public Voleur(Plateau p) {
        this.plateau = p;

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
    public void VoleurArrive(LinkedList<Joueur> joueurs, boolean ig) {
        if (!ig) {
            System.out.println(
                    "le voleur arrive! personne ne profite des ressources \ntous les joueur.euses qui ont plus de 6 ressources doivent defausser la moitie de leur main");
        } else {
            // affichage
        }
    }

    public void VoleurPart(int[] coord) {
        int x = coord[0];
        int y = coord[1];

        this.deplaceVoleur(plateau.plateauC[x][y]);
    }
}