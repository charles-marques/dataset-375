import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class used to generate random, coherent phrases from a given text file.
 * This class uses 4 arguments passed to its constructor:
 * <p>
 * 1. A string argument representing the length of the key to use.
 * <p>
 * 2. A string argument representing length of the phrase to be generated.
 * <p>
 * 3. A string argument representing the number of phrases to generate.
 * <p>
 * 4. A string argument representing the name of the file.
 * <p>
 * This information is passed in from a client and then that client uses this
 * class to generate a random number of phrases of the designated length based
 * on the length of the given key. The class generates these phrases based on
 * the conditional frequencies given by the distributions of the the various
 * characters within the file.<\br>
 * 
 * @author Nathan Newman
 * @constraint All Strings representing an Integer must be properly formatted,
 *             and String.IntegerValue >= 0 and String.SourceFileName must exist
 * @convention this.keyFrequencies = DenseFrequencyLibrary
 * @correspondence this.keyFrequencies = FrequencyLibrary
 */
public class DistributionPlagiarist {
	/**
	 * The length of the key to be used.
	 */
	private int keyLength;

	/**
	 * The length of the phrase to be used.
	 */
	private int phraseLength;

	/**
	 * The number of phrases to be generated.
	 */
	private int numOfPhrases;

	/**
	 * The name, or path, of the source file.
	 */
	private String sourceFileName;

	/**
	 * 
	 */
	private String sourceText = "";

	/**
	 * The source file object.
	 */
	private File sourceFile;

	/**
	 * The current position of the scanner.
	 */
	private int currentPosition = 0;

	/**
	 * The current position of the scanner.
	 */
	private String keyString = "";

	/**
	 * The frequency library object.
	 */
	private FrequencyLibrary keyFrequencies;

	/**
	 * A 4 argument constructor used to properly initialize the class with the 4
	 * Strings stated in the class description. Checks the first three arguments
	 * to make sure they are in the proper format to be converted into an
	 * integer. Throws a NumberFormatExpcetion if one of the first 3 strings are
	 * not in the appropriate format to use Integer.parseInt(String).
	 * 
	 * @param kLength
	 *            a string representing the length of the key
	 * @param pLength
	 *            a string representing the length of the phrase
	 * @param nPhrases
	 *            a string representing the number of desired phrases
	 * @param fName
	 *            the name, or path, of the designated file
	 * 
	 * @requires the above parameters are formatted correctly, all strings
	 *           representing integer values should be >= 0
	 * 
	 * @ensures this.keyLength = kLength, this.phraseLength = pLength
	 *          this.numOfPhrases = nPhrases, this.sourceFileName = fName
	 */
	public DistributionPlagiarist(String kLength, String pLength,
			String nPhrases, String fName) {

		// DistributionPlagiarist.keyLength = kLength;
		// If the number is nor formatted correctly prompt the user for a new
		// value.
		try {
			int k = Integer.parseInt(kLength);
			if (k >= 0) {
				keyLength = k;
			} else {
				keyLength = 0;
			}
		} catch (NumberFormatException e) {
			System.out.println(e);
			Scanner kbd = new Scanner(System.in);
			System.out.print("Please enter an INTEGER value for the length"
					+ " of the key to be used: ");
			int k = kbd.nextInt();
			keyLength = k;
			kbd.close();
		}

		// DistributionPlagiarist.phraseLength = pLength;
		// If the number is nor formatted correctly prompt the user for a new
		// value.
		try {
			int p = Integer.parseInt(pLength);
			if (p >= 0) {
				phraseLength = p;
			} else {
				phraseLength = 0;
			}
		} catch (NumberFormatException e) {
			System.out.println(e);
			Scanner kbd = new Scanner(System.in);
			System.out.print("Please enter an INTEGER value for the length"
					+ " of the phrase to be used: ");
			phraseLength = kbd.nextInt();
			kbd.close();
		}

		// DistributionPlagiarist.numOfPhrases = nPhrases;
		// If the number is nor formatted correctly prompt the user for a new
		// value.
		try {
			int n = Integer.parseInt(nPhrases);
			if (n >= 0) {
				numOfPhrases = n;
			} else {
				numOfPhrases = 0;
			}
		} catch (NumberFormatException e) {
			System.out.println(e);
			Scanner kbd = new Scanner(System.in);
			System.out.print("Please enter an INTEGER value for the number"
					+ " of phrases to be generated: ");
			numOfPhrases = kbd.nextInt();
			kbd.close();
		}

		sourceFileName = fName;
	}

