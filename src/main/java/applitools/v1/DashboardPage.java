package applitools.v1;

import applitools.AbstractLoadable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends AbstractLoadable<DashboardPage> {

  private String TITLE = "ACME demo app";
  private String PAGE_URL = "https://demo.applitools.com/hackathonApp.html";

  @FindBy(id = "amount")
  WebElement amountColumnHeader;

  @FindBy(css = ".text-right.bolder.nowrap")
  WebElement transactionAmount;

  public DashboardPage(WebDriver webDriver) {
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

  public void sortByAmount() {
    getWait().until(ExpectedConditions.visibilityOf(amountColumnHeader)).click();
  }

  public List<Double> getRecentTransactionAmounts() {
    List<WebElement> amountElements =
        getWait()
            .until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.cssSelector(".text-right.bolder.nowrap")));
    List<Double> amounts = new ArrayList<>();
    amountElements.forEach(
        webElement -> {
          String formattedString = webElement.getText().replace("USD", "").replace(" ", "");
          if (formattedString.contains("+")) {
            formattedString = formattedString.replace("+", "");
          }
          if (formattedString.contains(",")) {
            formattedString = formattedString.replace(",", "");
          }
          amounts.add(Double.valueOf(formattedString));
        });
    return amounts;
  }

  public List<WebElement> getTableRows() {
    return getWait()
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("tbody>tr")));
  }
}
