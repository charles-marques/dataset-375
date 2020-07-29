/**
 * 
 */
package unitTest;

import static org.junit.Assert.assertEquals;
import model.Phrase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Eric
 * @version 0.1
 */

public class PhraseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void accuracyTest() {
		Phrase p = new Phrase("你好", "Hello", null);
		assertEquals("default accuracy should be 0", 0, p.getAccuracy());
		p.increaseAccuracy();
		assertEquals("after increasing accuracy", 100, p.getAccuracy());
		p.decreaseAccuracy();
		assertEquals("after decreasing accuracy", 50, p.getAccuracy());
		p.decreaseAccuracy();
		assertEquals("after decreasing accuracy", 33, p.getAccuracy());
	}

}
