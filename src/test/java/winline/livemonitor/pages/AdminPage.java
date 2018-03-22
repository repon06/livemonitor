package winline.livemonitor.pages;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.collections.SizeLessThan;
import com.codeborne.selenide.ex.ElementNotFound;
import com.mysql.jdbc.UpdatableResultSet;

import winline.livemonitor.helpers.PageHelper;
import winline.livemonitor.lib.ReadFromFile;
import winline.livemonitor.models.EventSport;
import winline.livemonitor.models.LiveMonitor;
import winline.livemonitor.models.Sport;
import winline.livemonitor.models.TypeContentForMonitor;

public class AdminPage extends PageHelper {
	private final static Logger LOG = Logger.getLogger(AdminPage.class);

	// каталог контента
	private static By catalog = By.className("content-catalog prokrutka");
	private static By catalogItem = By.xpath("//div[@id_type_content]");

	private static By findLine = By.cssSelector(".custom_input.find");
	private static By sportFilters = By.className("sportFilters");// select

	// каталог событий
	private static By eventsTable = By.id("events");
	// private static By eventsItem = By.xpath("//tbody[@id='events']/tr");
	private static By eventsItem = By.cssSelector("tbody#events tr.item");
	private static By eventsItemTranslat = By.cssSelector("tbody#events tr.item td i.icon-tv_small");

	// панель мониторов
	private static By monitorPannel = By.className("monitors-panel");
	// private static By oneMonitorDropzone =
	// By.xpath("//div[contains(@class,'one_monitor dropzone')]");
	private static By MonitorsDropzone = By.cssSelector(".dropzone");

	private static By addMonitor = By.id("add-monitor");
	private static By oneMonitor = By.xpath("//div[@class='monitors-panel']/div[@class='results']/div");

	// управление монитором
	// надпись над монитором, можно менять
	private static By nameMonitor = By.cssSelector("div.name-monitor *");// p_or_input_надпись_над_монитором,_можно_менять

	private static By advMonitor = By.id("adv-monitor");// class_содержит/нет_enabled
	private static By removeMonitor = By.id("remove-monitor");
	private static By playlistMonitor = By.id("playlist-monitor");
	private static By reloadMonitor = By.id("reload-monitor");

	private static By textMonitor = By.className("monitor-text");
	// private static By iconMonitor =
	// By.xpath(".//p[@class='icon-monitor']/*");
	private static By iconMonitor = By.cssSelector("p.icon-monitor *");

	// перенос трансляции на монитор
	private static By translationDialog = By.cssSelector("div#dialog");
	private static By translationDialogClose = By.xpath("//div[@class='modal-close']/span");// By.cssSelector("div.modal-close
																							// span.glyphicon-remove");
	private static By translationDialogCaseTransl = By.xpath("//button[text()='Трансляция']");// By.cssSelector("button:contains('Трансляция')");//By.cssSelector("button[data-type='22']");
	private static By translationDialogCaseCoef = By.xpath("//button[text()='Коэффициенты']");// By.cssSelector("button:contains('Коэффициенты')");

	public static boolean isAdminPage() {
		waitingPreload();
		return $(eventsTable).exists();
	}

	/**
	 * фильтр по типу трансляции/игры Футбол
	 * 
	 * @param filter
	 */
	public static void selectSportFilter(String filter) {
		LOG.info("select SportFilter - " + filter);
		$(sportFilters).selectOption(filter);
		waitingPreload();
	}

	/**
	 * ожидание дозагрузки иконок трансляций у евентов
	 */
	public static boolean waitingLoadTraslationIcon() {
		try {
			// LOG.info("waiting Load Traslation Icon");
			$(eventsItemTranslat).waitUntil(Condition.present, 8000);
			// $(eventsItemTranslat).shouldHave(Condition.visible);
			// sleep(500);
			return true;
		} catch (ElementNotFound e) {
			return false;
		}
	}

	public static boolean isExistTranslationDialog() {
		$(translationDialog).waitUntil(Condition.present, 3000);
		return $(translationDialog).exists();
	}

