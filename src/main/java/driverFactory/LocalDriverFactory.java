package driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;

public class LocalDriverFactory {

    private static WebDriver driver;
    private static String driverName;

    public static WebDriver getDriver() {
        driverName = System.getProperty("driver") == null ? "chrome" : System.getProperty("driver");
        if (driver == null) {
            switch (driverName) {
                case ("chrome"):
                    WebDriverManager.chromedriver().setup();
                    org.openqa.selenium.chrome.ChromeOptions options= new org.openqa.selenium.chrome.ChromeOptions();
                    options.addArguments(Arrays.asList("--allow-running-insecure-content", "--ignore-certificate-errors",
                            "--disable-popup-blocking", "--disable-dev-shm-usage", "--no-sandbox", "--disable-gpu",
                            "--test-type"));
                    driver = new ChromeDriver(options);
                    break;
                case ("firefox"):
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
            driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
            driver.close();
            driver = null;
    }
}
