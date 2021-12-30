package catane;

public class Case {
	Sommet NO;
	Sommet NE;
	Sommet SO;
	Sommet SE;
	Paysage type;
	int numero;

	public void setType(Paysage t) {
		this.type = t;
	}

	public void setNumber(int i) {
		this.numero = i;
	}

	public Case(Sommet nO, Sommet nE, Sommet sO, Sommet sE) {
		super();
		NO = nO;
		NE = nE;
		SO = sO;
		SE = sE;
	}

	public String toString() {
		return this.type.toString() + " " + this.numero;
	}

}
