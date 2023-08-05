package poo.math;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.math.BigInteger;

public class BigIntLL extends AbstractBigInt {

	LinkedList<Integer> ll = new LinkedList<>();
	String UNSIGNED_INT="([0-9]+)";

	public BigIntLL() {
		this.ll = new LinkedList<>();
	}
	
	public BigIntLL(String s) {
		if(s.matches(UNSIGNED_INT)) {
			if(!zero(s)) {
				if(s.length() == 1 && s.equals("0"))
					this.ll.add(Integer.parseInt(s));
				boolean flag = true;
				for(int i=0;i<s.length();++i) {
					if(Integer.parseInt("" + s.charAt(i)) == 0 && flag)
						continue;
					else {
						this.ll.add(Integer.parseInt("" + s.charAt(i)));
						flag = false;
					}//else
				}//for
			}//if
			else
				this.ll.add(0);
		}//if
		else {
			throw new IllegalArgumentException("Numero non valido!");
		}//else
	}
	private boolean zero(String s) {
		if(Integer.parseInt("" + s.charAt(0))!= 0)
				return false;
		int c = 0;
		for(int i=0;i<s.length();++i)
			if(Integer.parseInt("" + s.charAt(i)) == 0)
				c++;
		if(c == s.length())
			return true;
		return false;
	}//zero
	 
	
	public BigIntLL(int x) {
		this(String.valueOf(x));
	}
	
	public BigIntLL(BigIntLL b) {
		Iterator<Integer> i1 = b.iterator();
		while(i1.hasNext()) {
			int daAgg = i1.next();
			this.ll.add(daAgg);
		}//while
	}

	@Override
	public BigInt factory(int x) {
		if(x < 0)
			throw new IllegalArgumentException("Intero negativo!");
		return new BigIntLL(x);
	}//factory
	
	@Override
	public BigInt add(BigInt x) {
		BigIntLL ret = new BigIntLL();
		BigIntLL b = new BigIntLL((BigIntLL)x);
		ListIterator<Integer> li1 = this.ll.listIterator(this.ll.size()), li2 = b.ll.listIterator(b.ll.size());
		int sommaCorr = 0;
		int riporto = 0;
		if(this.ll.size() >= b.ll.size()) {
			while(li1.hasPrevious()) {
				if(!li2.hasPrevious()) {
					while(li1.hasPrevious()) {
						int daAgg = li1.previous();
						daAgg += riporto;
						if(String.valueOf(daAgg).length() == 2) {
							ret.ll.addFirst(Integer.parseInt("" + String.valueOf(daAgg).charAt(1)));
							riporto = Integer.parseInt("" + String.valueOf(daAgg).charAt(0));
						}//if
						else {
							ret.ll.addFirst(daAgg);
							riporto = 0;
						}//else
					}//while
					if(riporto > 0)
						ret.ll.addFirst(riporto);
					return ret;
				}//if
				int e1 = li1.previous();
				int e2 = li2.previous();
				int somma = riporto + e1 + e2;
				if(String.valueOf(somma).length() == 2) {
					sommaCorr = Integer.parseInt("" + String.valueOf(somma).charAt(1));
					ret.ll.addFirst(sommaCorr);
					riporto = Integer.parseInt("" + String.valueOf(somma).charAt(0));
				}//if
				else {
					ret.ll.addFirst(somma);
					riporto = 0;
				}//else
			}//while
			if(riporto > 0)
				ret.ll.addFirst(riporto);
		}//if
		else {
			return b.add(this);
		}//else
		return ret;
	}//add
	
