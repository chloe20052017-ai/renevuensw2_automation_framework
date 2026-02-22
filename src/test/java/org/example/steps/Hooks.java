package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Hooks {
    private static WebDriver driver;

    @Before("@ui")
    public void setUp() {
        if (driver == null) {
            boolean isCi = "true".equalsIgnoreCase(System.getenv("CI"));
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (isCi) {
                options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            if (!isCi) {
                driver.manage().window().maximize();
            }
        }
    }

    @After("@ui")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    static WebDriver getDriver() {
        return driver;
    }
}
