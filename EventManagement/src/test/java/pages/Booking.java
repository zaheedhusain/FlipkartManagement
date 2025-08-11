package pages;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.ResuabilityClass;

public class Booking {

    WebDriver driver;
    ResuabilityClass utils;
    private static final Logger logger = LogManager.getLogger(Booking.class);

    public Booking(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utils = new ResuabilityClass(driver);
        logger.info("Booking page initialized");
    }

    // Input Fields
    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "phoneNo")
    WebElement phone;

    @FindBy(id = "emaiId")
    WebElement email;

    @FindBy(id = "eventType")
    WebElement eventTypeDropdown;

    @FindBy(id = "eventDate")
    WebElement eventDate;

    @FindBy(id = "eventTime")
    WebElement eventTime;

    @FindBy(id = "guestCount")
    WebElement guestCount;

    @FindBy(xpath = "//input[@name='radiobutton']")
    List<WebElement> cateringOptions;

    @FindBy(id = "address")
    WebElement address;

    @FindBy(id = "city")
    WebElement cityDropdown;

    @FindBy(id = "pincode")
    WebElement pincode;

    @FindBy(id = "eventDetail")
    WebElement eventDetails;

    // Buttons
    @FindBy(id = "book-now")
    WebElement submitBtn;

    @FindBy(id = "reset-now")
    WebElement resetBtn;

    // Result and error
    @FindBy(id = "booking-content-area")
    WebElement resultTable;

    @FindBy(id = "fnameErr")
    WebElement fnameErr;

    @FindBy(id = "lnameErr")
    WebElement lnameErr;

    @FindBy(id = "phoneErr")
    WebElement phoneErr;

    @FindBy(id = "emailErr")
    WebElement emailErr;

    @FindBy(id = "eventTypeErr")
    WebElement eventTypeErr;

    @FindBy(id = "eventDateErr")
    WebElement eventDateErr;

    @FindBy(id = "eventTimeErr")
    WebElement eventTimeErr;

    @FindBy(id = "guestCountErr")
    WebElement guestCountErr;

    @FindBy(id = "radioBtnErr")
    WebElement radioBtnErr;

    @FindBy(id = "addressErr")
    WebElement addressErr;

    @FindBy(id = "cityErr")
    WebElement cityErr;

    @FindBy(id = "pincodeErr")
    WebElement pincodeErr;

    public void fillBookingForm(Map<String, String> data) {
        logger.info("Filling booking form with data: {}", data);
        utils.enterText(firstName, data.get("First name"));
        utils.enterText(lastName, data.get("Last name"));
        utils.enterText(phone, data.get("Phone Number"));
        utils.enterText(email, data.get("Email ID"));
        try {
            utils.selectDropdownByVisibleText(eventTypeDropdown, data.get("Event Type"));
        } catch (NoSuchElementException e) {
            logger.warn("Event Type option not found: {}", data.get("Event Type"));
        }
        utils.enterText(eventDate, data.get("Event Date"));
        utils.enterText(eventTime, data.get("Event Time"));
        utils.enterText(guestCount, data.get("Guest Count"));
        utils.selectRadioButtonByValue(cateringOptions, data.get("Catering Service"));
        utils.enterText(address, data.get("Address"));
        try {
            utils.selectDropdownByVisibleText(cityDropdown, data.get("City"));
        } catch (NoSuchElementException e) {
            logger.warn("City option not found: {}", data.get("City"));
        }
        utils.enterText(pincode, data.get("Pincode"));
        utils.enterText(eventDetails, data.get("Event Details"));
    }

    public void clickSubmit() {
        logger.info("Clicking on Submit button");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -230)");
        utils.clickElement(submitBtn);
    }

    public void clickReset() {
        logger.info("Clicking on Reset button");
        utils.clickElement(resetBtn);
    }

    public void verifyResultTableVisible() {
        logger.info("Verifying booking result table is visible");
        utils.waitForElementToBeVisible(resultTable);
    }

    public void leaveRequiredFieldsEmpty() {
        logger.info("Leaving required fields empty");
        // Intentionally left blank
    }

    public void verifyValidationErrorsDisplayed() {
        logger.info("Verifying validation errors are displayed");
        Assert.assertTrue(eventTypeErr.isDisplayed(), "Event Type error should be displayed");
    }
}
