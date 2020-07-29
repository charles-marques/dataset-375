package hudson.plugins.backlog.base;

import java.io.InputStream;
import java.util.Properties;

import org.junit.BeforeClass;

public abstract class BaseTest {

	protected static String BACKLOG_SPACE_URL;
	protected static String BACKLOG_USERNAME;
	protected static String BACKLOG_PASSWORD;
	protected static int BACKLOG_PROJECT_ID;
	protected static String BACKLOG_PROJECT_KEY;

	@BeforeClass
	public static void setUpClass() throws Exception {
		loadBacklogProperties();
	}

	protected static void loadBacklogProperties() throws Exception {
		final Properties properties = new Properties();

		// プロパティファイル（テンプレート）の読込
		final InputStream propertyFileTemplate = BaseTest.class
				.getResourceAsStream("template.base.properties");
		properties.load(propertyFileTemplate);
		// 存在するならば、プロパティファイル（ユーザ固有）の読込
		final InputStream propertyFile = BaseTest.class
				.getResourceAsStream("base.properties");
		if (propertyFile != null) {
			properties.load(propertyFile);
		}

		BACKLOG_SPACE_URL = properties.getProperty("SPACE_URL");
		BACKLOG_USERNAME = properties.getProperty("USERNAME");
		BACKLOG_PASSWORD = properties.getProperty("PASSWORD");
		BACKLOG_PROJECT_ID = Integer.valueOf(properties
				.getProperty("PROJECT_ID"));
		BACKLOG_PROJECT_KEY = properties.getProperty("PROJECT_KEY");
	}

}
