package catane;

import java.awt.Color;
import java.util.Scanner;

public class OperationCommerciale  {

int taux = 4 ;
Joueur j  ;



public OperationCommerciale (Joueur j) {
	
	this.j = j ;
	{if (j.ports.size() > 0 ) { taux = 3 ;} 
	}
}

public void effectuer (boolean graphique) {
	// ruse pour �viter de demander un acc�s au plateau.
	if (!graphique) {
		System.out.println ("Quelle ressource voulez vous obtenir ?") ;
		Paysage p = demanderRessource() ;
		System.out.println("Le taux de base est de "+taux+ " contre 1 .");
		for (Port port : j.ports) {
			if (port instanceof PortSpecial) {
				System.out.println(" Gr�ce au port, vous b�n�ficiez d'un taux de 2 contre 1 en �changeant du "+ ((PortSpecial) port).Ressource.name() ) ;
			}
		}
		
		System.out.println ("Quelle ressource voulez vous obtenir ?") ;
		Paysage p2 = demanderRessource() ;
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
		
// et si c est l�gal on verse la ressource achet�e
	
if (legal ) { 
System.out.println("la transaction peut avoir lieu") ;
	
	
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
	return ;
	

		
	}
}
else { System.out.println("transaction ill�gale") ; }

}
	
	
}


public Paysage demanderRessource() {
    System.out.println(
           " S�lectionnez une ressource :\n 1 : Bois\n 2 : Laine \n 3 : Pierre \n 4 : Champ \n 5 : Argile");
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




}