package winline.livemonitor.tests;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import winline.livemonitor.helpers.TestHelper;
import winline.livemonitor.models.Sport;
import winline.livemonitor.pages.AdminPage;

public class TestFilterEvents extends TestHelper {
	private final static Logger LOG = Logger.getLogger(TestFilterEvents.class);

	@Before
	public void setUp() {
		Assert.assertTrue("its true page.", AdminPage.isAdminPage());
		Assert.assertEquals("Version is 'Beta 1.0.8'", "Beta 1.0.8", AdminPage.getVersion());
		AdminPage.Reload();
	}

	@Test
	// @Ignore
	public void TestMonitorsFilterSelect() {

		LOG.info("Test Monitors Filter footbal:");

		int countAllMatches = AdminPage.getCountEvents();
		LOG.info("All the matches: " + countAllMatches);
		AdminPage.selectSportFilter(Sport.footbal());
		int countMatches = AdminPage.getCountEvents();
		LOG.info("Matches of football: " + countMatches);
		Assert.assertTrue("Matches of football >= 0.", countMatches >= 0);

	}

	@Test
	// @Ignore
	public void TestMonitorsFilterSelectAll() {

		LOG.info("Test Monitors Filter all:");

		int countAllMatches = AdminPage.getCountEvents();
		LOG.info("All the matches: " + countAllMatches);
		AdminPage.selectSportFilter(Sport.all());
		int countMatches = AdminPage.getCountEvents();
		LOG.info("Matches of all: " + countMatches);
		Assert.assertTrue("Matches of football >= 0.", countMatches >= 0);

	}

	@Test
	// @Ignore
	public void TestMonitorsFilterLine1Symbol() {

		LOG.info("Test Monitors line Filter 1 symbol:");

		int countAllMatches = AdminPage.getCountEvents();
		LOG.info("All the matches: " + countAllMatches);

		// 1 символ регистр Б
		AdminPage.setFindLine("Т");
		int countMatches = AdminPage.getCountEvents();
		LOG.info("Matches of all: " + countMatches);
		Assert.assertTrue("Matches of  >= 0.", countMatches >= 0);

		// 1 символ пусто
		AdminPage.setFindLine("");
		countAllMatches = AdminPage.getCountEvents();
		LOG.info("Matches of all: " + countAllMatches);
		Assert.assertTrue("Matches of  >= 0.", countMatches <= countAllMatches);

		// 1 символ регистр М
		AdminPage.setFindLine("т");
		countMatches = AdminPage.getCountEvents();
		LOG.info("Matches of all: " + countMatches);
		Assert.assertTrue("Matches of  >= 0.", countMatches >= 0);
	}

	@Test
	// @Ignore
	public void TestMonitorsFilterLine1Word() {

		LOG.info("Test Monitors line Filter 1 symbol:");

		int countAllMatches = AdminPage.getCountEvents();
		LOG.info("All the matches: " + countAllMatches);

		AdminPage.setFindLine("теннис");
		int countMatches = AdminPage.getCountEvents();
		LOG.info("Matches of all: " + countMatches);
		Assert.assertTrue("Matches of  >= 0.", countMatches >= 0);

		AdminPage.setFindLine("ТЕННИС");
		int countMatchesTennise = AdminPage.getCountEvents();
		LOG.info("Matches of all: " + countMatches);
		Assert.assertEquals("Matches of Tennis ", countMatches, countMatchesTennise);
	}

}
