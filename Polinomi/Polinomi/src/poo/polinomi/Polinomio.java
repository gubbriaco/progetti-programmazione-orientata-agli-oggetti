package poo.polinomi;

import java.util.Iterator;


public interface Polinomio extends Iterable<Monomio> {

	default int size() {
		int c = 0;
		for(Iterator<Monomio> it = iterator(); it.hasNext(); it.next(), c++);
		return c;
	};
	
	void add(Monomio m);
	
	Polinomio crea();
	
	default Polinomio add(Polinomio p) {
		Polinomio somma = crea();
		for(Monomio m:this)
			somma.add(m);
		for(Monomio m:p)
			somma.add(m);
		return somma;
	};
	
	default Polinomio mul(Monomio m) {
		Polinomio prodotto = crea();
		for(Monomio x:this)
			prodotto.add(m.mul(x));
		return prodotto;
	};
	
	default Polinomio mul(Polinomio p) {
		Polinomio prodotto = crea();
		for(Monomio m:this)
			prodotto = prodotto.add(p.mul(m));
		return prodotto;
	};
	
	default Polinomio derivata() {
		Polinomio derivato = crea();
		for(Monomio m:this)
			if(m.getGRADO() > 0)
				derivato.add(new Monomio(m.getCOEFF()*m.getGRADO(), m.getGRADO()-1));
		return derivato;
	};
	
	default double valore(double x) {
		double v = 0.0D;
		for(Monomio m:this)
			v = v + m.getCOEFF()*Math.pow(x, m.getGRADO());
		return v;
	};	
	
}//Polinomio
