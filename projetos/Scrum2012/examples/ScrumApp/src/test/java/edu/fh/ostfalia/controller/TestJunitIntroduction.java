package edu.fh.ostfalia.controller;

import junit.framework.Assert;

import org.junit.Test;

public class TestJunitIntroduction {

	@Test
	public void testFactorialForZero() throws Exception {
		Assert.assertEquals(1, Factorial.factorial(0));
	}
	
	@Test
	public void testFactorialForOne() throws Exception {
		Assert.assertEquals(1, Factorial.factorial(1));
	}
	
	@Test
	public void testFactorialForTwo() throws Exception {
		Assert.assertEquals(2, Factorial.factorial(2));
	}
	
	@Test
	public void testFactorialForThree() throws Exception {
		Assert.assertEquals(6, Factorial.factorial(3));
	}
}
