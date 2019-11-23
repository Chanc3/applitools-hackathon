package applitools;

import com.google.common.base.Preconditions;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class WebdriverBase {

    private static final Logger LOG = LoggerFactory.getLogger(WebdriverBase.class);
    private WebDriver webDriver = initializeDriver();

    @Before
    public void setWebDriver() {
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().manage().window().setSize(new Dimension(1920, 1400));
        Preconditions.checkNotNull(getWebDriver(), "Failed to set up the WebDriver");
    }

    @After
    public void cleanUpWebDriver() {
        try {
            getWebDriver().manage().deleteAllCookies();
            getWebDriver().quit();
        } catch (UnreachableBrowserException ube) {
            // Browser couldn't be reached during the attempt to delete cookies.
            LOG.error(ube.getMessage(), ube);
        } catch (NullPointerException npe) {
            // This is fine...
        } catch (WebDriverException e) {
            // Browser couldn't be reached during the attempt to get logs.
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    private WebDriver initializeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("no-default-browser-check");
        chromeOptions.addArguments("disable-default-apps");
        setChromePreferences(chromeOptions);
        chromeOptions.setCapability(
                CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        chromeOptions.setCapability("screenResolution", "1920x1400x24");
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        return new ChromeDriver(chromeOptions);
    }

    private void setChromePreferences(ChromeOptions chromeOptions) {
        Map<String, Object> prefs = new HashMap<>();

        // Don't prompt to save passwords
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        // Text on Toolbar instead of icons
        prefs.put("browser.chrome.toolbar_style", 1);

        chromeOptions.setExperimentalOption("prefs", prefs);
    }
}
