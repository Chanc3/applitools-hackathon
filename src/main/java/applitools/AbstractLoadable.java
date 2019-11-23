package applitools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractLoadable<T extends LoadableComponent<T>>
    extends LoadableComponent<T> {

  private final WebDriver webDriver;
  private final WebDriverWait wait;
  protected static final Logger LOG = LoggerFactory.getLogger(AbstractLoadable.class);

  public AbstractLoadable(WebDriver webDriver) {
    super();
    this.webDriver = webDriver;
    this.wait = new WebDriverWait(getWebDriver(), 10);
  }

  public void hasLoaded() {
    String currentUrl = getWebDriver().getCurrentUrl();
    if (currentUrl == null || currentUrl.equals("data:,") || currentUrl.equals("about:blank")) {
      throw new Error();
    }
  }

  public WebDriver getWebDriver() {
    return this.webDriver;
  }

  @Override
  protected void isLoaded() {
    LOG.info("Checking that [" + this.getClass().getSimpleName() + "] is loaded.");
  }

  @Override
  protected void load() {
    LOG.info("Loading [" + this.getClass().getSimpleName() + "].");
  }

  public WebDriverWait getWait() {
    return wait;
  }

  public boolean isDisplayed(WebElement webElement) {
    return getWait().until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
  }
}
