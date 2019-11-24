package applitools.v1;

import applitools.AbstractLoadable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HackathonChartPage extends AbstractLoadable<HackathonChartPage> {

  private String TITLE = "ACME demo app";

  private String PAGE_URL = "https://demo.applitools.com/hackathonChart.html";

  @FindBy(id = "addDataset")
  WebElement addDatasetButton;

  public HackathonChartPage(WebDriver webDriver) {
    super(webDriver);
    PageFactory.initElements(webDriver, this);
  }

  @Override
  protected void load() {
    super.load();
  }

  @Override
  protected void isLoaded() throws Error {
    super.isLoaded();
    hasLoaded();
    if (getWebDriver().getCurrentUrl().contains(PAGE_URL)) {
      getWait().until(ExpectedConditions.titleIs(TITLE));
    } else {
      throw new Error();
    }
  }

  public HackathonChartPage clickAddDataset() {
    getWait().until(ExpectedConditions.visibilityOf(addDatasetButton)).click();
    return new HackathonChartPage(getWebDriver()).get();
  }
}
