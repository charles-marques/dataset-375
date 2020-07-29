/**
 * A BigNatural is an integer greater than or equal to zero and unbounded above.
 * Therefore, this class allows for the use of arbitrarily large positive
 * numbers, that aren't bounded by the MAX_INTEGER value, and instead are only
 * "bounded" by the amount of available memory.
 * 
 * @mathmodel A NaturalNumber, n, is a sequence of Integers x, such that 0 <= x
 *            <= 9
 * 
 * @constraint n >= 0
 * 
 * @initially n = 0
 * @author Nathan Newman
 * 
 */
public interface BigNatural extends Comparable<BigNatural> {
	/**
	 * Adds one to the value of the natural number. With the given interface
	 * this is the only way to increase a natural number, since no addition or
	 * multiplication is available. A BigNatural is returned that is a copy of
	 * the initial BigNatural incremented by 1. Therefore, increment() allows
	 * for immutability.
	 * 
	 * @ensures this.copy = #this + 1 and this = #this
	 * 
	 * @return a copy of this s.t. this.copy = this + 1
	 */
	BigNatural increment();

	/**
	 * Subtracts one from the value of the natural number. With the given
	 * interface this is the only way to decrease a natural number, since no
	 * subtraction of division are available. A BigNatural is returned that is a
	 * copy of the initial BigNatural decremented by 1. Therefore, decrement()
	 * allows for immutability.
	 * 
	 * @ensures this.copy = #this - 1, or if #this = 0 then this.copy = 0, and
	 *          this = #this
	 * 
	 * @return a copy of this s.t. this.copy = this - 1
	 */
	BigNatural decrement();

	/**
	 * Returns the value of the natural number as a string representation. The
	 * representation is given in the format "123456789" with no dashes or
	 * commas. This representation is useful when you wish to print the natural
	 * number.
	 * 
	 * @ensures The value returned is a valid toString representation of this
	 * 
	 * @return A valid string representation of this
	 */
	String toString();

}
