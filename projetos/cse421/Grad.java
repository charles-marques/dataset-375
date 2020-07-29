/**
 * Represents an Graduate student's transcript. Using three arrays in order to
 * keep track of LetterGrades(grades), shorts(credits), and floats(decGrades) a
 * transcript can be created. The only requirement that needs to be met is that
 * all the indices of the arrays must line up.
 * 
 * @mathmodel transcript is a sequence of CourseGrades, where <br />
 *            CourseGrade is a pair &lt;g,c&gt; where <br />
 *            g in {E,D,D+,C-,C,C+,B-,B,B+,A-,A} and c in {1,2,3,4,5}.
 * 
 * @mathdef totalCredit = sum(i : transcript[i].c) <br />
 *          gradeValue(g) in {0, 1, 1.3, 1.7, 2, 2.3, 2.7, 3.0, 3.3, 3.7, 4.0}
 * 
 * @correspondence transcript = grades + credits + decGrades
 * 
 * @constraint if 0 <= i <= tranSize then grades[i], credits[i], decGrades[i]
 *             all correspond to the same class.
 * 
 * @author Nathan Newman
 * 
 */
public class Grad implements Graded {

	/**
	 * An array storing the student's LetterGrades.
	 */
	private LetterGrade[] grades;
	
	/**
	 * An array storing the student's credits.
	 */
	private short[] credits;
	
	/**
	 * An array storing the student's decimal equivalent of the student's
	 * LetterGrade.
	 */
	private float[] decGrades;
	
	/**
	 * An integer used to keep track of the total number of credits a student
	 * has.
	 */
	private int tranSize;

	/**
	 * Initializes the transcript to be empty.
	 */
	public Grad() {
		tranSize = 0;
		grades = new LetterGrade[tranSize];
		this.credits = new short[tranSize];
		decGrades = new float[tranSize];
	}

	/**
	 * A method that converts the given LetterGrade to its decimal equivalent.
	 * 
	 * @param elGee
	 *            -a LetterGrade
	 * 
	 * @requires elGee is in LetterGrade
	 * 
	 * @ensures getDecGrade() = decimal equivalent of elGee
	 * @return getDecGrade()
	 */
	private float getDecGrade(LetterGrade elGee) {
		float deeCee = 0;
		switch (elGee) {
		case APURE:
			deeCee = (float) 4.0;
			break;
		case AMINUS:
			deeCee = (float) 3.7;
			break;
		case BPLUS:
			deeCee = (float) 3.3;
			break;
		case BPURE:
			deeCee = (float) 3.0;
			break;
		case BMINUS:
			deeCee = (float) 2.7;
			break;
		case CPLUS:
			deeCee = (float) 2.3;
			break;
		case CPURE:
			deeCee = (float) 2.0;
			break;
		case CMINUS:
			deeCee = (float) 1.7;
			break;
		case DPLUS:
			deeCee = (float) 1.3;
			break;
		case DPURE:
			deeCee = (float) 1.0;
			break;
		default:
			deeCee = 0;
			break;
		}
		return deeCee;
	}

	/**
	 * A method that returns the grade added most recently to the transcript.
	 * 
	 * @requires transcript.size > 0
	 * 
	 * @return last added LetterGrade
	 */
	@Override
	public LetterGrade getLastGrade() {
		if (tranSize > 0) {
			return grades[tranSize - 1];
		}
		return null;
	}

	/**
	 * A method that returns the total number of credits a student has
	 * accumulated.
	 * 
	 * @ensures getTotalCredits() = totalCredit
	 * 
	 * @return getTotalCredits()
	 */
	@Override
	public int getTotalCredits() {
		int sum = 0;
		for (int i = 0; i < tranSize; i++) {
			sum = sum + credits[i];
		}
		return sum;
	}

	/**
	 * A method that returns the GPA of the student.
	 * 
	 * @requires this.getTotalCredits() > 0
	 * 
	 * @ensures getGpa() = sum(decGrades[i] * credits[i]) /
	 *          this.getTotalCredits()
	 * 
	 * @return getGpa()
	 */
	@Override
	public float getGPA() {
		float sum = 0;
		if (this.getTotalCredits() > 0) {
			for (int i = 0; i < tranSize; i++) {
				sum = sum + decGrades[i] * credits[i];
			}
			sum = sum / this.getTotalCredits();
		}
		return sum;
	}

	/**
	 * This method takes in a short and a LetterGrade and converts it to a
	 * CourseGrade, then adds it onto the end of the transcript.
	 * 
	 * @requires 1 <= credits <= 5
	 * 
	 * @ensures transcript = #transcript + CourseGrade(credits, grade)
	 */
	@Override
	public void assignGrade(short credits, LetterGrade grade) {
		if ((1 <= credits) && (credits <= 5)) {
			// First create three temporary arrays and swap over the
			// arrays if this
			LetterGrade[] tempGrades = new LetterGrade[tranSize];
			short[] tempCredits = new short[tranSize];
			float[] tempDecGrades = new float[tranSize];
			for (int i = 0; i < tranSize; i++) {
				tempGrades[i] = grades[i];
				tempCredits[i] = this.credits[i];
				tempDecGrades[i] = decGrades[i];
			}
			// Then increment the size of the transcript and create new
			// arrays of this according the new value of tranSize
			tranSize++;
			grades = new LetterGrade[tranSize];
			this.credits = new short[tranSize];
			decGrades = new float[tranSize];
			// Then transfer the temporary arrays back to the new arrays
			for (int i = 0; i < tranSize - 1; i++) {
				grades[i] = tempGrades[i];
				this.credits[i] = tempCredits[i];
				decGrades[i] = tempDecGrades[i];
			}
			// Finally, append the passed in information to the transcript
			grades[tranSize - 1] = grade;
			this.credits[tranSize - 1] = credits;
			decGrades[tranSize - 1] = this.getDecGrade(grades[tranSize - 1]);
		}
	}
}
