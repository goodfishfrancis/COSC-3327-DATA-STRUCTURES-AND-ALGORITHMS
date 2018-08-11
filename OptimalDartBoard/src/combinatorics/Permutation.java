package combinatorics;

import java.util.Set;


/**
 * @author kart
 *
 */
public interface Permutation<E> 
{
	public E getImage(E element);
	public E getPreImage(E element);
	public Set<E> getDomain();
}
