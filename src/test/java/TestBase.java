import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestBase {

    WebDriver driver;
    private ChromeOptions chromeOptions = new ChromeOptions();

    @BeforeAll
    static void setupBrowser() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
