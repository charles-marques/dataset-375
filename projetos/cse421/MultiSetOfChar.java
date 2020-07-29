import java.util.Set;

/**
 * @mathmodel b is a finite multiset
 * @mathdef |b| is the cardinality of b <br />
 *          ||c,b|| is the number of occurrences of element c in b
 * @constraint (forall i in b : i is a char) <br />
 *             |b| <= Integer.MAX_VALUE
 * @initially b is the empty multiset (for zero-argument constructor) <br />
 *            b = {c} (for a one-argument constructor taking a char argument, c)
 */
interface MultiSetOfChar {

	/**
	 * Returns the number of elements in this multiset (ie its cardinality).
	 * Note that since multisets can include duplicates, the cardinality may be
	 * larger than the number of distinct elements. Also, the total number of
	 * items in the multiset is bounded above by Integer.MAX_VALUE.
	 * 
	 * @return |b|, ie the cardinality of b
	 */
	int getCardinality();

	/**
	 * Returns the number of occurrences of a gven element in the multiset. A
	 * simple identity relating getElementCount and getCardinality is that the
	 * sum of getElementCount for each char is equal to the cardinality of the
	 * set.
	 * 
	 * @param target
	 *            char to be counted in the multiset
	 * @return ||target,b||, ie the number of occurrences of target in b
	 */
	int getElementCount(char target);

	/**
	 * Returns a set such that every element in the multiset is in the set (but
	 * no duplicates). The cardinality of the returned set must be less than or
	 * equal to |b|. The cardinality of the two are equal if and only if b
	 * contains no duplicate elements.
	 * 
	 * @return a set of Character, s, such that: <br />
	 *         (for all (Character)i in s : (char)i in b) and <br />
	 *         (for all (char)i in b : (Character)i in s)
	 */
	Set<Character> getElementSet();

	/**
	 * Adds a single element to the multiset. This operation always increases
	 * the cardinality of the multiset by 1, assuming that the maximum capacity
	 * of Integer.MAX_VALUE has not been reached.
	 * 
	 * @param item
	 *            the char to be added to b
	 * @requires |b| < Integer.MAX_VALUE
	 * @alters b
	 * @ensures b = #b union {item}
	 */
	void add(char item);

	/**
	 * Removes the target, if it is present in the multiset. The method returns
	 * true if and only if it changes the multiset. Note that this method
	 * removes only a <em>single</em> instance of the target. Thus, assuming
	 * the target is in the multiset, this method decreases the cardinality of
	 * the multiset by one.
	 * 
	 * @param target
	 *            the char to be removed
	 * @alters b
	 * @ensures (target not in #b) ==> (b = #b) <br />
	 *          (target in #b) ==> (b union {target} = #b)
	 * @return target in #b
	 */
	boolean remove(char target);

	/**
	 * Returns a char chosen randomly based on the contents of the multiset.
	 * This operation does not remove the char from the multiset or change the
	 * multiset in any way. In particular, the cardinality of the multiset is
	 * the same before and after this method.
	 * 
	 * <p>
	 * Characters should be returned with a random distribution equal to the
	 * distribution of characters in the multiset. That is, for a character that
	 * appears N times in a multiset of cardinality M, the probability of that
	 * character being returned is N / M. For example, a multiset that contains
	 * only the character 'a', possibly many times, would always result in an
	 * 'a' being generated. On the other hand, a multiset with an equal number
	 * of 'a' and 'b' elements would return an 'a' approximately half the time
	 * and a 'b' the other half.
	 * 
	 * @requires |b| >= 1
	 * @return char c with probability p, where: <br />
	 *         p = ||c,b|| / |b|
	 */
	char randomUniformChoose();
}
