package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/Features",
    glue = "stepDefinitions",
    plugin = {"pretty", "html:target/cucumber-reports","testRunner.CucumberExtentReportPlugin"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
