package catane;

public  abstract class Carte {
    private int numero;
    private boolean jouee  = false ;
    private String effet ;
    private Joueur possesseur ;
    
  
    
    public  void Jouer()  { if (jouee) { System.out.println("la carte est déjà jouée"); } 
    else { jouee = true ; 
    	actionCarte();
    	
    	
    }
    

    
    
    }
    
    public void jouerCarte() {
    	this.jouee = true ;
    }

	protected abstract void actionCarte();
    
    public void setPossesseur(Joueur p) {this.possesseur = p ; }
    public Joueur getPossesseur() {return this.possesseur  ; }
	
	public void setNumero ( int i ) 
	{this.numero = i ;}

	public boolean getJouee() {
		return this.jouee;
		
	}
    protected int getNumero() {return this.numero;}
    
}