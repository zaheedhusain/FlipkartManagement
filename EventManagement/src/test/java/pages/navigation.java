package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ResuabilityClass;

public class navigation {
	WebDriver driver;
	ResuabilityClass utils;
	
	public navigation(WebDriver driver){
		this.driver = driver;
        PageFactory.initElements(driver, this);
        utils = new ResuabilityClass(driver);
	}
	
	@FindBy(xpath="//a[text()='Home']")
	WebElement home;
	
	@FindBy(xpath="//a[text()='About Us']")
	WebElement about;
	
	@FindBy(xpath="//a[text()='Booking']")
	WebElement booking;
	
	@FindBy(xpath="//a[text()='Contact Us']")
	WebElement contact;
	
	@FindBy(id="tm-section-1")
	WebElement homeSection;

	@FindBy(id="tm-section-3")
	WebElement aboutSection;

	@FindBy(id="tm-section-4")
	WebElement bookingSection;

	@FindBy(id="tm-section-6")
	WebElement contactSection;
	

	public void clickHome() {
		utils.clickElement(home);		
	}
	
	public void clickAbout() {
		utils.clickElement(about);
		
	}
	
	public void clickBooking() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0, -230)");
		utils.clickElement(booking);
		
	}
	
	public void clickContactus() {
		utils.clickElement(contact);
		
	}

	public boolean isHomeVisible() {
	    return homeSection.isDisplayed();
	}

	public boolean isAboutVisible() {
	    return aboutSection.isDisplayed();
	}

	public boolean isBookingVisible() {
	    return bookingSection.isDisplayed();
	}

	public boolean isContactVisible() {
	    return contactSection.isDisplayed();
	}

}
