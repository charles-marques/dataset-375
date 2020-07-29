import java.util.Stack;

/**
 * Using a stack to represent the sequence of digits, allows for the
 * representation of an unbounded integer greater than or equal to zero. This
 * representation is determined by considering the top of the stack to be the
 * ones digit, the item under that to be the tens digit, etc.
 * 
 * @mathdef IsValidString implies string is of the form, "123456789", therefore
 *          containing only digits, no commas or dashes
 * 
 * @correspondence NaturalNumber, n = reverse (stackRep)
 * 
 * @convention this.stackRep.top = right most digit
 * 
 * @author Nathan Newman
 * 
 */
public class SlowBigNatural implements BigNatural {

	/**
	 * Stack used to represent the natural number following the convention.
	 */
	private final Stack<Integer> stackRep;

	/**
	 * Variable used to hold an important integer: 9.
	 */
	private static final int TESTVAL = 9;

	/**
	 * The hashCode initialization value.
	 */
	private static final int HASHCODESTART = 11;

	/**
	 * Used to hold the cached hashCode so it doesn't need to be generated
	 * everytime it needs to be used.
	 */
	private int hashCodeCache = 0;

	/**
	 * Private method used to eliminate unnecessary leading zeros. Some calls to
	 * decrement, specifically when the size of the stack (or the length of the
	 * string of digits) is decreased leave behind leading zeros at the at the
	 * front of the number (bottom of the stack). This method helps to clean
	 * those unnecessary zeros.
	 * 
	 * @requires this.stackRep.size > 0
	 * 
	 * @ensures #this = <0^i> * this where 0 <= i < this.stackRep.size [if there
	 *          are leading zeros, they are removed]
	 */
	private void cleanUp() {
		// First create a temporary object and then transfer the contents
		// of stackRep over, therefore the "front" of the number is the
		// top of the stack
		Stack<Integer> tempStack = new Stack<Integer>();
		int top;
		while (stackRep.size() > 0) {
			top = stackRep.pop();
			tempStack.push(top);
		}
		// While stackRep.top = 0, remove the top of the stack
		// Special case: when stackRep.top = 0 and stackRep.size = 1
		// then this = 0; therefore don't remove the top in
		// order to preserve this
		while ((tempStack.peek() == 0) && (tempStack.size() != 1)) {
			tempStack.pop();
		}
		// Transfer the remaining digits back to this.stackRep
		while (tempStack.size() > 0) {
			top = tempStack.pop();
			stackRep.push(top);
		}
	}

	/**
	 * Increment helper method used to allow for immutability.
	 * 
	 * @requires this >= 0
	 * 
	 * @ensures this = this + 1
	 */
	private void incHelper() {
		// First, check size of stackRep for recursive call
		// Second, if stackRep.top = 9, then stackRep.top -> 0
		// and then recursively call on this
		// Else, stackRep.top++
		if (stackRep.size() > 0) {
			int top = stackRep.pop();
			if (top == SlowBigNatural.TESTVAL) {
				top = 0;
				this.incHelper();
				stackRep.push(top);
			} else {
				top++;
				stackRep.push(top);
			}
			// Base Case, stackRep.size == 0, therefore there must have
			// been a 9 on the call before, thus add a leading 1
		} else {
			int top = 1;
			stackRep.push(top);
		}
	}

	/**
	 * Decrement helper method used to allow for immutability.
	 * 
	 * @requires this > 0
	 * 
	 * @ensures this = this - 1
	 */
	private void decHelper() {
		// First, check base case for recursive call
		// Second, if the stackRep.top = 1 and it is the only element
		// then decrement (this = 1 => this = 0)
		// Else, if stackRep.top = 0 and stackRep.size > 0 then
		// stackRep.top -> 9, and make recursive call on this
		if (stackRep.size() > 0) {
			int top = stackRep.pop();
			if ((top == 0) && (stackRep.size() == 0)) {
				stackRep.push(top);
			} else if ((top == 1) && (stackRep.size() == 0)) {
				top--;
				stackRep.push(top);
			} else if (top == 0) {
				int nine = SlowBigNatural.TESTVAL;
				top = nine;
				this.decHelper();
				stackRep.push(top);
			} else {
				top--;
				if ((stackRep.size() != 0) || (top != 0)) {
					stackRep.push(top);
				}
			}
		}
		// Call cleanUp() in order to remove unnecessary leading zeros
		if (!(stackRep.peek() != 0 && stackRep.size() == 1)) {
			this.cleanUp();
		}
	}

	/**
	 * Initially the NaturalNumber is 0.
	 * 
	 * @ensures this = 0
	 */
	public SlowBigNatural() {
		// Constructor that initializes the BigNatural to 0
		int zero = 0;
		stackRep = new Stack<Integer>();
		stackRep.push(zero);
	}

