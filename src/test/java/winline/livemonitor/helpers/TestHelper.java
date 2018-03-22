package winline.livemonitor.helpers;

import static com.codeborne.selenide.Selenide.open;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import winline.livemonitor.lib.ReadFromFile;

public class TestHelper {
	private final static Logger LOG = Logger.getLogger(TestHelper.class);

	protected static Properties properties = new Properties();

	private static DesiredCapabilities getChromeCapabilities() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("no-sandbox");

		/*
		 * Алексей_winline_разработчик: запускается но только вот с этими
		 * параметрами google-chrome --no-sandbox --user-data-dir
		 */
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(CapabilityType.LOGGING_PREFS, "OFF");
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		return cap;
	}

	protected static WebDriver createChromeDriver() {
		DesiredCapabilities capabilities = new DesiredCapabilities();// getChromeCapabilities();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("no-sandbox");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		return new ChromeDriver(capabilities);
	}

	@BeforeClass
	public static void init() throws IOException {

		LOG.info("TestHelper.Init()");
		LOG.info("os.name=" + System.getProperty("os.name"));

		properties.load(new FileReader(new File("src/test/resources/local.properties")));

		if (isWindows()) {
			System.setProperty("webdriver.chrome.driver", "D:\\work\\java\\Quality-lab\\live-monitors\\src\\protected\\tests\\src\\test\\resources\\chromedriver_2.23.exe");
			// [17:18:20] Oleg Polovynkin:
			// src/test/resources/chromedriver_2.23.exe"
			// [17:18:24] Oleg Polovynkin: никогда так не делай)
			// [17:18:45] Oleg Polovynkin: если хромдрайвер в папке с проектом
			// [17:18:52] Oleg Polovynkin: то mvn clean не сработает
			// [17:18:59] Oleg Polovynkin: изза того что винда залочит ехешник
		} else if (isUnix()) {
			// WebDriverRunner.setWebDriver(createChromeDriver());
		}

		System.setProperty("selenide.browser", "Chrome");
		System.setProperty("selenide.openBrowserTimeout", "30000");
		Configuration.browser = "Chrome";
		Configuration.savePageSource = false;
		Configuration.startMaximized = false;
		Configuration.timeout = 30000;
		// LOG.info("Configuration.collectionsTimeout="+Configuration.collectionsTimeout);
		Configuration.collectionsTimeout = 30000;
		if (Configuration.baseUrl.equals("http://localhost:8080"))
			Configuration.baseUrl = properties.getProperty("baseUrl");
		WebDriverRunner.getWebDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		open(Configuration.baseUrl);
	}

	public static boolean isWindows() {
		String os = System.getProperty("os.name").toLowerCase();
		// windows
		return (os.indexOf("win") >= 0);
	}

	public static boolean isUnix() {
		String os = System.getProperty("os.name").toLowerCase();
		// linux or unix
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}

}