	/**
	 * Opens the source file and copies the text within the file into a String
	 * object this.sourceText. Also, initializes the Frequency library needed to
	 * generate a random phrase. Reads in characters from this.sourceText and
	 * stores them in the library according to the key they are associated with.
	 * @throws IOException 
	 * 
	 * @requires this.sourceFileName is a valid file name within the directory
	 *           or is a valid path name, and
	 *           <p>
	 *           this.sourceText.length() != 0
	 * 
	 * @ensures this.sourceText = sourceFile.sourceText and
	 *          <p>
	 *          this.keyFrequencies = FrequencyLibrary(this.sourceText), in
	 *          other words a FrequencyLibrary is created based on the strings
	 *          of length = this.keyLength
	 */
	public final void setSourceText() throws FileNotFoundException {
		keyFrequencies = new DenseFrequencyLibrary();
		try {
			// Instantiate a new file object.
			sourceFile = new File(sourceFileName);
			// Instantiate a new scanner in order to read in the data from
			// this.sourceFile.
			Scanner fileScan = new Scanner(sourceFile);
			while (fileScan.hasNextLine()) {
				// Loop through the source file reading in a line at a time and
				// append that line to this.sourceText, adding spaces when
				// appropriate.
				String line = fileScan.nextLine();
				while (currentPosition < line.length()) {
					if (currentPosition + keyLength < line.length()) {
						keyString = line.substring(currentPosition,
								currentPosition + keyLength);
						if (currentPosition + keyLength + 1 < line.length()) {
							keyFrequencies.add(keyString,
									line.charAt(currentPosition + keyLength));
						}
					}
					currentPosition++;
				}
				line = line + ' ';
				sourceText = sourceText + line;
				currentPosition = 0;
			}
			fileScan.close();
		} catch (FileNotFoundException e) {
			System.out.println("***************");
			System.out.println("File not found!");
			System.out
					.println("Please try again with a valid file name or path name.");
			System.out.println("***************");
		}
	}

	/**
	 * Using the uniform and conditional frequencies, this generates a random
	 * phrase according to the size of key used and the length of the phrase.
	 * 
	 * @requires this.keyFrequencies.size > 0
	 * 
	 * @ensures the string returned is generated based on the uniform and
	 *          conditional frequencies of the library
	 * 
	 * @return A random string based on the properties above
	 */
	public final String getRandomPhrase() {
		String retString = "";
		if (keyFrequencies.size() > 0) {
			if (keyLength == 0) {
				StringBuilder randomString = new StringBuilder();
				while (randomString.length() < phraseLength) {
					char c = keyFrequencies.randomUniformChoose("");
					randomString.append(c);
				}
				retString = randomString.toString();
				return retString;
			}
			String seedString = keyFrequencies.getRandomTitle();

			StringBuilder randomString = new StringBuilder();
			String prevString = "";
			while (randomString.length() <= phraseLength) {
				// Use the random seed to generate a character distribution,
				// then call random uniform choose on that string, and use
				// this method to build a random phrase.
				if (keyFrequencies.contains(seedString)) {
					prevString = seedString;
					StringBuilder nextString = new StringBuilder();

					nextString.append(seedString.substring(1));
					char c = keyFrequencies.randomUniformChoose(seedString);

					nextString.append(c);
					seedString = nextString.toString();

					randomString.append(c);
				} else {
					StringBuilder nextString = new StringBuilder();

					nextString.append(prevString.substring(1));
					char c = keyFrequencies.randomUniformChoose(prevString);

					nextString.append(c);
					seedString = nextString.toString();

					randomString.append(c);
				}
			}
			retString = randomString.toString();
		}
		return retString;
	}

	/**
	 * Returns the specified keyLength.
	 * 
	 * @return an integer = this.keyLenth
	 */
	public final int getKeyLength() {
		return keyLength;
	}

	/**
	 * Returns the specified phraseLength.
	 * 
	 * @return an integer = this.phraseLength
	 */
	public final int getPhraseLength() {
		return phraseLength;
	}

	/**
	 * Returns the specified numOfPhrases.
	 * 
	 * @return an integer = this.numOfPhrases
	 */
	public final int getNumOfPhrase() {
		return numOfPhrases;
	}

	/**
	 * Returns the specified fileName.
	 * 
	 * @return an integer = this.sourceFileName
	 */
	public final String getFileName() {
		return sourceFileName;
	}

	/**
	 * Returns a reference to this.keyFrequencies.
	 * 
	 * @return a -> this.keyFrequency
	 */
	public final FrequencyLibrary getFrequencyLibrary() {
		return keyFrequencies;
	}
}
