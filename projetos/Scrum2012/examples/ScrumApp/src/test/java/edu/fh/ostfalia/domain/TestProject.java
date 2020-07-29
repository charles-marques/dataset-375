package edu.fh.ostfalia.domain;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TestProject {

	@Test(expected=NullPointerException.class)
	public void testThrowException() throws Exception {
		Project project = new Project();
		throw new NullPointerException();
	}
	
	@Test
	public void testNewProject() throws Exception {
		Project project = new Project();
		Assert.assertNotNull(project);
	}
	
}
