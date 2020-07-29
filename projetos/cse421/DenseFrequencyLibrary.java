import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * An implementation of FrequencyLibrary using a List of strings to represent
 * the titles and a List of MultiSets to represent the MultiSetOfChars
 * associated with those titles. The Lists and subsequent methods are
 * implemented in such a way that if a title is in the titleList then there
 * exists a matching MultiSet of the same index in the multiSetList.
 * 
 * @convention a FrequencyLibrary
 * @see FrequencyLibrary
 * @correspondence if s, string, has index i in this.titleList then there exist
 *                 a non-empty MultiSetOfChar m associated with s at index i in
 *                 this.multiSetList
 * @constraint |this.titleList| = |this.multiSetList|
 * 
 * @author Nathan Newman
 * 
 */
public class DenseFrequencyLibrary implements FrequencyLibrary {

	/**
	 * A list used to keep track of the names of the books in the library.
	 */
	private List<String> titleList;

	/**
	 * A list used to keep track of the MultiSets associated with the books in
	 * the library.
	 */
	private List<MultiSetOfChar> multiSetList;

	/**
	 * Constructs a DenseFrequencyLibrary that contains no books.
	 */
	public DenseFrequencyLibrary() {
		titleList = new ArrayList<String>();
		multiSetList = new ArrayList<MultiSetOfChar>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int size() {
		return titleList.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean contains(String target) {
		return titleList.contains(target);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final MultiSetOfChar getFrequencies(String target) {
		// If target is a title within the library then return its associated
		// Multiset.
		if (titleList.contains(target)) {
			int indexOfTarget = titleList.indexOf(target);
			return multiSetList.get(indexOfTarget);
		}
		// Else return null.
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void add(String name, char element) {
		// If name is not in the library then add it to the library and create a
		// MultiSet with one occurrence of element.
		if (!titleList.contains(name)) {
			titleList.add(name);
			MultiSetOfChar newSet = new DenseMultiSetOfChar(element);
			multiSetList.add(newSet);
		} else {
			// Else name is in the library, so add one occurrence of element to
			// the existing MultiSet.
			int indexOfName = titleList.indexOf(name);
			multiSetList.get(indexOfName).add(element);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean remove(String name, char element) {
		boolean retVal = false;
		// If name is in the library then attempt to remove one occurrence of
		// element from its associated MultiSet.
		if (titleList.contains(name)) {
			int indexOfName = titleList.indexOf(name);
			retVal = multiSetList.get(indexOfName).remove(element);
			// If this removal results in the associated MultiSet to be empty
			// then remove the book and the MultiSet from the library.
			if (multiSetList.get(indexOfName).getCardinality() == 0) {
				titleList.remove(indexOfName);
				multiSetList.remove(indexOfName);
			}
		}
		// Else the name was not in the library so the removal was unsuccessful,
		// therefore return false.
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final char randomUniformChoose(String name) {
		// If name is in the library then call
		// this.multiSetList.get.randomUniformChoose.
		if (titleList.contains(name)) {
			int indexOfName = titleList.indexOf(name);
			return multiSetList.get(indexOfName).randomUniformChoose();
		}
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getRandomTitle() {
		String randString = "";
		if (titleList.size() > 0) {
			Random gen = new Random();
			int seed = gen.nextInt(titleList.size());
			randString = titleList.get(seed);
		}
		return randString;
	}

}

