package testRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.Plugin;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestStepFinished;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.plugin.event.Status;


import org.openqa.selenium.WebDriver;

public class CucumberExtentReportPlugin implements Plugin, ConcurrentEventListener {
	private WebDriver driver;
	 private static ExtentReports extent;
	    private static ExtentTest scenarioTest;
 
	    public void setEventPublisher(EventPublisher publisher) {
	        publisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
	        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
	        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
	    }
 
	    public CucumberExtentReportPlugin() {
	        ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentReports.html");
	        extent = new ExtentReports();
	        extent.attachReporter(reporter);
	    }
 
	    private void onTestCaseStarted(TestCaseStarted event) {
	        scenarioTest = extent.createTest(event.getTestCase().getName());
	    }
 
	    private void onTestStepFinished(TestStepFinished event) {
	        if (event.getResult().getStatus().is(Status.PASSED)) {
	            scenarioTest.pass(event.getTestStep().getCodeLocation());
	        } else if (event.getResult().getStatus().is(Status.FAILED)) {
	            scenarioTest.fail(event.getResult().getError().getMessage());
	        } else if (event.getResult().getStatus().is(Status.SKIPPED)) {
	            scenarioTest.skip("Step skipped: " + event.getTestStep().getCodeLocation());
	        }
	    }
 
	    private void onTestCaseFinished(TestCaseFinished event) {
	        if (event.getResult().getStatus().is(Status.FAILED)) {
	            scenarioTest.fail("Scenario failed: " + event.getResult().getError().getMessage());
	        }
	        extent.flush();
	    }
}