package poo.stack;

import java.util.*;

public abstract class StackAstratto<T> implements Stack<T>{

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext())
				sb.append(", ");
		}//while
		sb.append("]");
		return sb.toString();
	}//toString
	
}//StackAstratto
