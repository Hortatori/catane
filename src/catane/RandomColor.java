package catane;
import java.awt.Color ;
import java.util.Random;

public class RandomColor{
	Color c ;
	public RandomColor() {
		
		Random  rd = new Random(3);
        int r = rd.nextInt();
        int g = rd.nextInt();
        int b = rd.nextInt();
        this.c = new Color (100 * r, 100 *g, 100*b);
        
	}

	
	
	
}
