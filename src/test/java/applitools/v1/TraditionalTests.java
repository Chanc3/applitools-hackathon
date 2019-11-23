package applitools.v1;

import applitools.WebdriverBase;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TraditionalTests extends WebdriverBase {

  @Test
  public void loginPageContainsAllElements() {
    LoginPage loginPage = new LoginPage(getWebDriver()).get();
    assertThat(loginPage.isLogoDisplayed()).isTrue();
    assertThat(loginPage.isLoginHeaderDisplayed()).isTrue();
    assertThat(loginPage.isUsernameInputDisplayed()).isTrue();
    assertThat(loginPage.isPasswordInputDisplayed()).isTrue();
    assertThat(loginPage.isRememberMeCheckboxDisplayed()).isTrue();
    assertThat(loginPage.isTwitterIconDisplayed()).isTrue();
    assertThat(loginPage.isFacebookIconDisplayed()).isTrue();
    assertThat(loginPage.isLinkedinIconDisplayed()).isTrue();
  }

  @Test
  public void cannotLogInWithEmptyUsernameAndPassword() {
    LoginPage loginPage = new LoginPage(getWebDriver()).get();
    loginPage.clickLoginExpectingError();
    assertThat(loginPage.getAlertWarning()).isEqualTo("Both Username and Password must be present");
  }

  @Test
  public void cannotWithEmptyPassword() {
    LoginPage loginPage = new LoginPage(getWebDriver()).get();
    loginPage.typeUsername("Appli");
    loginPage.clickLoginExpectingError();
    assertThat(loginPage.getAlertWarning()).isEqualTo("Password must be present");
  }

  @Test
  public void cannotLoginWithEmptyUsername() {
    LoginPage loginPage = new LoginPage(getWebDriver()).get();
    loginPage.typePassword("Password");
    loginPage.clickLoginExpectingError();
    assertThat(loginPage.getAlertWarning()).isEqualTo("Username must be present");
  }

  @Test
  public void canLogin() {
    LoginPage loginPage = new LoginPage(getWebDriver()).get();
    loginPage.loginAs("Hackathon", "Password");
  }

  @Test
  public void canSortDashboardTableByAmount() {
    LoginPage loginPage = new LoginPage(getWebDriver()).get();
    DashboardPage dashboardPage = loginPage.loginAs("Sorting", "Test");
    List<WebElement> beforeSort = dashboardPage.getTableRows();
    dashboardPage.sortByAmount();
    List<WebElement> afterSort = dashboardPage.getTableRows();
    List<Double> amounts = dashboardPage.getRecentTransactionAmounts();
    assertThat(amounts).isSortedAccordingTo(Comparator.naturalOrder());
    afterSort.forEach(
        webElement -> {
          assertThat(beforeSort).contains(webElement);
        });
  }

  @Test
  public void canValidateCanvasData() {
    /*
    Unable to automate using just selenium because the canvas chart is
    generated from the chartjs-render-monitor class and there are
    no available elements to interact with or gather info from on the page.

    This test will require a visual testing tool
     */
  }

  @Test
  public void canTestDynamicContent() {
    LoginPage loginPage = new LoginPage(getWebDriver()).get();
    DashboardPage dashboardPage = loginPage.loginAs("Sorting", "Test");
    assertThat(dashboardPage.isGifOneDisplayed()).isTrue();
    assertThat(dashboardPage.isGifTwoDisplayed()).isTrue();
  }
}
