package applitools.v1;

import applitools.WebdriverBase;
import org.junit.Test;

public class VisualAITests extends WebdriverBase {

    @Test
    public void loginPageContainsAllElements() {
        new LoginPage(getWebDriver()).get();
        validateWindow();
    }

    @Test
    public void cannotLogInWithEmptyUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(getWebDriver()).get();
        loginPage.clickLoginExpectingError();
        validateWindow();
    }

    @Test
    public void cannotWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(getWebDriver()).get();
        loginPage.typeUsername("Appli");
        loginPage.clickLoginExpectingError();
        validateWindow();
    }

    @Test
    public void cannotLoginWithEmptyUsername() {
        LoginPage loginPage = new LoginPage(getWebDriver()).get();
        loginPage.typePassword("Password");
        loginPage.clickLoginExpectingError();
        validateWindow();
    }

    @Test
    public void canLogin() {
        LoginPage loginPage = new LoginPage(getWebDriver()).get();
        loginPage.loginAs("Hackathon", "Password");
        validateWindow();
    }

    @Test
    public void canSortDashboardTableByAmount() {
        LoginPage loginPage = new LoginPage(getWebDriver()).get();
        DashboardPage dashboardPage = loginPage.loginAs("Sorting", "Test");
        dashboardPage.sortByAmount();
        validateWindow();
    }

    @Test
    public void canValidateCanvasData() {
        LoginPage loginPage = new LoginPage(getWebDriver()).get();
        DashboardPage dashboardPage = loginPage.loginAs("Hackathon", "Password");
        dashboardPage.clickCompareExpenses();
        validateWindow();
    }

    @Test
    public void canValidateAdditionalCanvasData() {
        LoginPage loginPage = new LoginPage(getWebDriver()).get();
        DashboardPage dashboardPage = loginPage.loginAs("Hackathon", "Password");
        HackathonChartPage hackathonChartPage = dashboardPage.clickCompareExpenses();
        hackathonChartPage.clickAddDataset();
        validateWindow();
    }

    @Test
    public void canTestDynamicContent() {
        LoginPage loginPage = new LoginPage(getWebDriver()).get();
        loginPage.loginAs("Sorting", "Test");
        validateWindow();
    }
}