	@Override
	public BigInt sub(BigInt x) {
		if(this.compareTo(x) == 0)
			return new BigIntLL("0");
		BigIntLL b = new BigIntLL((BigIntLL)x);
		BigIntLL ret = new BigIntLL();
		ListIterator<Integer> li1 = this.ll.listIterator(this.ll.size()),  li2 = b.ll.listIterator(b.ll.size());
		if(this.compareTo(x) < 0)
			throw new IllegalArgumentException("Impossibile effettuare la sottrazione di un BigInt this minore del BigInt s");
		else {
			int sottr = 0;
			int daTogl = 0;
			while(li1.hasPrevious()) {
				if(!li2.hasPrevious()) {
					while(li1.hasPrevious()) {
						int daAgg = li1.previous();
						daAgg -= daTogl;
						if(daAgg < 0) {
							daAgg += 10;
							daTogl = 1;
							ret.ll.addFirst(daAgg);
						}//if
						ret.ll.addFirst(daAgg);
						daTogl = 0;
					}//while
					Iterator<Integer> it = ret.ll.iterator();
					while(it.hasNext()) {
						int e1 = it.next();
						if(e1 == 0) {
							it.remove();
							it = ret.ll.iterator();
						}//if
						else
							break;
					}//while
					return ret;
				}//if
				int e1 = li1.previous();
				int e2 = li2.previous();
				e1 -= daTogl;
				if(e1 < e2) {
					e1 += 10;
					daTogl = 1;
					sottr = e1 - e2;
					ret.ll.addFirst(sottr);
				}//if
				else {
					sottr = e1 - e2;
					daTogl = 0;
					ret.ll.addFirst(sottr);
				}//else
			}//while
		}//else
		Iterator<Integer> it = ret.ll.iterator();
		while(it.hasNext()) {
			int e1 = it.next();
			if(e1 == 0) {
				it.remove();
				it = ret.ll.iterator();
			}//if
			else
				break;
		}//while
		return ret;
	}//sub
	
	@Override
	public BigInt mul(BigInt x) {
		if(this.compareTo(new BigIntLL("1")) == 0)
			return x;
		if(x.compareTo(new BigIntLL("1")) == 0)
			return this;
		BigIntLL ret = new BigIntLL("0");
		if(this.compareTo(ret) == 0 || x.compareTo(ret) == 0)
			return ret;
		BigIntLL b = new BigIntLL((BigIntLL)x);
		if(this.ll.size() >= b.ll.size()) {
			ListIterator<Integer> li2 = b.ll.listIterator(b.ll.size());
			int i = 0;
			int riporto = 0;
			int prod = 0;
			int prodCorr = 0;
			while(li2.hasPrevious()) {
				BigInt daAdd;
				int e2 = li2.previous();
				LinkedList<Integer> list = new java.util.LinkedList<>();
				ListIterator<Integer> li1 = this.ll.listIterator(this.ll.size());
				riporto = 0;
				prod = 0;
				while(li1.hasPrevious()) {
					int e1 = li1.previous();
					prodCorr = riporto + (e1 * e2);
					if(String.valueOf(prodCorr).length() == 2) {
						prod = Integer.parseInt("" + String.valueOf(prodCorr).charAt(1));
						riporto = Integer.parseInt("" + String.valueOf(prodCorr).charAt(0));
						list.addFirst(prod);
						if(!li1.hasPrevious())
							list.addFirst(riporto);
					}//if
					else {
						list.addFirst(prodCorr);  
						riporto = 0;
					}//else
				}//while
				for(int j=0;j<i;++j) {
					list.addLast(0);
				}//for
				StringBuilder sb = new StringBuilder();
				for(int w=0;w<list.size();++w) {
					sb.append(list.get(w));
				}//for
				daAdd = new BigIntLL(sb.toString());
				ret = (BigIntLL) ret.add(daAdd);
				i++;
			}//while
		}//if
		else {
			return b.mul(this);
		}//else
		return ret;
	}//mul
	
	@Override
	public int compareTo(BigInt b) {
		if(this.length() > b.length())
			return 1;
		if(this.length() < b.length())
			return -1;
		if(this.length() == b.length()) {
			Iterator<Integer> i1 = this.iterator(), i2 = b.iterator();
			while(i1.hasNext()) {
				int e1 = i1.next();
				int e2 = i2.next();
				if(e1 > e2)
					return 1;
				if(e1 < e2)
					return -1;
			}//while
		}//if
		return 0;
	}//compareTo
	
	@Override
	public Iterator<Integer> iterator() {
		return this.ll.iterator();
	}//iterator
		

	public static void main(String...args) {
		BigInteger b = new BigInteger("2");
		BigInteger p = b.pow(128);
		System.out.println("2^128=" + p);
		BigInt bigInt = new BigIntLL("2");
		BigInt ris = bigInt.pow(128);
		System.out.println("2^128=" + ris);
	}//main
	
}//BigIntLL
