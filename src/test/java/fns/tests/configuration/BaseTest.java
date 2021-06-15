package fns.tests.configuration;

import app.App;
import app.helpers.AllureScreenShooter;
import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;


@Listeners(AllureScreenShooter.class)
public class BaseTest {

    protected App app;

    {
        Configuration.baseUrl = "https://www.nalog.gov.ru";
        Configuration.startMaximized = true;
        Configuration.browser = Browsers.CHROME;
        Configuration.timeout = 10000;
    }

    @BeforeClass
    public void setUp() {
        app = new App();
    }

    @AfterClass
    public void tearDown() {
        WebDriverRunner.getWebDriver().close();
    }

}
