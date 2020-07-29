import java.util.Random;
public class GradedTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random gen = new Random();
		
		Graded uGrad = new Undergrad();
		Graded grad = new Grad();
		
		final int NUMBSTDS = 30;
		final short NUMBCRDS = 3;
		LetterGrade grade = LetterGrade.EPURE;
		
		int rand = gen.nextInt(10) + 1;
		switch (rand){
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
		
		for (int i = 0; i < NUMBSTDS; i ++){
			uGrad.assignGrade(NUMBCRDS, grade);
			grad.assignGrade(NUMBCRDS, grade);
		}
		
		System.out.println("Undergrad GPA: " + uGrad.getGPA());
		System.out.println("Graduate GPA: " + grad.getGPA());
	}
}
