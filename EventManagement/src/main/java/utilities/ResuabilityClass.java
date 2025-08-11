package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * Utility class to encapsulate reusable Selenium operations like waits,
 * interactions, alerts, scrolling, and screenshots.
 */
public class ResuabilityClass {
    WebDriver driver;

    /**
     * Constructor to initialize the WebDriver
     * 
     * @param driver the WebDriver instance passed from test or page class
     */
    public ResuabilityClass(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Waits until the specified WebElement is visible on the page.
     * 
     * @param element the element to wait for
     */
    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the element is clickable and then clicks it.
     * 
     * @param element the element to click
     */
    public void clickElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * Waits until the element is visible, clears it, and enters the provided text.
     * 
     * @param element the input field WebElement
     * @param text    the text to enter
     */
    public void enterText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Captures a screenshot and stores it in the provided path.
     * Automatically creates directories if not present.
     * 
     * @param path file path where screenshot will be saved
     * @throws IOException if the file path is invalid
     */
    public void takeScreenshot(WebDriver driver, String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            // Absolute path to your screenshots folder
            String dest = "C:\\Users\\nanda\\eclipse-workspace\\EventManagement\\src\\test\\resources\\screenshots\\" + fileName + ".png";
            File destination = new File(dest);

            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved at: " + dest);
        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
    }



    /**
     * Scrolls down the page and clicks the element located by XPath using JavaScript.
     * 
     * @param elementXpath XPath of the element to scroll to and click
     * @throws InterruptedException for wait after scroll
     */
    public void scrollDown(String elementXpath) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath(elementXpath));

        // Scroll directly to the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        Thread.sleep(2000); // Visual confirmation (Optional)

        // Click the element using JavaScript
        js.executeScript("arguments[0].click();", element);
    }


    /**
     * Handles multiple browser windows and switches back to the main window.
     * Clicks the element by XPath to open a new window.
     * 
     * @param firstClick XPath of element that opens new window
     * @throws InterruptedException 
     */
    public void windowHandling(String firstClickXPath) {
        driver.findElement(By.xpath(firstClickXPath)).click();
        String mainWindow = driver.getWindowHandle();

        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(driver -> driver.getWindowHandles().size() > 1);

        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);

                WebElement resultText = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='VU-ZEz']")));

                System.out.println("First result text is: " + resultText.getText());
            }
        }
    }


    /**
     * Handles JavaScript alert popups, sends a value (if prompt), and accepts it.
     * 
     * @param value the value to enter in alert prompt
     * @throws InterruptedException to allow time before alert action
     */
    public void alerthandling(String value) throws InterruptedException {
        Alert alert = driver.switchTo().alert();
        Thread.sleep(2000); // Optional: for timing issues

        alert.sendKeys(value);
        alert.accept();
    }

    /**
     * Returns the current page title.
     * 
     * @return the page title as a String
     */
    public String getTitleName() {
        return driver.getTitle();
    }
    
    
    public void verifyElementsAreVisible(List<WebElement> elements) {
        for (WebElement e : elements) {
            waitForElementToBeVisible(e);
            if (!e.isDisplayed()) {
                throw new AssertionError("Element not visible: " + e.toString());
            }
        }
    }
    

    /**
     * Selects an option in a dropdown using visible text.
     */
    public void selectDropdownByVisibleText(WebElement dropdown, String visibleText) {
        waitForElementToBeVisible(dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Selects a radio button from a list based on its value attribute.
     */
    public void selectRadioButtonByValue(List<WebElement> radioButtons, String inputValue) {
        String expectedValue = "";

        if (inputValue.equalsIgnoreCase("Veg")) {
            expectedValue = "Vegetarian";
        } else if (inputValue.equalsIgnoreCase("NonVeg")) {
            expectedValue = "NonVegetarian";
        } else {
            expectedValue = inputValue; // Fallback if full value is passed
        }

        for (WebElement radio : radioButtons) {
            if (radio.getAttribute("value").equalsIgnoreCase(expectedValue)) {
                if (!radio.isSelected()) {
                    radio.click();
                }
                break;
            }
        }
    }


}
