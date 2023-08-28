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
import java.util.HashMap;

import static utils.AllureAttachment.attachVideoSelenoid;

public class TestBase {

    protected WebDriver driver;
    private String sessionId;
    private boolean isSelenoid = false;

    @BeforeAll
    static void setupBrowser() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() throws MalformedURLException {
        driver = setUpSelenoid();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
    }

    @AfterEach
    void teardown() {
        AllureAttachment.attachImage("screenshot", ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE));
        driver.quit();
        if(isSelenoid) {
            attachVideoSelenoid(sessionId);
        }
    }

    private RemoteWebDriver setUpSeleniumGrid() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new RemoteWebDriver(new URL("http://localhost:4444"), options);
    }

    private RemoteWebDriver setUpSelenoid() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("sessionTimeout", "2m");
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        options.addArguments("--start-maximized");
        isSelenoid = true;
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }
}
