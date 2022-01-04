package catane;

public  abstract class Carte {
    private int numero;
    private boolean jouee  = false ;
    private String effet ;
    
  
    
    public  void Jouer()  { if (jouee) { System.out.println("la carte est déjà jouée"); } 
    else { jouee = true ; 
    	actionCarte();
    	
    	
    }
    }

	protected abstract void actionCarte();
    
	public void setNumero ( int i ) 
	{this.numero = i ;}

	public boolean getJouee() {
		return this.jouee;
		
	}
    
    
}