package poo.math;

import java.util.Iterator;

public interface BigInt extends Comparable<BigInt>, Iterable<Integer> {

	default String value() {
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> it = this.iterator();
		while(it.hasNext()) {
			int daAgg = it.next();
			sb.append(daAgg);
		}//while
		return sb.toString();
	};
	
	default int length() {
		int c = 0;
		for(@SuppressWarnings("unused") Integer x: this)
			c++;
		return c;
	};
	
	 BigInt factory( int x );
	
	default BigInt incr() {
		return this.add(factory(1));
	};
	
	default BigInt decr() {
		if(this.compareTo(factory(0)) == 0)
			throw new ArithmeticException("Impossibile effettuare il decremento di un numero pari a zero!");
		return this.sub(factory(1));
	};
	
	BigInt add( BigInt a );
	
	BigInt sub( BigInt s );
	
	 default BigInt mul( BigInt m ) {
		BigInt ret = new BigIntLL((BigIntLL)this);
		BigInt c = factory(0);
		while(c.compareTo(m) < 0) {
			ret.add(this);
			c.incr();
		}//while
		return ret;
	 };
	 
	default BigInt div( BigInt d ) {
		if(this.compareTo(d) < 0)
			throw new IllegalArgumentException("Impossibile dividere per un numero maggiore di this!");
		if(this.compareTo(factory(0)) == 0)
			return factory(0);
		if(d.compareTo(factory(0)) == 0)
			throw new IllegalArgumentException("Impossibile dividere per zero");
		BigInt ret = factory(1);
		while(this.compareTo(ret.mul(d)) >= 0) {
			ret = ret.incr();
		}//while
		ret = ret.decr();
		return ret;
	};
	
	default BigInt rem( BigInt d ) {
		if(this.compareTo(d) < 0)
			throw new IllegalArgumentException("Impossibile calcolare il resto di una divisione dove this < d!");
		BigInt ret = factory(0);
		BigInt sottraendo = d.mul(this.div(d));
		ret = this.sub(sottraendo);
		return ret;
	};
	
	default BigInt pow( int exponent ) {
		if(exponent == 0)
			return factory(1);
		BigInt ret = new BigIntLL((BigIntLL)this);
		for(int i=1;i<exponent;++i)
			ret = ret.mul(this);
		return ret;
	};
	
}//BigInt
