package winline.livemonitor.tests;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import winline.livemonitor.helpers.TestHelper;
import winline.livemonitor.models.LiveMonitor;
import winline.livemonitor.pages.AdminPage;

public class TestRemoveMonitor extends TestHelper {
	private final static Logger LOG = Logger.getLogger(TestRemoveMonitor.class);

	@Before
	public void setUp() {
		Assert.assertTrue("its true page.", AdminPage.isAdminPage());
		Assert.assertEquals("Version is 'Beta 1.0.8'", "Beta 1.0.8", AdminPage.getVersion());
		// AdminPage.Reload();
	}

	@Test
	// @Ignore("пока не будем удалять мониторы")
	public void TestDeleteMonitors() {

		LOG.info("Test Monitors Delete:");

		if (AdminPage.getCountMonitors() > 0) {
			ArrayList<LiveMonitor> m = AdminPage.getMonitors();
			AdminPage.deleteAllMonitors(m);
			// AdminPage.Reload();
		}

		Assert.assertEquals("null monitors is expected", 0, AdminPage.getCountMonitors());
	}

	@Test
	@Ignore
	public void TestDeleteMonitorsSurplus() {
		LOG.info("Test get Monitors:");

		if (AdminPage.getCountMonitors() > 0) {
			ArrayList<LiveMonitor> m = AdminPage.getMonitors();
			for (LiveMonitor lm : m) {
				AdminPage.deleteMonitor(lm.getCssSelectorDropzone());
			}
		}
	}
}
