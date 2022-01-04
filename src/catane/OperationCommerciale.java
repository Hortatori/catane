package catane;

import java.awt.Color;
import java.util.Scanner;

public class OperationCommerciale  {
boolean graphique = this.j.partie.plateau.graphique;
int taux = 4 ;
Joueur j  ;



public OperationCommerciale (Joueur j) {
	
	this.j = j ;
	{if (j.ports.size() > 0 ) { taux = 3 ;} 
	}
}

public boolean effectuer () {
	
	
		System.out.println("Sélectionnez d'abord la ressource que vous voulez obtenir ") ;
		Paysage p ;
		if (graphique) {p = demanderRessource() ; }
		else {p = this.j.vj.paysage  ;
		diviserAction("Le taux de base est de "+taux+ " contre 1 .");
		for (Port port : j.ports) {
			if (port instanceof PortSpecial) {
				System.out.println(" Grâce au port, vous bénéficiez d'un taux de 2 contre 1 en échangeant du "+ ((PortSpecial) port).Ressource.name() ) ;
			}
		}
		
		diviserAction ("Et ensuite celle que vous voulez céder") ;
		Paysage p2 ;
		if (graphique) {p2 = demanderRessource() ; }
		else {p2 = this.j.vj.paysage2 ;}
		
		for (Port port : j.ports) {
			if (port instanceof PortSpecial) {
				if ( ((PortSpecial) port).Ressource == p2 ) {
					this.taux = 2 ;
				}
				
				
				}
		}
boolean legal = false ;
// conversion paysage ressource
switch (p2) {
	
			
		case MONTAGNE :
			if (j.getR().getPierre() >= taux ) {
	
				j.getR().payPierre(taux);
				legal = true ;
				}
			
			
			
		case PRE :
			if (j.getR().getMouton() >= taux ) {
				
				j.getR().payMouton(taux);
				legal = true ;
				}
			
			
		case FORET :
			if (j.getR().getBois() >= taux ) {
				
				j.getR().payBois(taux);
				legal = true ;
				}
			
			
			
		case COLLINE :
			if (j.getR().getArgile() >= taux ) {
				
				j.getR().payArgile(taux);
				legal = true ;
				}
			
			
		case CHAMP :
			if (j.getR().getBle() >= taux ) {
				
				j.getR().payBle(taux);
				legal = true ;
				}
			
			
		default :
			break ;
			
		
		
			
		}
		
// et si c est légal on verse la ressource achetée
	
if (legal ) { 
diviserAction("la transaction peut avoir lieu") ;
	
	
	switch (p) {


case MONTAGNE :
	
		j.getR().addPierre(1);
		
	
	
	
case PRE :
		
		j.getR().addMouton(1);
		
	
	
case FORET :
	
		j.getR().addBois(1);
		
	
	
	
case COLLINE :
		
		j.getR().addArgile(1);
		
	
	
case CHAMP :
		j.getR().addBle(1);
		
	
	
default :
	return false;
	

		
	}
}
else { diviserAction("transaction illégale") ; } 

return legal;	
		}


return false;
	
}


public Paysage demanderRessource() {
    System.out.println(
           " Sélectionnez une ressource :\n 1 : Bois\n 2 : Laine \n 3 : Pierre \n 4 : Champ \n 5 : Argile");
    Scanner sc = new Scanner (System.in);
    int scan = sc.nextInt();
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
            demanderRessource();

    }
	return null;
   
	

}

public void  diviserAction(String s) {
	if (graphique) {this.j.partie.view.Communicate(s);}
	else {System.out.println(s);}
	
}


}