	/**
	 * Initializes the NaturalNumber to the value of the integer passed to it.
	 * 
	 * @param tempInt
	 *            -Integer value used to initialize the NaturalNumber
	 * 
	 * @requires 0 <= tempInt <= MAX_INTEGER
	 * 
	 * @ensures this = tempInt or if this < 0, this = 0
	 */
	public SlowBigNatural(int tempInt) {
		stackRep = new Stack<Integer>();
		if (tempInt > 0) {
			String tempString = Integer.toString(tempInt);
			for (int i = 0; i < tempString.length(); i++) {
				Character c = Character.valueOf(tempString.charAt(i));
				String s = c.toString();
				int temp = Integer.parseInt(s);
				stackRep.push(temp);
			}
		} else {
			int zero = 0;
			stackRep.push(zero);
		}
	}

	/**
	 * Initializes the NaturalNumber to the numerical value of the string passed
	 * to it.
	 * 
	 * @param tempString
	 *            -String representation of some number used to initialize the
	 *            NaturalNumber
	 * 
	 * @requires IsValidString(tempString)
	 * 
	 * @ensures this = numerical value of tempString
	 */
	public SlowBigNatural(String tempString) {
		stackRep = new Stack<Integer>();
		if (tempString.length() > 0) {
			for (int i = 0; i < tempString.length(); i++) {
				Character c = Character.valueOf(tempString.charAt(i));
				String s = c.toString();
				int temp = Integer.parseInt(s);
				stackRep.push(temp);
			}
		} else {
			int zero = 0;
			stackRep.push(zero);
		}
	}

	/**
	 * Initializes the NaturalNumber to the value of the NaturalNumber passed to
	 * it.
	 * 
	 * @param bigNat
	 *            - a properly formatted BigNatural
	 * 
	 * @requires bigNat is a NaturalNumber
	 * 
	 * @ensures this = bigNat
	 */
	public SlowBigNatural(BigNatural bigNat) {
		this(bigNat.toString());
	}

	/**
	 * Makes a copy of this and adds one to the value of the copied natural
	 * number. Returning a copy of the natural number allows for immutability.
	 * 
	 * @requires this >= 0
	 * 
	 * @ensures this.copy = this + 1 and this = #this
	 * 
	 * @return A copy of this s.t. this.copy = this + 1
	 */
	@Override
	public final SlowBigNatural increment() {
		SlowBigNatural temp = new SlowBigNatural(this);
		temp.incHelper();
		return temp;
	}

	/**
	 * Makes a copy of this and subtracts one from the value of the copied
	 * natural number. Returning a copy of the natural number allows for
	 * immutability.
	 * 
	 * @requires this > 0
	 * 
	 * @ensures this.copy = this - 1 and this = #this
	 * 
	 * @return A copy of this s.t. this.copy = this - 1
	 */
	@Override
	public final SlowBigNatural decrement() {
		SlowBigNatural temp = new SlowBigNatural(this);
		temp.decHelper();
		return temp;
	}

	/**
	 * Returns the value of the natural number as a string representation.
	 * 
	 * @requires this is a natural number
	 * 
	 * @ensures toString = IsValidString(this.stackRep)
	 * 
	 * @return a valid string representation of this.stackRep
	 */
	@Override
	public final String toString() {
		String stringRep = "", temp = "";
		Stack<Integer> tempStack = new Stack<Integer>();
		while (stackRep.size() > 0) {
			int top = stackRep.pop();
			temp = String.valueOf(top);
			stringRep = temp + stringRep;
			tempStack.push(top);
		}
		while (tempStack.size() > 0) {
			int top = tempStack.pop();
			stackRep.push(top);
		}
		return stringRep;
	}

