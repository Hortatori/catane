package catane;

public class Voleur{
    private Case caseVoleur;
    private Plateau plateau;




    public Voleur(Plateau p){
        this.plateau = p;

    }

    public void setCase(Case c){
        this.caseVoleur = c;
    }
    public Case getCase(){
        return this.caseVoleur;
    }

    public void deplaceVoleur(Case c){
        this.caseVoleur.setStatutVoleur(false);
        c.setStatutVoleur(true);
        this.caseVoleur = c;
    }


}