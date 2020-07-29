/**
 * 
 */
package unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import model.Account;
import model.Lesson;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import program.InvalidFileNameException;

/**
 * @author Eric
 * @version 0.1
 */

public class AccountTest {

	// private Connection conn;

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
		File file = new File("data/test.db");
		file.delete();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.Account#Account(java.lang.String)}.
	 */
	@Test
	public void testAccount() throws InvalidFileNameException {
		Account a = new Account("test");
		assertNotNull("new Account should not be null", a);
		assertEquals("account should contains correct account name",
				a.getName(), "test");
	}

	@Test
	public void testLoadDefaultLesson() throws InvalidFileNameException {
		Account a = new Account("test");
		a.loadDefaultLessons();
		Lesson l = a.getLesson(1);
		assertEquals("english of the first phrase", "Hello", l.getPhrase(1)
				.getEnglish());
		assertEquals("chinese of the fifth phrase", "你是谁", l.getPhrase(5)
				.getChinese());
		assertEquals("audio of the last phrase", "110", l.getPhrase(10)
				.getAudio());
	}

	/**
	 * Test method for {@link model.Account#createNewLesson(java.lang.String)}.
	 */
	@Test
	public void testCreateNewLesson() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Account#getRandomTenPhrase()}.
	 */
	@Test
	public void testGetRandomTenPhrase() {
		fail("Not yet implemented");
	}

}
