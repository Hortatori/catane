package catane;

public  abstract class Carte {
	
    private int numero;
    private boolean jouee  = false ;
    public String effet ;
    private Joueur possesseur ;
    
  
    
    public  void Jouer()  { if (jouee) {this.possesseur.partie.Communicate ("la carte " + this.toString()+ " est déjà jouée"); } 
    else { jouee = true ; 
    		actionCarte();	
    }
    }
    


	protected abstract void actionCarte();
    
    public void setPossesseur(Joueur p) {this.possesseur = p ; }
    public Joueur getPossesseur() {return this.possesseur  ; }
	
	public void setNumero ( int i )  {this.numero = i ;}

	public boolean getJouee() {return this.jouee;}
	
    public int getNumero() {return this.numero;}
    
    public String toString() {
    	return this.getClass().getName() +  " jouée :  "+ this.jouee +" effet : " + this.effet ;
    }
    
    
    
}


