import java.util.Random;

/**
 * 
 * @author Nathan Newman
 * 
 */
public class Prof {

	public void evaluate(Graded[] gradedArr) {
		Random gen = new Random();

		for (int i = 0; i < gradedArr.length; i++) {
			final short NUMBCRDS = 3;
			LetterGrade grade = LetterGrade.EPURE;

			int rand = gen.nextInt(10) + 1;
			// Using a random number generator, assigns a random LetterGrade
			// to each slot of the passed in array.
			switch (rand) {
			case 1:
				grade = LetterGrade.APURE;
				break;
			case 2:
				grade = LetterGrade.AMINUS;
				break;
			case 3:
				grade = LetterGrade.BPLUS;
				break;
			case 4:
				grade = LetterGrade.BPURE;
				break;
			case 5:
				grade = LetterGrade.BMINUS;
				break;
			case 6:
				grade = LetterGrade.CPLUS;
				break;
			case 7:
				grade = LetterGrade.CPURE;
				break;
			case 8:
				grade = LetterGrade.CMINUS;
				break;
			case 9:
				grade = LetterGrade.DPLUS;
				break;
			case 10:
				grade = LetterGrade.DPURE;
				break;
			default:
				grade = LetterGrade.EPURE;
				break;
			}

			gradedArr[i].assignGrade(NUMBCRDS, grade);
		}
	}
}
