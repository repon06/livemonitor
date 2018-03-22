package winline.livemonitor.tests;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import winline.livemonitor.helpers.TestHelper;
import winline.livemonitor.pages.AdminPage;

public class TestAddMonitor extends TestHelper {
	private final static Logger LOG = Logger.getLogger(TestAddMonitor.class);

	@Before
	public void setUp() {
		Assert.assertTrue("its true page.", AdminPage.isAdminPage());
		Assert.assertEquals("Version is 'Beta 1.0.8'", "Beta 1.0.8", AdminPage.getVersion());
		// AdminPage.Reload();
	}

	@Test
	// @Ignore
	public void TestAddMonitors() {

		LOG.info("Test Monitors Add:");
		int add = 5;

		int firstCountMonitor = AdminPage.getCountMonitors();
		for (int i = 1; i <= add; i++) {
			AdminPage.addMonitor();
			Assert.assertEquals("addition is expected", firstCountMonitor + i, AdminPage.getCountMonitors());
		}
	}

}
