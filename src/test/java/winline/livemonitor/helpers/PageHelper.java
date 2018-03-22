package winline.livemonitor.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
//import static winline.livemonitor.helpers.PageHelper.preload;

public class PageHelper {
	private final static Logger LOG = Logger.getLogger(PageHelper.class);

	public static By pin = By.xpath("//p[@class='lead pin']"); // <p class="lead
	public static By version = By.xpath("//p[@class='lead version']");
	private static By reload = By.xpath("//div[@class='reload icon-reload']");

	private static By preload = By.id("preload");

	public static void waitingPreload() {
		//LOG.info("waitingPreload");
		$(preload).waitUntil(disappear, 10000);
	}

	public static String getPin() {
		return $(pin).text().replaceAll("Your PIN:", "");
	}

	public static String getVersion() {
		return $(version).text();
	}

	public static void Reload() {
		$(reload).click();
		waitingPreload();
	}

	public static WebDriver getDriver() {
		return WebDriverRunner.getWebDriver();
	}
}
