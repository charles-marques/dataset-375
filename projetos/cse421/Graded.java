/**
 * A student, capable of taking courses for credit and receiving letter grades
 * in such courses. The sum total of all courses taken (and their associated
 * grades) contributes to the student's cumulative GPA. The interface is
 * simiplified somewhat by only considering traditional letter grades (A-D, and
 * E). That is, there are no pass/fail courses or incompletes.
 * 
 * @mathmodel transcript is a sequence of CourseGrades, where <br />
 *            CourseGrade is a pair &lt;g,c&gt; where <br />
 *            g in {E,D,D+,C-,C,C+,B-,B,B+,A-,A} and c in {1,2,3,4,5}.
 * @mathdef totalCredit = sum(i : transcript[i].c) <br />
 *          gradeValue(g) in {0, 1, 1.3, 1.7, 2, 2.3, 2.7, 3.0, 3.3, 3.7, 4.0}
 * @initially transcript is empty
 * @author paolo
 */
public interface Graded {

	/**
	 * Obtains the most recently assigned grade.
	 * 
	 * @requires transcript.length > 0
	 * @return transcript[transcript.length-1].g
	 */
	LetterGrade getLastGrade();

	/**
	 * Obtains the total number of credit hours attempted. This total includes
	 * courses that the student did not pass.
	 * 
	 * @return totalCredit
	 */
	int getTotalCredits();

	/**
	 * Obtains current cumulative GPA, calculated on the usual 4-point scale
	 * where an A is worth 4 points, a B worth 3, and so on. Note that this
	 * cumulative GPA is weighted. That is, the grade obtained on a 5-credit
	 * course is 5 times more significant than the grade obtained on a 1-credit
	 * course.
	 * 
	 * @requires totalCredit > 0
	 * @return sum(&lt;g,c&gt; in transcript : gradeValue(g) * c) / totalCredit
	 */
	float getGPA();

	/**
	 * Records the outcome of attempting a course. The credit hours count
	 * towards the total credit hours attempted regardless of the grade obtained
	 * on the course.
	 * 
	 * @param credits
	 *            credit hour weighting for this course
	 * @param grade
	 *            LetterGrade earned for this course
	 * @requires 1 <= credits <= 5
	 * @alters transcript
	 * @ensures transcript = #transcript + &lt;grade,credits&gt;
	 */
	void assignGrade(short credits, LetterGrade grade);
}
