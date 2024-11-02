package testCases;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

   public static WebDriver driver;
   public Properties properties;
   public Logger logger;


    @BeforeSuite
    @Parameters({"os", "browser"})
    public void setUp(@Optional("Windows")String os, @Optional("chrome") String br) throws IOException {

        //Loading config.properties file
        FileReader file = new FileReader(".//src/test/resources/config.properties");
        properties = new Properties();
        properties.load(file);

        //Initialize logger
        logger = LogManager.getLogger(this.getClass());


        switch(br.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser name");
                return;
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
}
