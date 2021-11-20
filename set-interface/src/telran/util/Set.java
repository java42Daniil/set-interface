package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;

public interface Set<T> extends Iterable<T>{
/**
 * adds new object
 * @param element
 * @return true if added, otherwise false (set doesn't include equaled objects)
 */
	boolean add(T obj);
	/**
	 * removes object equaled a pattern
	 * @param pattern
	 * @return reference to removed object or null if no equaled object exists
	 */
	T remove(T pattern);
	
	/**
	 * 
	 * @param pattern
	 * @return true if a set contains an object equaled a given pattern, otherwise false
	 */
	 boolean contains(T pattern) ;
	/** 
	 * 
	 * @return amount of the existing objects
	 */
	int size() ;
	/**
	 * removes all objects matching a given predicate
	 * @param predicate
	 * @return true if at least one object has been removed
	 */
	default boolean removeIf(Predicate<T> predicate) {
		int oldSize = size();
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			if(predicate.test(it.next())) {
				it.remove();
			}
		}
		return oldSize > size();
	}
	/** 
	 * removes all objects
	 */
	default void clear() {
		removeIf(e -> true);
	}
	
	/**
	 * removes all objects contained in an other set
	 * @param other
	 * @return
	 */
	default boolean removeAll(Set<T> other) {
		return removeIf(e -> other.contains(e));
	}
	/**
	 * removes all objects not contained in an other set
	 * @param other
	 * @return
	 */
	default boolean retainAll(Set<T> other) {
		return removeIf(e -> !other.contains(e));
	}
	
}