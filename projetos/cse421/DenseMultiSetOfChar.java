import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

/**
 * A set of characters allowing for duplicate characters to be added.
 * 
 * @convention A MultiSetOfChar
 * @see MultiSetOfChar
 * @correspondence uses a map of <Character, Integer> in order to keep track of
 *                 the various Characters added to the MultiSet and their
 *                 associated occurrences. Also, uses an int to keep track of
 *                 the total number of items within the set.
 * @constraint this.total >= |this| >= 0
 * @constraint this.total <= Integer.MAX_INTEGER
 * @author Nathan Newman
 * 
 */
public class DenseMultiSetOfChar implements MultiSetOfChar {

	/**
	 * Map used to keep track of the Characters within the MultiSet and their
	 * associated occurrences.
	 */
	private Map<Character, Integer> charMap = new TreeMap<Character, Integer>();

	/**
	 * Int used to keep track of the total number of times any character is
	 * added to the MultiSet.
	 */
	private int totalContents;

	/**
	 * Static int used in initializations. Although 1 is not a 'magic number' if
	 * the initial value is changed then it only needs to be changed once.
	 */
	private static final Integer INITIAL = 1;

	/**
	 * Zero-argument constructor that creates an empty MultiSet.
	 */
	public DenseMultiSetOfChar() {

	}

	/**
	 * Constructor that creates a MultiSet containing only the passed in
	 * Character.
	 * 
	 * @param c
	 *            - a valid Character
	 */
	public DenseMultiSetOfChar(Character c) {
		charMap.put(c, DenseMultiSetOfChar.INITIAL);
		totalContents = DenseMultiSetOfChar.INITIAL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getCardinality() {
		return totalContents;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getElementCount(char target) {
		int i = 0;
		// if target is in the MultiSet then return the number of times it
		// occurs within the MultiSet. Else, return zero.
		if (charMap.containsKey(target)) {
			i = charMap.get(target);
		}
		return i;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<Character> getElementSet() {
		Set<Character> keySet = charMap.keySet();
		return keySet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void add(char item) {
		// If the totalContents is less than Integer.MAX_VALUE then increase it
		// by 1 since a Character was added to the MultiSet. Else, leave it
		// alone since it has reached its max value, this helps to avoid
		// unwanted MAX_INTEGER errors.
		if (totalContents < Integer.MAX_VALUE) {
			// If item is within the MultiSet then increase its count by 1.
			if (charMap.containsKey(item)) {
				Integer i = charMap.get(item);
				charMap.remove(item);
				charMap.put(item, i + 1);
			} else {
				// Else, add it to the MultiSet with a count starting at 1.
				charMap.put(item, DenseMultiSetOfChar.INITIAL);
			}
			totalContents++;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean remove(char target) {
		// If target is in the MultiSet then remove one occurrence of it and
		// return true.
		if (charMap.containsKey(target)) {
			Integer i = charMap.get(target);
			charMap.remove(target);
			// If the number of occurrences of target > 0 than reduce it and add
			// it back. Else, it only had one occurrence left and therefore the
			// last occurrence has been removed.
			if (i - 1 > 0) {
				charMap.put(target, i - 1);
			}
			// Reduce totalContents by 1 to account for the removal of one item
			// from the MultiSet.
			totalContents--;
			return true;
		}

		// Else, nothing was removed, thus return false.
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final char randomUniformChoose() {
		// First, a List of Character is created and a set of key values is
		// created from this.charMap. Then using an iterator, the List of
		// Character is populated with the various Characters within the key set
		// according to how many times they occur within this.charMap. Finally,
		// a random number between 0 and List.size - 1 and that number is used
		// as an index into the List to choose a Character.
		List<Character> multiSetList = new ArrayList<Character>();
		Set<Character> keySet = charMap.keySet();
		Iterator<Character> listIt = keySet.iterator();
		while (listIt.hasNext()) {
			Character c = listIt.next();
			int i = charMap.get(c);
			while (i > 0) {
				multiSetList.add(c);
				i--;
			}
		}
		Random gen = new Random();
		int randNumb = gen.nextInt(multiSetList.size());
		return multiSetList.get(randNumb);
	}

}