	/**
	 * закрываем диалог
	 */
	public static void CloseTranslationDialog() {
		if (isExistTranslationDialog()) {
			$(translationDialog).$(translationDialogClose).click();
			$(translationDialog).waitUntil(Condition.disappear, 3000);
		}
	}

	/**
	 * выбираем трансляцию из диалога
	 */
	public static void CaseTranslationDialog() {
		if (isExistTranslationDialog()) {
			$(translationDialog).$(translationDialogCaseTransl).click();
			// LOG.info("Case Translation");
		}
	}

	/**
	 * выбираем коэффициенты из диалога
	 */
	public static void CaseCoefficientDialog() {
		if (isExistTranslationDialog()) {
			$(translationDialog).$(translationDialogCaseCoef).click();
			// LOG.info("Case Coefficient");
		}
	}

	/**
	 * фильтр текстовый
	 * 
	 * @param str
	 */
	public static void setFindLine(String str) {
		$(findLine).val(str);
		waitingPreload();
	}

	public static int getCountEvents() {
		return $$(eventsItem).size();
	}

	public static ArrayList<EventSport> getEventSport() {
		ArrayList<EventSport> event = new ArrayList<EventSport>();
		waitingLoadTraslationIcon();
		for (SelenideElement el : $$(eventsItem)) {
			// LOG.info("ID="+el.$(By.xpath("./td[1]")).text());
			event.add(new EventSport(el.$(By.xpath("./td[1]")).text(), el.$(By.xpath("./td[2]")).text(),
					el.$(By.xpath("./td[3]")).text(), el.$(By.xpath("./td[4]")).text(),
					el.$(By.xpath("./td[5]")).text(), el.$(By.xpath("./td[6]")).text(),
					el.$(By.xpath("./td[7]")).innerHtml().contains("<i class=\"icon icon-tv_small\"></i>")));

		}
		return event;
	}

	public static int getCountMonitors() {
		return $$(MonitorsDropzone).size();
	}

	/**
	 * список мониторов
	 * 
	 * @return
	 */
	public static ArrayList<LiveMonitor> getMonitors() {

		ArrayList<LiveMonitor> monitors = new ArrayList<LiveMonitor>();

		// for (SelenideElement mon : $$(MonitorsDropzone)) {
		for (SelenideElement mon : $$(MonitorsDropzone)) {

			if (!mon.find(iconMonitor).attr("class").contains("glyphicon"))
				monitors.add(new LiveMonitor(mon.getAttribute("id"), mon.find(nameMonitor).text(),
						mon.find(textMonitor).text(), mon.find(iconMonitor).attr("class")));
			else
				monitors.add(new LiveMonitor(mon.getAttribute("id"), mon.find(nameMonitor).text(), null,
						mon.find(iconMonitor).attr("class")));
		}
		return monitors;
	}

	public static void addMonitor() {
		int countMonitors = getCountMonitors();
		$(addMonitor).click();
		$$(MonitorsDropzone).shouldHave(SizeLessThan.sizeGreaterThan(countMonitors));
		// sleep(60000);
		// $$(oneMonitorDropzone).size();
	}

	/**
	 * удаленеи 1 монитора
	 * 
	 * @param monitor
	 *            - div propzone
	 */
	public static void deleteMonitor(String monitor) {
		$(monitor).find(removeMonitor).click();
		$(monitor).should(disappear);
	}

	/**
	 * удаление коллекции мониторов
	 * 
	 * @param mon
	 */
	public static void deleteAllMonitors(ArrayList<LiveMonitor> mon) {

		LOG.info("delete All Monitors: Count Monitors = " + mon.size());

		for (LiveMonitor m : mon) {
			deleteMonitor(m.getCssSelectorDropzone());
		}
	}

	/**
	 * перейти к плейлисту
	 * 
	 * @param name
	 */
	public static void gotoPlaylistMonitor(String monitor) {
		$(monitor).find(playlistMonitor).click();
		// sleep(10000);
	}

