package winline.livemonitor.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import winline.livemonitor.helpers.PageHelper;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PlaylistPage extends PageHelper {
	private final static Logger LOG = Logger.getLogger(PlaylistPage.class);

	// хлебные крошки
	private static By breadcrumbPl = By.xpath("//ol[@class='breadcrumb']/li/a[text()='Плейлист']");
	private static By breadcrumbIndex = By.xpath("//ol[@class='breadcrumb']/li/a[text()='Главная']");

	//заголовок
private static By headerMonitorName = By.cssSelector("div.panel-heading");
	
	private static By infinityPlaylist = By.id("infinity-playlist");

	// <button id="infinity-playlist" class="btn btn-warning" disabled=""
	// type="submit">
	// <button id="infinity-playlist" class="btn btn-warning" type="submit">

	public static boolean isPlaylistPage() {
		return $(breadcrumbPl).waitUntil(visible, 2000).exists();
	}

	public static void gotoIndex() {
		$(breadcrumbIndex).click();
	}

	public static boolean isinfinityPlaylistEnabled() {
		LOG.info($(infinityPlaylist).getAttribute("disabled"));
		return true;
	}

	public static String getMonitorName(){
		return $(headerMonitorName).text().trim();
	}


}
