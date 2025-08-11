package stepDefinitions;

import java.util.Map;

import baseclass.LibraryClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.contactus;
import utilities.ExcelReader;
import utilities.ResuabilityClass;

public class ContactusSteps {
	
	contactus contact;
	ResuabilityClass utils;
	
	
	@Given("navigate to contact form URL")
	public void navigate_to_contact_form_url() {
		
		LibraryClass.openApplication();
		contact = new contactus(LibraryClass.driver); 
        utils = new ResuabilityClass(LibraryClass.driver);
	}

	@When("fill valid data in fields")
	public void fill_valid_data_in_fields() {
	    String excelPath = "src/test/resources/ValidTestDataContactUs.xlsx";  // make sure this file exists
	    ExcelReader reader = new ExcelReader(excelPath, "Sheet1");
	    Map<String, String> data = reader.getRowData(1);  // assuming row 1 contains your test data
	    contact.fillingform(data);
	}


	@When("click send message button")
	public void click_send_message_button() {
	    contact.clicksubmit();
	}

	@Then("success message should be visible")
	public void success_message_should_be_visible() {
	    contact.checkSuccessMessage();
	}

	@When("leave all fields empty")
	public void leave_all_fields_empty() {
	    contact.leaveAllFieldsEmpty();
	}

	@Then("error message should be visible")
	public void error_message_should_be_visible() {
	    contact.errorMessageVisible();
	}



}
