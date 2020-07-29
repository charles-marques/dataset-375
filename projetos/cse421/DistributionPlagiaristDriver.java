import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author Nathan Newman
 * 
 */
public final class DistributionPlagiaristDriver {

	/**
	 * Index for the keyLength in args.
	 */
	private static final int KINDEX = 0;

	/**
	 * Index for the phraseLength in args.
	 */
	private static final int PINDEX = 1;

	/**
	 * Index for the numOfPhrases in args.
	 * 
	 */
	private static final int NINDEX = 2;

	/**
	 * Index for the fileName in args.
	 */
	private static final int FINDEX = 3;

	/**
	 * Private constructor to suppress the checkstyle violation for utility
	 * classes having public or default constructors.
	 */
	private DistributionPlagiaristDriver() {

	}

	/**
	 * Utility class used to run the DistributionPlagiarist using the arguments
	 * read in from the command line.
	 * 
	 * @param args
	 *            an array of strings consisiting of:
	 *            <p>
	 *            args[0] = keyLength.
	 *            <p>
	 *            args[1] = phraseLength,
	 *            <p>
	 *            args[2] = numOfPhrases,
	 *            <p>
	 *            args[3] = fileName
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String k, p, n, name;

		try {
			k = args[DistributionPlagiaristDriver.KINDEX];
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Not enough command line arguments!");
			IndexOutOfBoundsException ie = new ArrayIndexOutOfBoundsException();
			throw ie;
		}

		try {
			p = args[DistributionPlagiaristDriver.PINDEX];
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Not enough command line arguments!");
			IndexOutOfBoundsException ie = new ArrayIndexOutOfBoundsException();
			throw ie;
		}

		try {
			n = args[DistributionPlagiaristDriver.NINDEX];
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Not enough command line arguments!");
			IndexOutOfBoundsException ie = new ArrayIndexOutOfBoundsException();
			throw ie;
		}

		try {
			name = args[DistributionPlagiaristDriver.FINDEX];
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Not enough command line arguments!");
			IndexOutOfBoundsException ie = new ArrayIndexOutOfBoundsException();
			throw ie;
		}

		DistributionPlagiarist distPlag = new DistributionPlagiarist(k, p, n,
				name);

		distPlag.setSourceText();

		System.out.println("Key Length: " + distPlag.getKeyLength());
		System.out.println("Length of Phrase: " + distPlag.getPhraseLength());
		System.out.println("Number of Phrases to be generated: "
				+ distPlag.getNumOfPhrase());
		System.out.println("Name of Source File: " + distPlag.getFileName());
		System.out.println();

		int count = 1;
		while (count <= distPlag.getNumOfPhrase()) {
			System.out.println("Random Phrase #" + count);
			System.out.println(distPlag.getRandomPhrase());
			count++;
		}
	}
}
