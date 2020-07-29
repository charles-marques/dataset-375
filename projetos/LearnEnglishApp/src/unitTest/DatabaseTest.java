/**
 * 
 */
package unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.Database;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import program.InvalidFileNameException;

/**
 * @author charmjunewonder
 * 
 */
public class DatabaseTest {

	private Connection conn;

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
		File file = new File("data/test/database_test.db");
		file.delete();
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager
				.getConnection("jdbc:sqlite:data/test/database_test.db");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.Database#createAccount(java.lang.String)}.
	 */
	@Test
	public void testCreateAccount() throws InvalidFileNameException {
		Database database = new Database(conn);
		try {
			database.createAccount("Eric");
			database.createAccount("Eric");
		} catch (SQLException e) {
			assertEquals("column Name should be unique",
					"column Name is not unique", e.getMessage());
		}
	}

	/**
	 * Test method for {@link model.Database#deleteAccount(int)}.
	 */
	@Test
	public void testDeleteAccount() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Database#getAllAccountNames()}.
	 */
	@Test
	public void testGetAllAccountNames() {
		fail("Not yet implemented");
	}

}
