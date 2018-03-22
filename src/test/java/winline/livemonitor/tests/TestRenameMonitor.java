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

public class TestRenameMonitor extends TestHelper {
	private final static Logger LOG = Logger.getLogger(TestRenameMonitor.class);

	@Before
	public void setUp() {
		Assert.assertTrue("its true page.", AdminPage.isAdminPage());
		Assert.assertEquals("Version is 'Beta 1.0.8'", "Beta 1.0.8", AdminPage.getVersion());
		AdminPage.Reload();
	}

	@Test
	//@Ignore
	public void TestRenameMonitors() {
		LOG.info("Test get Monitors:");

		String newNameMonitor = "0123456789101112131415";

		if (AdminPage.getCountMonitors() == 0)
			AdminPage.addMonitor();

		ArrayList<LiveMonitor> m = AdminPage.getMonitors();
		AdminPage.setNameMonitor(m.get(0).getCssSelectorDropzone(), newNameMonitor);
		Assert.assertEquals(newNameMonitor.substring(0, 10),
				AdminPage.getNameMonitor(m.get(0).getCssSelectorDropzone()));
	}
}
