package helps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;

    private static void initializeChromeDriver() {
        System.setProperty("webdriver.chrom.driver", "src/main/resources/chromedriver.exe");
    }

    public static WebDriver getChromeDriver() {
        initializeChromeDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }


}
