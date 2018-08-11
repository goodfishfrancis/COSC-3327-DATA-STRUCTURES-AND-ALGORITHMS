package dartboard;
import java.util.Set;

public interface Dartboard
{
	//part of post: getNumbers().contains(rv)
	public int getTopmostNumber();
	//part of pre: getNumbers().contains(number);
	//part of post: getNumbers().contains(rv)
	public int getClockwiseAdjacentNumber(int number);
	public Set<Integer> getNumbers();
}