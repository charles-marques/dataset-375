/**
 * Represents an Undergraduate student's transcript. Using a class called
 * CourseGrade, an array of CourseGrade can be created in order to represent the
 * student's transcript.
 * 
 * @mathmodel transcript is a sequence of CourseGrades, where <br />
 *            CourseGrade is a pair &lt;g,c&gt; where <br />
 *            g in {E,D,D+,C-,C,C+,B-,B,B+,A-,A} and c in {1,2,3,4,5}.
 * 
 * @mathdef totalCredit = sum(i : transcript[i].c) <br />
 *          gradeValue(g) in {0, 1, 1.3, 1.7, 2, 2.3, 2.7, 3.0, 3.3, 3.7, 4.0}
 * 
 * @correspondence transcript = CourseGrade[]
 * 
 * 
 * @author Nathan Newman
 * 
 */
public class Undergrad implements Graded {

	/**
	 * Array of CourseGrade used to store the student's grades/credits.
	 */
	private CourseGrade[] transcript;
	
	/**
	 * An integer used to keep track of the size of the transcript.
	 */
	private int tranSize;
	
	/**
	 * An integer used to keep track of the total number of credits a student
	 * has.
	 */
	private int totalCredits;

	/**
	 * Initializes the transcript to be empty.
	 */
	public Undergrad() {
		tranSize = 0;
		transcript = new CourseGrade[tranSize];
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
			return transcript[tranSize - 1].getGrade();
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
			sum = sum + transcript[i].getCredits();
		}
		return sum;
	}

	/**
	 * A method that returns the GPA of the student.
	 * 
	 * @requires totalCredit > 0
	 * 
	 * @ensures getGpa() = sum(LetterGrade[i] * Credit{i]) / totalCredit
	 * 
	 * @return getGpa()
	 */
	@Override
	public float getGPA() {
		float sum = 0;
		if (totalCredits > 0) {
			float decGrade;
			for (int i = 0; i < tranSize; i++) {
				// Convert the value of the given LetterGrade to a float value
				switch (transcript[i].getGrade()) {
				case APURE:
					decGrade = (float) 4.0;
					break;
				case AMINUS:
					decGrade = (float) 3.7;
					break;
				case BPLUS:
					decGrade = (float) 3.3;
					break;
				case BPURE:
					decGrade = (float) 3.0;
					break;
				case BMINUS:
					decGrade = (float) 2.7;
					break;
				case CPLUS:
					decGrade = (float) 2.3;
					break;
				case CPURE:
					decGrade = (float) 2.0;
					break;
				case CMINUS:
					decGrade = (float) 1.7;
					break;
				case DPLUS:
					decGrade = (float) 1.3;
					break;
				case DPURE:
					decGrade = (float) 1.0;
					break;
				default:
					decGrade = 0;
					break;
				}
				sum = sum + decGrade * transcript[i].getCredits();
			}
			sum = sum / totalCredits;
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
			// First create a temporary transcript and transfer the current
			// transcript over
			CourseGrade[] tempTrans = new CourseGrade[tranSize];
			for (int i = 0; i < tranSize; i++) {
				tempTrans[i] = new CourseGrade(transcript[i].getCredits(),
						transcript[i].getGrade());
			}
			// Then increase the transcript size and create a new transcript
			// based on the new size
			tranSize++;
			transcript = new CourseGrade[tranSize];
			// Then transfer the temporary transcript back over
			for (int i = 0; i < tranSize - 1; i++) {
				transcript[i] = new CourseGrade(tempTrans[i].getCredits(),
						tempTrans[i].getGrade());
			}
			// Finally, append the CourseGrade(credits, grade) onto the
			// transcript
			transcript[tranSize - 1] = new CourseGrade(credits, grade);
			totalCredits = this.getTotalCredits();
		}
	}
}
