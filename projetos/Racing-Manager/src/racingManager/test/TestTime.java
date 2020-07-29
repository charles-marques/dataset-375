package racingManager.test;

import racingManager.util.Time;
import racingManager.util.*;

import junit.framework.TestCase;
import org.junit.*;

public class TestTime extends TestCase {
	public void testTime() {
		Time t1 = new Time(3600);
		Time t2 = new Time(0);
		Time t3 = new Time(3601);
		Time t4 = new Time(3660);
		Time t5 = new Time(3665);
		
		Time t6 = new Time(7385);
		
		Assert.assertTrue(t1.toString().equals("1:0:0"));
		Assert.assertTrue(t2.toString().equals("0:0:0"));
		Assert.assertTrue(t3.toString().equals("1:0:1"));
		Assert.assertTrue(t4.toString().equals("1:1:0"));
		Assert.assertTrue(t5.toString().equals("1:1:5"));
		
		Assert.assertTrue(t6.toString().equals("2:3:5"));
	}
}
