package poo.math;

import java.util.Iterator;

public abstract class AbstractBigInt implements BigInt {

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> it = this.iterator();
		while(it.hasNext()) {
			int daAgg = it.next();
			sb.append(daAgg);
		}//while
		return sb.toString();
	}//toString
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof BigInt))
			return false;
		BigInt b = (BigInt)o;
		if(this.length() != b.length())
			return false;
		Iterator<Integer> it1 = this.iterator();
		Iterator<Integer> it2 = b.iterator();
		while(it1.hasNext()) {
			int t1 = it1.next();
			int b2 = it2.next();
			if(t1 != b2)
				return false;
		}//while
		return true;
	}//equals
	
	@Override
	public int hashCode() {
		final int M = 83;
		int h = 0;
		for(Integer i:this)
			h = h*M + (i).hashCode();
		return h;
	}//hashCode
	
}//AbstractBigInt
