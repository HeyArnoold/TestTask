package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LocalStorage {
    private JavascriptExecutor js;

    public LocalStorage(WebDriver webDriver) {
        this.js = (JavascriptExecutor) webDriver;
    }

    public void removeItemFromLocalStorage(String item) {
        js.executeScript(String.format(
                "window.localStorage.removeItem('%s');", item));
    }

    public boolean isItemPresentInLocalStorage(String item) {
        return !(js.executeScript(String.format(
                "return window.localStorage.getItem('%s');", item)) == null);
    }

    public String getItemFromLocalStorage(String key) {
        return (String) js.executeScript(String.format(
                "return window.localStorage.getItem('%s');", key));
    }

    public void clearLocalStorage() {
        js.executeScript(String.format("window.localStorage.clear();"));
    }
}
