package applitools.v1;

import applitools.AbstractLoadable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractLoadable<LoginPage> {

  private String TITLE = "ACME demo app";
  private String PAGE_URL = "https://demo.applitools.com/hackathonV2.html?showAd=true";

  @FindBy(css = ".logo-w")
  WebElement pageLogo;

  @FindBy(css = ".auth-header")
  WebElement loginFormHeader;

  @FindBy(id = "username")
  WebElement usernameInput;

  @FindBy(id = "password")
  WebElement passwordInput;

  @FindBy(id = "log-in")
  WebElement loginButton;

  @FindBy(css = ".form-check-input")
  WebElement rememberMeCheckbox;

  @FindBy(css = "img[src='img/social-icons/twitter.png']")
  WebElement twitterIcon;

  @FindBy(css = "img[src='img/social-icons/facebook.png']")
  WebElement facebookIcon;

  @FindBy(css = "img[src='img/social-icons/linkedin.png']")
  WebElement linkedinIcon;

  @FindBy(css = ".alert.alert-warning")
  WebElement alertWarning;

  public LoginPage(WebDriver webDriver) {
    super(webDriver);
    PageFactory.initElements(webDriver, this);
  }

  @Override
  protected void load() {
    super.load();
    getWebDriver().navigate().to(PAGE_URL);
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

  public boolean isLogoDisplayed() {
    return isDisplayed(pageLogo);
  }

  public boolean isLoginHeaderDisplayed() {
    return (isDisplayed(loginFormHeader) && loginFormHeader.getText().equals("Login Form"));
  }

  public boolean isUsernameInputDisplayed() {
    return isDisplayed(usernameInput);
  }

  public boolean isPasswordInputDisplayed() {
    return isDisplayed(passwordInput);
  }

  public boolean isLoginButtonDisplayed() {
    return isDisplayed(loginButton);
  }

  public boolean isRememberMeCheckboxDisplayed() {
    return isDisplayed(rememberMeCheckbox);
  }

  public boolean isTwitterIconDisplayed() {
    return isDisplayed(twitterIcon);
  }

  public boolean isFacebookIconDisplayed() {
    return isDisplayed(facebookIcon);
  }

  public boolean isLinkedinIconDisplayed() {
    return isDisplayed(linkedinIcon);
  }

  public LoginPage clickLoginExpectingError() {
    getWait().until(ExpectedConditions.visibilityOf(loginButton)).click();
    return new LoginPage(getWebDriver()).get();
  }

  public String getAlertWarning() {
    return getWait().until(ExpectedConditions.visibilityOf(alertWarning)).getText();
  }

  public void typeUsername(String text) {
    getWait().until(ExpectedConditions.visibilityOf(usernameInput)).sendKeys(text);
  }

  public void typePassword(String text) {
    getWait().until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(text);
  }

  public DashboardPage loginAs(String username, String password) {
    typeUsername(username);
    typePassword(password);
    getWait().until(ExpectedConditions.visibilityOf(loginButton)).click();
    return new DashboardPage(getWebDriver()).get();
  }
}
