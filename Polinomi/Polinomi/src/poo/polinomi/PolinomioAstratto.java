package poo.polinomi;

import java.util.Iterator;

public abstract class PolinomioAstratto implements Polinomio {

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(300);
		Iterator<Monomio> it = this.iterator();
		boolean flag = true;
		while(it.hasNext()) {
			Monomio m = it.next();
			if(m.getCOEFF() > 0 && !flag)
				sb.append('+');
			sb.append(m);
			if(flag)
				flag = !flag;
		}//while
		return sb.toString();
	}//toString
	
	@Override
	public boolean equals(Object x) {
		if(this == x)
			return true;
		if(!(x instanceof Polinomio))
			return false;
		Polinomio p = (Polinomio)x;
		if(this.size() != p.size())
			return false;
		Iterator<Monomio> i1 = this.iterator();
		Iterator<Monomio> i2 = p.iterator();
		while(i1.hasNext()) {
			Monomio m1 = i1.next();
			Monomio m2 = i2.next();
			if(m1.getCOEFF() != m2.getCOEFF() && m1.getGRADO() != m2.getGRADO())
				return false;
		}//while
		return true;
	}//equals
	
	@Override
	public int hashCode() {
		final int M = 83;
		int h = 0;
		for(Monomio m:this)
			h = h*M+(String.valueOf(m.getCOEFF()) + String.valueOf(m.getGRADO())).hashCode();
		return h;
	}//hashCode
	
}//PolinomioAstratto
