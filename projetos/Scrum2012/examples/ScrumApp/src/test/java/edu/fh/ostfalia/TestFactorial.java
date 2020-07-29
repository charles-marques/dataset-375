package edu.fh.ostfalia;

import junit.framework.Assert;

import org.junit.Test;

public class TestFactorial {

	@Test
	public void testFactorialWithOne() throws Exception {

		long fakultaet = Fakultaet.fakultaet(1);
		Assert.assertEquals("Die Fakultät von 1 ist 1.", 1, fakultaet);
	}
	
	@Test
	public void testFactorialWithZero() throws Exception {
		
		long fakultaet = Fakultaet.fakultaet(0);
		Assert.assertEquals("Die Fakultät von 1 ist 1.", 1, fakultaet);
	}
	
	@Test
	public void testFactorialWithTwo() throws Exception {
		
		long fakultaet = Fakultaet.fakultaet(2);
		Assert.assertEquals("Die Fakultät von 2 ist 2.", 2, fakultaet);
	}
	
	@Test(expected=RuntimeException.class)
	public void testFactorialWithNegativeValue() throws Exception {
		Fakultaet.fakultaet(-1);
	}
}
