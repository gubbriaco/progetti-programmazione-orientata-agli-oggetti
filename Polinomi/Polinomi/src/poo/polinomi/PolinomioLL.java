package poo.polinomi;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;

public class PolinomioLL extends PolinomioAstratto {

	private LinkedList<Monomio> lista = new LinkedList<>();
	
	@Override
	public int size() {
		return this.lista.size();
	}//getSize
	
	@Override
	public PolinomioLL crea() {
		return new PolinomioLL();
	}//crea
	
	@Override
	public void add(Monomio m) {
		if(m.getCOEFF() == 0)
			return;
		ListIterator<Monomio> lit = this.lista.listIterator();
		boolean flag = false;
		while(lit.hasNext() && !flag) {
			Monomio mc = lit.next();
			if(mc.equals(m)) {
				Monomio nm = mc.add(m);
				if(nm.getCOEFF() == 0) {
					lit.remove();
				}//if
				else {
					lit.set(nm);
				}//else
				flag = true;
			}//if
			else if(mc.compareTo(m) > 0){
				lit.previous();
				lit.add(m);
				flag = true;
			}//else if
		}//while
		if(!flag) {
			lit.add(m);
		}//if
	}//add

	@Override
	public Iterator<Monomio> iterator() {
		return this.lista.iterator();
	}//iterator
	
}//PolinomioLL
