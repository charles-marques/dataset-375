/**
 * A class used to represent a letter grade and credit hours associated with 
 * a particular course.  
 * 
 * @author Nathan Newman
 *
 */
public class CourseGrade {

	private LetterGrade grade;
	private short credits;

	public CourseGrade(LetterGrade g) {
		grade = g;
	}

	public CourseGrade(short c) {
		this.credits = c;
	}

	public CourseGrade(short c, LetterGrade g) {
		grade = g;
		this.credits = c;
	}

	public LetterGrade getGrade() {
		return grade;
	}

	public short getCredits() {
		return this.credits;
	}
}
