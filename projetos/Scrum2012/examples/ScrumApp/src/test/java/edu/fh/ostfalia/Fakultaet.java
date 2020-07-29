package edu.fh.ostfalia;


public class Fakultaet {

	public static long fakultaet(int n) {
		if(n < 0)
			throw new RuntimeException("N darf nicht negativ sein,");
		if (n == 0 || n == 1)
			return 1;
		else
			return n * fakultaet(n - 1);
	}

}
