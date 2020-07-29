/**
 * 
 */
package unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import model.Lesson;
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

public class LessonTest {

	private Connection conn;

	// private Statement statement;

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
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:data/test.db");
		// statement = conn.createStatement();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Lesson#Lesson(java.sql.Connection, int)}.
	 */
	@Test
	public void testLesson() throws Exception {
		Lesson l = new Lesson(conn, "Lesson_0", false);
		assertNotNull("new Lesson should not be null", l);
		assertEquals("lesson should contains correct lesson name",
				l.getLessonName(), "Lesson_0");
	}

	/**
	 * Test method for
	 * {@link Lesson#addPharse(java.lang.String, java.lang.String, java.lang.String)}
	 * and {@link Lesson#getPhrase(int)}.
	 */
	@Test
	public void testAddAndGetPharse() throws Exception {
		Statement statement = conn.createStatement();
		statement.executeUpdate("drop table if exists Lesson_0;");
		Lesson l = new Lesson(conn, "Lesson_0", false);
		Phrase p = new Phrase("Hello", "你好", null);
		l.addPhrase(p);
		p = new Phrase("Nice Dream", "好梦", null);
		l.addPhrase(p);
		p = l.getPhrase(2);
		assertEquals("check Chinese of the second phrase", "好梦", p.getChinese());
		assertEquals("check English of the second phrase", "Nice Dream",
				p.getEnglish());
		p = l.getPhrase(1);
		assertEquals("check Chinese of the first phrase", "你好", p.getChinese());
		assertEquals("check English of the first phrase", "Hello",
				p.getEnglish());
	}

	@Test
	public void testLoadDefaultLesson() throws Exception {
		Lesson l = new Lesson(conn, 1);
		assertEquals("english of the first phrase", "Hello", l.getPhrase(1)
				.getEnglish());
		assertEquals("chinese of the fifth phrase", "你是谁", l.getPhrase(5)
				.getChinese());
		assertEquals("audio of the last phrase", "110", l.getPhrase(10)
				.getAudio());
	}

	/**
	 * Test method for {@link Lesson#close()}.
	 */
	@Test
	public void testClose() {
		fail("Not yet implemented");
	}

}
