package poo.stack;

import java.util.*;

public class StackConcatenato<T> extends StackAstratto<T> {

	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}//Nodo
	
	private Nodo<T> first = null;
	private int size = 0;
	
	@Override
	public int size() {
		return size;
	}//size
	
	@Override
	public void push(T x) {
		Nodo<T> nuovo = new Nodo<>();
		nuovo.info = x;
		nuovo.next = first;
		first = nuovo;
		size++;
	}//push
	
	@Override
	public T pop() {
		if(first == null)
			throw new NoSuchElementException();
		T x = first.info;
		first = first.next;
		size--;
		return x;
	}//pop
	
	@Override
	public T peek() {
		if(first == null)
			throw new NoSuchElementException();
		T x = first.info;
		return x;
	}//peek
	
	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}//iterator
	
	private class StackIterator implements Iterator<T> {
		
		private Nodo<T> pre = null, cor = null;
		
		@Override
		public boolean hasNext() {
			if(cor == null)
				return first != null;
			return cor.next != null;
		}//hasNext
		
		@Override
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException();
			if(cor == null)
				cor = first;
			else {
				pre = cor;
				cor = cor.next;
			}//else
			return cor.info;
		}//next
		
		@Override
		public void remove() {
			if(pre == cor)
				throw new IllegalStateException();
			if(cor == first)
				first = first.next;
			else
				pre.next = cor.next;
			size--;
			cor = pre;
		}//remove
		
	}//StackIterator
	
}//StackConcatenato
