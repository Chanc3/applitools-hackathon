package applitools.v1;

import applitools.WebdriverBase;
import org.junit.Test;

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
}
