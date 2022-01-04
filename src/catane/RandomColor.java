package catane;
import java.awt.Color ;
import java.util.Random;

public class RandomColor{
	Color c ;
	public RandomColor() {
		
		Random  rd = new Random();
        int r = rd.nextInt(3);
        int g = rd.nextInt(3);
        int b = rd.nextInt(3);
        System.out.println ( r ) ;
        System.out.println ( g ) ;
        System.out.println ( b ) ;
        this.c = new Color (100 * r, 100 *g, 100*b);
        
	}

	
	
	
}
