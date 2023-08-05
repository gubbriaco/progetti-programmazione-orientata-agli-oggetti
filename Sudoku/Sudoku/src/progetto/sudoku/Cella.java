package progetto.sudoku;

public class Cella {

	private int i, j;
	
	public Cella(int i, int j) {
		if(i < 0 || i > 8 || j < 0 || j > 8)
			throw new IllegalArgumentException();
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return this.i;
	}//getI
	
	public int getJ() {
		return this.j;
	}//getJ
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(this.i);
		sb.append(",");
		sb.append(this.j);
		sb.append(">");
		return sb.toString();
	}//toString
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(this instanceof Cella))
			return false;
		Cella ps = (Cella)o;
		return this.i == ps.i && this.j == ps.j;
	}//equals
	
	@Override
	public int hashCode() {
		final int M = 83;
		int h = 0;
		h = h*M + this.i;
		h = h*M + this.j;
		return h;
	}//hashCode
	
}//PuntoScelta
