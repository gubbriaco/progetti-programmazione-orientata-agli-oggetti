package poo.polinomi;

public class Monomio implements Comparable<Monomio> {

	private final int COEFF, GRADO;
	
	public Monomio(final int COEFF, final int GRADO) {
		if(GRADO < 0)
			throw new IllegalArgumentException();
		this.COEFF = COEFF;
		this.GRADO = GRADO;
	}
	
	public Monomio(Monomio m) {
		this.COEFF = m.COEFF;
		this.GRADO = m.GRADO;
	}
	
	public int getCOEFF() {
		return this.COEFF;
	}//getCOEFF
	
	public int getGRADO() {
		return this.GRADO;
	}//getGRADO
	
	public Monomio add(Monomio m) {
		if(!this.equals(m))
			throw new RuntimeException("Monomi non simili!");
		return new Monomio(this.COEFF + m.COEFF, this.GRADO);
	}//add
	
	public Monomio mul(int s) {
		return new Monomio(this.COEFF * s, this.GRADO);
	}//mul
	
	public Monomio mul(Monomio m) {
		return new Monomio(this.COEFF * m.COEFF, this.GRADO + m.GRADO);
	}//mul
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(30);
		if(this.COEFF == 0)
			sb.append(0);
		else {
			if(this.COEFF < 0)
				sb.append('-');
			if(Math.abs(this.COEFF) != 1 || this.GRADO == 0)
				sb.append(Math.abs(this.COEFF));
			if(this.COEFF != 0 && this.GRADO > 0)
				sb.append('x');
			if(this.COEFF != 0 && this.GRADO > 1) {
				sb.append('^');
				sb.append(this.GRADO);
			}//if
		}//else
		return sb.toString();
	}//toString
	
	@Override
	public boolean equals(Object x) {
		if(x == this) //sono in aliasing
			return true;
		if(!(x instanceof Monomio))
			return false;
		Monomio m = (Monomio)x;
		return this.GRADO == m.GRADO;
	}//equals
	
	@Override
	public int hashCode() {
		return GRADO;
	}//hashCode
	
	@Override
	public int compareTo(Monomio m) {
		if(this.GRADO > m.GRADO)
			return -1;
		if(this.equals(m))
			return 0;
		return 1;
	}//compareTo
	
	
	
	public static void main(String...args) {
		
		Monomio m1 = new Monomio(3,0);
		Monomio m2 = new Monomio(-4,2);
		Monomio m3 = m1.mul(m2);
		m3 = m3.add(m2);
		m3 = m3.mul(-1);
		System.out.println("m1 = "+m1);
		System.out.println("m2 = "+m2);
		System.out.println("m3 = "+m3);	
		
	}//main
	
}//Monomio
