import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.AllureAttachment;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestBase {

    protected WebDriver driver;

    @BeforeAll
    static void setupBrowser() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() throws MalformedURLException {
        driver = setUpSeleniumGrid();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterEach
    void teardown() {
        AllureAttachment.attachImage("screenshot", ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
        driver.quit();
    }

    private RemoteWebDriver setUpSeleniumGrid() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new RemoteWebDriver(new URL("http://localhost:4444"), options);
    }
}
