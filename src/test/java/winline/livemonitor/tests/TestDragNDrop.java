package winline.livemonitor.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import winline.livemonitor.helpers.PageHelper;
import winline.livemonitor.helpers.TestHelper;
import winline.livemonitor.models.Content;
import winline.livemonitor.models.EventSport;
import winline.livemonitor.models.LiveMonitor;
import winline.livemonitor.models.Sport;
import winline.livemonitor.models.TypeContentForMonitor;
import winline.livemonitor.pages.AdminPage;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.TypeConstraintException;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class TestDragNDrop extends TestHelper {
	private final static Logger LOG = Logger.getLogger(TestDragNDrop.class);

	@Before
	public void setUp() {
		Assert.assertTrue("its true page.", AdminPage.isAdminPage());
		Assert.assertEquals("Version is 'Beta 1.0.8'", "Beta 1.0.8", AdminPage.getVersion());
		AdminPage.Reload();
	}

	@Test
	@Ignore("переносим контент")
	public void TestDragAndDropContent() throws InterruptedException, IOException {

		LOG.info("Test Monitor Get & Move:");

		if (AdminPage.getCountMonitors() == 0) {
			AdminPage.addMonitor();
			Assert.assertEquals("addition is expected", 1, AdminPage.getCountMonitors());
		}

		if (AdminPage.getCountMonitors() > 0) {
			ArrayList<LiveMonitor> m = AdminPage.getMonitors();
			LOG.info("ArrayList is " + m.size());
			LOG.info(Content.general1);
			LOG.info(m.get(0).getCssSelectorDropzone());
			AdminPage.moveItemToMonitor(Content.general1, m.get(0).getCssSelectorDropzone());
		}
		// ArrayList<LiveMonitor> m2 = AdminPage.getMonitors();
	}

	@Test
	public void TestDragAndDropEvents() throws IOException {
		LOG.info("TestGet DragAndDropEvents:");
		String monIcon = null;
		int CountEvents = AdminPage.getCountEvents();

		CountEvents = AdminPage.getCountEvents();
		// проверить все ли евенты имеют выбранный тип спорта

		LOG.info("Item Events:" + CountEvents);

		if (CountEvents > 0 && AdminPage.getCountMonitors() > 0) {
			ArrayList<EventSport> es = AdminPage.getEventSport();
			ArrayList<LiveMonitor> m = AdminPage.getMonitors();

			LOG.info("Item:" + es.size());

			// трансляцию перетаскиваем
			for (int i = 0; i < es.size(); i++) {

				// monitor0
				AdminPage.moveItemToMonitor(es.get(i), m.get(0), TypeContentForMonitor.Translation);
				if (!es.get(i).getIsTranslation())
					Assert.assertTrue("icon is event-sport",
							m.get(0).getIcon().contains(Sport.getIcon(es.get(i).getName())));
				else
					Assert.assertTrue("icon is video", m.get(0).getIcon().contains(Sport.video_icon()));

				// monitor2
				AdminPage.moveItemToMonitor(es.get(i), m.get(2), TypeContentForMonitor.Coefficient);
				Assert.assertTrue("icon is event-sport",
						m.get(2).getIcon().contains(Sport.getIcon(es.get(i).getName())));

			}
		}
	}

	@Test
	@Ignore
	public void TestDragAndDropEventsTranslation() throws IOException {
		LOG.info("TestGet DragAndDropEvents Translation:");

		int CountEvents = AdminPage.getCountEvents();

		if (CountEvents > 0) {
			// дожидаемся прогрузки видеоТрансляций у событий
			boolean isTranslIconExist = AdminPage.waitingLoadTraslationIcon();

			if (isTranslIconExist) {
				AdminPage.selectSportFilter(Sport.video());
				CountEvents = AdminPage.getCountEvents();
				// проверить все ли евенты имеют выбранный тип спорта

				LOG.info("Item Events:" + CountEvents);

				if (CountEvents > 0 && AdminPage.getCountMonitors() > 0) {
					ArrayList<EventSport> es = AdminPage.getEventSport();
					ArrayList<LiveMonitor> m = AdminPage.getMonitors();

					LOG.info("Item EventSport:" + es.size());

					// трансляцию перетаскиваем
					for (int i = 0; i < es.size(); i++) {
						if (es.get(i).getIsTranslation()) {

							AdminPage.moveItemToMonitor(es.get(i), m.get(0), TypeContentForMonitor.Translation);
							Assert.assertTrue("icon is video", m.get(0).getIcon().contains(Sport.video_icon()));

							String iconMonitor = m.get(1).getIcon();
							AdminPage.moveItemToMonitor(es.get(i), m.get(1), TypeContentForMonitor.Cancel);
							Assert.assertEquals("icon is not changed", iconMonitor, m.get(1).getIcon());

							AdminPage.moveItemToMonitor(es.get(i), m.get(2), TypeContentForMonitor.Coefficient);
							Assert.assertTrue("icon is video", m.get(1).getIcon().contains(Sport.video_icon()));
							// break;// только 1 трансляцию перетаскиваем
						}
					}
				}
			}
		}
	}
}
