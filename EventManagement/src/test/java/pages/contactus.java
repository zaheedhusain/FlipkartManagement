package pages;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.ResuabilityClass;

public class contactus {

    WebDriver driver;
    ResuabilityClass utils;
    private static final Logger logger = LogManager.getLogger(contactus.class);

    public contactus(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utils = new ResuabilityClass(driver);
        logger.info("Contact Us page initialized");
    }

    @FindBy(id = "contact_name")
    WebElement Name;

    @FindBy(id = "contact_email")
    WebElement Email;

    @FindBy(id = "contact_subject")
    WebElement subject;

    @FindBy(id = "contact_message")
    WebElement message;

    @FindBy(id = "message")
    WebElement submit;

    @FindBy(id = "mesgtab")
    WebElement succmsg;

    @FindBy(id = "contactNameErr")
    WebElement nameErr;

    @FindBy(id = "contactEmailErr")
    WebElement emailErr;

    @FindBy(id = "contactSubjectErr")
    WebElement SubErr;

    @FindBy(id = "contactMessageErr")
    WebElement MsgErr;

    public void fillingform(Map<String, String> data) {
        logger.info("Filling contact form with data: {}", data);
        utils.enterText(Name, data.get("Name"));
        utils.enterText(Email, data.get("Email"));
        utils.enterText(subject, data.get("Subject"));
        utils.enterText(message, data.get("Message"));
    }

    public void clicksubmit() {
        logger.info("Clicking on Send Message button");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300)");
        utils.clickElement(submit);
    }

    public void checkSuccessMessage() {
        logger.info("Checking for success message visibility");
        utils.waitForElementToBeVisible(succmsg);
    }

    public void leaveAllFieldsEmpty() {
        logger.info("Leaving all contact fields empty");
        utils.enterText(Name, "");
        utils.enterText(Email, "");
        utils.enterText(subject, "");
        utils.enterText(message, "");
    }

    public void errorMessageVisible() {
        WebElement[] errorElements = { nameErr, emailErr, SubErr, MsgErr };
        String[] fieldNames = { "Name", "Email", "Subject", "Message" };

        for (int i = 0; i < errorElements.length; i++) {
            WebElement err = errorElements[i];
            try {
                utils.waitForElementToBeVisible(err);
                boolean isDisplayed = err.isDisplayed();
                String msg = err.getText();

                logger.error("Validation for " + fieldNames[i] + ": " + msg);
                Assert.assertTrue(isDisplayed, fieldNames[i] + " error should be displayed");

            } catch (Exception e) {
                logger.error("Exception while checking error for: " + fieldNames[i], e);
                Assert.fail("Exception thrown for field: " + fieldNames[i]);
            }
        }
    }
}
