package stepDefinitions;

import org.testng.Assert;

import baseclass.LibraryClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.navigation;
import utilities.ResuabilityClass;

public class NavigationSteps {
	navigation nav;
	ResuabilityClass util;
	String currentMenu = "";

	@Given("navigate to menu")
	public void navigate_to_menu() {
	    LibraryClass.openApplication();
	    nav = new navigation(LibraryClass.driver);
	    util = new ResuabilityClass(LibraryClass.driver);
	}

	@When("click {string} option")
	public void click_menu_option(String menu) {
	    currentMenu = menu.trim().toUpperCase();
	    switch (currentMenu) {
	        case "HOME":
	            nav.clickHome();
	            util.takeScreenshot(LibraryClass.driver, menu);
	            break;
	        case "ABOUT US":
	            nav.clickAbout();
	            util.takeScreenshot(LibraryClass.driver, menu);
	            break;
	        case "BOOKING":
	            nav.clickBooking();
	            util.takeScreenshot(LibraryClass.driver, menu);
	            break;
	        case "CONTACT US":
	            nav.clickContactus();
	            util.takeScreenshot(LibraryClass.driver, menu);
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid menu option: " + menu);
	    }
	}

	@Then("navigates to according page")
	public void navigates_to_according_page() {
	    boolean isVisible = false;

	    switch (currentMenu) {
	        case "HOME":
	            isVisible = nav.isHomeVisible();
	            break;
	        case "ABOUT US":
	            isVisible = nav.isAboutVisible();
	            break;
	        case "BOOKING":
	            isVisible = nav.isBookingVisible();
	            break;
	        case "CONTACT US":
	            isVisible = nav.isContactVisible();
	            break;
	        default:
	            throw new IllegalStateException("Unexpected value for currentMenu: " + currentMenu);
	    }

	    Assert.assertTrue(isVisible, currentMenu + " section not visible.");
	}

}
