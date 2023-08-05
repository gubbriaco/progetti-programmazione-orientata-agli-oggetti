package poo.stack;

import java.util.*;

public interface Stack<T> extends Iterable<T> {

	int size();
	default void clear() {
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}//while
	};
	
	void push(T x);
	
	default T pop() {
		Iterator<T> it = iterator();
		T x = it.next();
		it.remove();
		return x;
	};
	
	default T peek() {
		Iterator<T> it = iterator();
		T x = it.next();
		return x;
	};
	
	default boolean isEmpty() {
		return size() == 0;
	};
	
	default boolean isFull() {
		return false;
	};
	
}//Stack
