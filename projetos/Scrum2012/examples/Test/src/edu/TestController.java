package edu;

public class TestController {
	
	public void testCreate() throws Exception {
		Controller c = new Controller();
		c.foo();
//		c.createTest("", 42);
	}
	
	public void testCreateWithNull() throws Exception {
		Controller c = new Controller();
//		c.createTest(null, 42);
	}
}
