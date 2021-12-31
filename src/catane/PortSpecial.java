package catane;

import catane.Case.Paysage;

public class PortSpecial extends Port {

    Paysage Ressource;

    PortSpecial(Paysage ressource) {
        super();
        this.Ressource = ressource;
        this.setTaux(2);
    }

    public String afficherPort() {
        String s = "~~| Port " + this.Ressource.name();
        while (s.length() < 14) {
            s += " ";
        }
        if (s.length() > 13) {
            s = s.substring(0, 14);
        }
        s += " |";
        return s;
    }

    public String toString() {
        return super.toString() + " vous pourrez ici �changer du " + this.Ressource;
    }

    public String afficherTaux() {
        String Newligne = System.getProperty("line.separator");
        return super.afficherTaux() + Newligne + this.Ressource ;
    }
}

