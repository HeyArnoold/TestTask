package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static utils.Constants.URL_LOGIN_PAGE;

public class LoginPage extends CommonPage{
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By customerLoginBy = By.cssSelector("[ng-click='customer()']");
    private By dropDownNameBy = By.cssSelector("[ng-model='custId']");
    private By loginButtonBy = By.cssSelector("[type='submit']");
    private String xPathDropDownName = "//*[contains(text(),'%s')]";

    public LoginPage openPage() {
        driver.get(URL_LOGIN_PAGE);
        return this;
    }

    public AccountPage logInAsCustomer(String loginName) {
        driver.findElement(customerLoginBy).click();
        driver.findElement(dropDownNameBy).click();
        driver.findElement(By.xpath(String.format(xPathDropDownName, loginName))).click();
        driver.findElement(loginButtonBy).click();

        return new AccountPage(driver);
    }
}
