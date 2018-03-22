package winline.livemonitor.tests;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import winline.livemonitor.helpers.TestHelper;
import winline.livemonitor.models.LiveMonitor;
import winline.livemonitor.pages.AdminPage;
import winline.livemonitor.pages.PlaylistPage;

public class TestPlayList extends TestHelper {
	private final static Logger LOG = Logger.getLogger(TestPlayList.class);

	@Before
	public void setUp() {
		Assert.assertTrue("its true page.", AdminPage.isAdminPage());
		Assert.assertEquals("Version is 'Beta 1.0.8'", "Beta 1.0.8", AdminPage.getVersion());
		// AdminPage.Reload();
	}

	@Test
	// @Ignore
	public void TestGotoPlaylistMonitor() {

		LOG.info("Test Goto Playlist Monitor:");

		int countMonitor = AdminPage.getCountMonitors();
		if (countMonitor > 0) {
			ArrayList<LiveMonitor> m = AdminPage.getMonitors();

			AdminPage.gotoPlaylistMonitor(m.get(0).getCssSelectorDropzone());
			Assert.assertTrue("PlayList is true page", PlaylistPage.isPlaylistPage());
			
			Assert.assertEquals("monitor names is equals", m.get(0).getName(),PlaylistPage.getMonitorName());
		}
	}

}
