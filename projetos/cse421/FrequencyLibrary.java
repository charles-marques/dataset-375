import java.util.List;

/**
 * An object used to hold a library of books and their respective MultiSets
 * representing the characters and associated frequencies within the books.
 * 
 * @constraint |FrequencyLibrary| > 0
 * 
 * @initially |FrequencyLibrary| = 0
 * 
 * @author Interface used from CSE 421 lab 7.3, Documentation by Nathan Newman
 * 
 */
interface FrequencyLibrary {

	/**
	 * A method used to return the number of books within the library.
	 * 
	 * @requires |FrequencyLibrary| >= 0
	 * 
	 * @ensures the total number of books in the library are returned
	 * 
	 * @return an integer representing the number of books within the library
	 */
	int size();

	/**
	 * A method used to determine whether or not the passed in title is within
	 * the library.
	 * 
	 * @requires the passed in object is a String
	 * 
	 * @ensures if {the passed in title is in the library then return true} else
	 *          {return false}
	 * 
	 * @param target
	 *            a valid string representing a work possibly in the library
	 * 
	 * @return a boolean value representing whether or not the passed in title
	 *         is within the library
	 */
	boolean contains(String target);

	/**
	 * A method that takes in a string representing a title within the library
	 * and it returns a MultiSetOfChar representing the characters within the
	 * book and their occurrences. If the title of the book is not found then
	 * null is returned.
	 * 
	 * @requires the passed in object is a String and target is in the library
	 * 
	 * @ensures if {target is in the library then a MultiSetOfChar is returned
	 *          representing the characters within the work and their associated
	 *          counts} else {null is returned}
	 * 
	 * @param target
	 *            a valid string representing a work possibly in the library
	 * 
	 * @return a MultiSetOfChar that represents the characters within the work
	 *         and their associated counts
	 */
	MultiSetOfChar getFrequencies(String target);

	/**
	 * A method used to add one more occurrence of the passed in element to the
	 * MultiSet representing the work associated with the passed in title. If
	 * the given work does not have a MultiSet yet then this creates an empty
	 * set and adds one occurrence of the passed in element to it.
	 * 
	 * @requires true (otherwise, can be called on any title with any character,
	 *           and on an empty library)
	 * 
	 * @ensures if {there exists a MultiSetOfChar associated with the passed in
	 *          title then add one occurrence of the passed in element to it}
	 *          else if {there does not exist a MultiSet associated with the
	 *          passed in title then create an empty set and add one occurrence
	 *          of the passed in element to it} else {the title is not in the
	 *          library then add the title to the library and create an empty
	 *          set and add one occurrence of the passed in element to it} and
	 *          |MultiSet| = |#MultiSet| + 1
	 * 
	 * @param name
	 *            a valid string representing a work possibly in the library
	 * @param element
	 *            a character within the work
	 */
	void add(String name, char element);

	/**
	 * A method used to remove a single occurrence of an element from the
	 * MultiSet representing the work passed in. If this method removes the last
	 * element in the MultiSet then the work is removed from the library. The
	 * boolean returned represents whether or not the passed in element was
	 * actually removed, thus it returns true iff the element passed in was
	 * present within the MultiSet and one occurrence was removed.
	 * 
	 * @requires name is in the library and element is in the associated
	 *           MultiSet (or |MultiSet| > 0)
	 * 
	 * @ensures if {|#MultiSet| = 1 then the work is removed from the library}
	 *          else {a single occurrence of the element is removed from the
	 *          MultiSet} and |MultiSet| = |#MultiSet| - 1
	 * 
	 * @param name
	 *            a valid string representing a work within the library
	 * @param element
	 *            a valid character within the MultiSet
	 * 
	 * @return a boolean representing whether or not the element was removed
	 *         from the MultiSet
	 */
	boolean remove(String name, char element);

	/**
	 * A method that returns a character chosen randomly based on the contents
	 * of the MultiSet associated with the passed in name. This method does not
	 * remove the element from the Multset or alter the contents of the MultiSet
	 * in anyway.
	 * 
	 * @requires name is in the library and has an associated MultiSet s.t.
	 *           |MultiSet| > 0
	 * 
	 * @ensures the character returned is determined in a way s.t. it
	 *          probability of being chosen is: let n be the number of
	 *          occurrences of element within the MultiSet, then its probability
	 *          p = n / |MultiSet|
	 * 
	 * @param name
	 *            a valid string representing a work within the library
	 * 
	 * @return a character chosen randomly from the MultiSet using the formula
	 *         in the ensure clause
	 */
	char randomUniformChoose(String name);

	/**
	 * A method used to return a random title from the library.
	 * 
	 * @requires |FrequencyLibrary| > 0
	 * 
	 * @ensures if |FrequencyLibrary| > 0 then title returned is a randomly
	 *          selected title from library, else an empty string is returned.
	 * 
	 * @return a randomly selected string from the library
	 */
	String getRandomTitle();
}