	/**
	 * Compares two BigNaturals and returns an integer depending on how they are
	 * related. If this < bN then it returns a number < 0, if this > bN then it
	 * returns a number > 0, and if this = bN then it returns 0.
	 * 
	 * @param bN
	 *            - properly formatted BigNatural
	 * 
	 * @ensures this = #this, bN = #bN, and for some integer i if this < bN
	 *          return i < 0, if this > bN return i > 0, or if this == bN return
	 *          i = 0.
	 * 
	 * @return some i, s.t. i < 0, i == 0, or i > 0
	 */
	@Override
	public final int compareTo(BigNatural bN) {
		int result = 0;

		// First convert to the two BigNaturals to strings then convert them to
		// stacks, in order to compare them according to their representation.

		// Also, note:
		// this.stackRep = x = reverse(tempX), and a = tempX.top
		// bN.stackRep = y = reverse(tempY), and b = tempY.top
		String thisString = this.toString();
		String bNString = bN.toString();
		Stack<Integer> x = new Stack<Integer>();
		Stack<Integer> y = new Stack<Integer>();

		if (thisString.length() > 0) {
			for (int i = 0; i < thisString.length(); i++) {
				Character c = Character.valueOf(thisString.charAt(i));
				String s = c.toString();
				int temp = Integer.parseInt(s);
				x.push(temp);
			}
		} else {
			int zero = 0;
			x.push(zero);
		}

		if (bNString.length() > 0) {
			for (int i = 0; i < bNString.length(); i++) {
				Character c = Character.valueOf(bNString.charAt(i));
				String s = c.toString();
				int temp = Integer.parseInt(s);
				y.push(temp);
			}
		} else {
			int zero = 0;
			y.push(zero);
		}

		// Now, if one size is greater than the other (say x.size > y.size) then
		// that Number is greater (thus x > y). Therefore, check these two
		// conditions first.
		if (x.size() > y.size()) {
			return 1;
		} else if (x.size() < y.size()) {
			return -1;
		} else {
			// Else, reverse the order of the stacks in order to compare the
			// lead digits of the numbers first, then continue iterating through
			// as necessary.
			Stack<Integer> tempX = new Stack<Integer>();
			Stack<Integer> tempY = new Stack<Integer>();

			for (int i = 0; i < x.size(); i++) {
				int j;
				j = x.pop();
				tempX.push(j);
				j = y.pop();
				tempY.push(j);
			}

			int a = tempX.pop();
			int b = tempY.pop();
			// If the lead digits are not equal then return an integer value
			// according to the the comparison. In other words, if a < b, then
			// this < bN, thus return -1, etc...
			if (a != b) {
				if (a < b) {
					return -1;
				} else {
					return 1;
				}
			} else {
				// Else, the lead digits are the same, therefore you need to
				// iterate through the stacks (numbers) in order to continue
				// comparing them until you find two different digits. Then you
				// return the appropriate integer according to the comparison.
				int count = 0, len = tempX.size();
				while (count < len) {
					a = tempX.pop();
					b = tempY.pop();

					if (a != b) {
						if (a < b) {
							return -1;
						} else if (a > b) {
							return 1;
						}
					}
					// Allows for the loop to terminate without violating the
					// requires of clause of stack.pop().
					count++;
				}
			}
		}
		// If nothing happens by this point in the code, then no difference were
		// found, which implies that the numbers are equal. Therefore return 0.
		return result;
	}

	/**
	 * Returns a boolean argument determining whether the two BigNaturals are
	 * equal. Follows the equals definition by satisfying the reflexive,
	 * symmetric, and transitive properties. Also, is robust to null.
	 * 
	 * @param o
	 *            - any item of the Object class
	 * 
	 * @ensures the value returned is true iff the BigNatural = the passed in
	 *          object
	 * 
	 * @return a boolean value based on the equality of the two objects
	 */
	@Override
	public final boolean equals(Object o) {
		// If the passed in object is an alias to the BigNatural then return
		// true
		if (o == this) {
			return true;
		}
		// If the passed in object points to null then return null
		if (o == null) {
			return false;
		}
		// If the Class of the two objects aren't equal then return false
		if (!o.getClass().equals(this.getClass())) {
			return false;
		}

		// Cast the passed in object to a SlowBigNatural (this is okay since the
		// above if statements were not satisfied) and then call compareTo.
		// If compareTo = 0 then the two objects are equal, therefore return
		// true.
		SlowBigNatural b = (SlowBigNatural) o;
		if (this.compareTo(b) == 0) {
			return true;
		}
		// Else, none of the above if statements were satisfied => the objects
		// aren't equal, therefore return false
		return false;
	}

	/**
	 * Returns a distinct and repeatable hash value determined by the given
	 * BigNatural. Using a simple algorithm generates an integer depending on
	 * the state and value of the BigNatural.
	 * 
	 * @ensures the returned integer is based on the BigNatural
	 * 
	 * @return an integer that is distinct and repeatable based on the state and
	 *         value of the BigNatural
	 */
	@Override
	public final int hashCode() {
		// If the hashCode hasn't been generated then it will be equal to zero,
		// therefore it needs to be generated. Else, it has already been
		// generated and cached which means it doesn't need to be generated
		// again.
		if (hashCodeCache == 0) {
			// Initialize the hash code to the vale of HASHCODESTART
			hashCodeCache = SlowBigNatural.HASHCODESTART;

			// Convert the BigNatural to a string then convert that string to an
			// array of characters.
			String s = this.toString();
			char[] c = s.toCharArray();

			// Now, sum the values of the digits of the BigNatural and add that
			// sum to HASHCODESTART.
			for (int i = 0; i < c.length; i++) {
				String temp = "";
				temp = temp + c[i];
				int j = Integer.valueOf(temp);
				hashCodeCache = hashCodeCache + j;
			}

			// Finally divide the above sum by the length of the string
			// representation of the BigNatural and add one. Then return that
			// value as the hash code.
			hashCodeCache = (hashCodeCache / s.length()) + 1;
		}

		return hashCodeCache;
	}
}
