package poo.polinomi;

import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class PolinomioSet extends PolinomioAstratto {

	private Set<Monomio> monomi = new TreeSet<>(); 
	
	@Override
	public int size() {
		return this.monomi.size();
	}//size
	
	@Override
	public void add(Monomio m) {
		if(m.getCOEFF() == 0)
			return;
		Iterator<Monomio> it = this.monomi.iterator();
		Monomio ms = null;
		while(it.hasNext()) {
			Monomio mc = it.next();
			if(mc.equals(m)) {
				it.remove();
				ms = mc;
				break;
			}//if
		}//while
		if(ms != null) {
			ms = ms.add(m);
			if(ms.getCOEFF() != 0)
				this.monomi.add(ms);
		}//if
		else {
			this.monomi.add(m);
		}//else
	}//add

	@Override
	public Polinomio crea() {
		return new PolinomioSet();
	}//crea

	@Override
	public java.util.Iterator<Monomio> iterator() {
		return this.monomi.iterator();
	}//iterator

}//PolinomioSet
