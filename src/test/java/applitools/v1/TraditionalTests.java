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
}
