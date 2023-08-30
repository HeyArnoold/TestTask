import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static utils.Constants.URL_BROWSER;

public class TestBase {

    protected WebDriver driver;

    @BeforeEach
    void setupTest() throws MalformedURLException {
        driver = setUpBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterEach
    void afterTest() {
        attachScreenshot();
        driver.quit();
    }

    private RemoteWebDriver setUpBrowser() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new RemoteWebDriver(new URL(URL_BROWSER), options);
    }

    @Attachment(value = "screenshot", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