	public static String getNameMonitor(String monitor) {
		return $(monitor).find(nameMonitor).text();
	}

	public static void setNameMonitor(String monitor, String newName) {
		if ($(monitor).find(nameMonitor).getTagName().equals("p"))
			$(monitor).find(nameMonitor).click();
		if ($(monitor).find(nameMonitor).getTagName().equals("input")) {
			$(monitor).find(nameMonitor).val(newName);
			$(monitor).find(nameMonitor).sendKeys(Keys.ENTER);
		}
		// $(monitor).should(disappear);
	}

	public static void moveItemToMonitor(String CssSelectorDraggable, String CssSelectorDropzone) throws IOException {

		dad(CssSelectorDraggable, CssSelectorDropzone);
	}

	public static void moveItemToMonitor(EventSport event, LiveMonitor monitor, String typeContent) throws IOException {
		String iconM = null;
		String textM = null;
		// LOG.info("ID=" + monitor.getId());

		// LOG.info("1- '" + monitor.getIcon() + "', был у monitor.Icon");
		// LOG.info("2- '" + Sport.getIcon(event.getName()) + "', переносим
		// новый у event");
		// LOG.info("3- '" +
		// $(monitor.getCssSelectorDropzone()).find(iconMonitor).attr("class")+
		// "', был у $(monitor).iconMonitor до переноса");

		// кидаем элементы
		dad(event.getCssDraggable(), monitor.getCssSelectorDropzone());

		if (event.getIsTranslation()) {

			if (typeContent.equals(TypeContentForMonitor.Translation)) {
				CaseTranslationDialog();
				// LOG.info("is translation");
			} else if (typeContent.equals(TypeContentForMonitor.Coefficient)) {
				CaseCoefficientDialog();
				// LOG.info("is coeffic");
			} else if (typeContent.equals(TypeContentForMonitor.Cancel)) {
				CloseTranslationDialog();
				// LOG.info("is cancel");
			}
		}

		// если предыдущая иконка не совпадает с новой и не трансляция,
		// переносимой, ждем обновл
		// иконки у монитора
		if (!monitor.getIcon().contains(Sport.getIcon(event.getName())) && !event.getIsTranslation())
			$(monitor.getCssSelectorDropzone()).find(iconMonitor)
					.shouldNotHave(Condition.hasAttribute("class", monitor.getIcon()));

		// sleep(1000);
		// LOG.info("4- '" +
		// $(monitor.getCssSelectorDropzone()).find(iconMonitor).attr("class") +
		// "', стал у $(monitor).iconMonitor после переноса");

		iconM = $(monitor.getCssSelectorDropzone()).find(iconMonitor).attr("class");
		if (!iconM.contains("glyphicon-ban-circle"))
			textM = $(monitor.getCssSelectorDropzone()).find(textMonitor).text();

		// обновляем текущий монитор
		monitor.updateMonitor(textM, iconM);
	}

	/**
	 * java+jQuery <B>DragAndDrop</B> - HTML5 is work!! JQuery can ONLY work
	 * with id and css , xpath does NOT work with it.
	 * 
	 * @param source
	 *            - что тащить
	 * @param target
	 *            - куда тащить
	 * @throws IOException
	 */
	private static void dad(String source, String target) throws IOException {
		final String JQUERY_LOAD_SCRIPT = ("src/test/resources/jquery_load_helper.js");
		String jQueryLoader = ReadFromFile.getText(JQUERY_LOAD_SCRIPT);

		final String filePath = "src/test/resources/drag_and_drop_helper.js";
		String javaScript = ReadFromFile.getText(filePath);

		JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
		js.executeAsyncScript(
				jQueryLoader /* ,http://localhost:8080/jquery-1.7.2.js */);

		// ready to rock
		js.executeScript("jQuery(function($) { $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); }); ");

		javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";

		((JavascriptExecutor) getDriver()).executeScript(javaScript);
	}
}
