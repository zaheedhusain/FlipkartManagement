package baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LibraryClass {
	public static WebDriver driver;
    protected static Properties config = new Properties();
    private static final Logger logger = LogManager.getLogger(LibraryClass.class);
 
    // Load config.properties
    public static void loadConfig() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/Configuration.Properties/Config.property");
            config.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load configuration: " + e.getMessage());
        }
    }
 
    // Initialize browser based on config
    public static void initializeBrowser() {
        loadConfig();
        String browser = config.getProperty("browser", "chrome");
      //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        int implicitWait = Integer.parseInt(config.getProperty("implicitWait", "10"));
 
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            logger.info("Chrome driver setup successful");

        }
        else if(browser.equalsIgnoreCase("firefox")){
        	 WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();
        }

        // You can add other browsers like Firefox, Edge etc.
 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
    }
 
    // Open application using config URL
    public static void openApplication() {
        String url = config.getProperty("url");
        driver.get(url);
        logger.info("Navigated to url");
    }
 
    // Close the browser
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            logger.info("driver is closed");

        }
    }
}

