package stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import baseclass.LibraryClass;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {

	
    @After
    public void close_browser(Scenario scenario) throws IOException, InterruptedException {
        if (LibraryClass.driver != null) {
            // Take screenshot
        	Thread.sleep(4000); 
        	
            	TakesScreenshot ts = (TakesScreenshot) LibraryClass.driver;
                File src = ts.getScreenshotAs(OutputType.FILE);

                // Format scenario name to make it filesystem-safe
                String name = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");

                // Extract feature name from the scenario URI (works in Cucumber 7+)
                String featurePath = scenario.getUri().toString();
                String featureName = featurePath.substring(featurePath.lastIndexOf("/") + 1).replace(".feature", "");

                // Construct full path
                String destPath = "src/test/resources/screenshots/" + featureName + "_" + name + ".png";
                File dest = new File(destPath);
                FileUtils.copyFile(src, dest);

                System.out.println("📸 Screenshot saved: " + destPath);
            

            // Close the browser
            LibraryClass.driver.quit();
        }
    }
}
