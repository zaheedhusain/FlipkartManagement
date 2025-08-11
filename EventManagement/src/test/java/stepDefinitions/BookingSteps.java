package stepDefinitions;

import java.util.List;
import java.util.Map;

import baseclass.LibraryClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Booking;
import utilities.ResuabilityClass;

public class BookingSteps {

    Booking bookingPage;
    ResuabilityClass utils;

    @Given("launch the browser")
    public void launch_the_browser() {
        LibraryClass.initializeBrowser();
    }

    @Given("navigate to booking form")
    public void navigate_to_booking_form() {
        LibraryClass.openApplication();
        bookingPage = new Booking(LibraryClass.driver); 
        utils = new ResuabilityClass(LibraryClass.driver);
    }

    @When("Fill valid inputs in fields")
    public void fill_valid_inputs_in_fields(DataTable dataTable) {
        List<Map<String, String>> formData = dataTable.asMaps(String.class, String.class);
        bookingPage.fillBookingForm(formData.get(0));
    }

    @When("click submit button")
    public void click_submit_button() {
        bookingPage.clickSubmit();
    }

    @Then("shows result table")
    public void shows_result_table() {
        bookingPage.verifyResultTableVisible();
    }

    @When("Leaves all required fields empty")
    public void leaves_all_required_fields_empty() {
        bookingPage.leaveRequiredFieldsEmpty();
    }

    @Then("display error message")
    public void display_error_message() {
        bookingPage.verifyValidationErrorsDisplayed();
    }

    @When("Enter invalid data in fields")
    public void enter_invalid_data_in_fields(DataTable dataTable) {
        List<Map<String, String>> invalidData = dataTable.asMaps(String.class, String.class);
        bookingPage.fillBookingForm(invalidData.get(0));
    }
}
