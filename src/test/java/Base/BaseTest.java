package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {

    private final String browserName = "chrome";

    protected static WebDriver driver;
    private final String APP_URL = "http://www.automationpractice.pl/index.php";
    private Logger log = LoggerFactory.getLogger(BaseTest.class);


    @BeforeEach
    void setup() {

        //1. open url http://www.automationpractice.pl/index.php

        driver = getDriver();
        driver.get(APP_URL);
        log.info("URL opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @AfterEach
    void teardown() {

        //10. zamykanie przegladarki

        driver.quit();
        log.info("Driver closed");

    }

    private WebDriver getDriver() {


        switch (this.browserName) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-search-engine-choice-screen");
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                log.info("Driver initialized properly");

            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                //firefoxOptions.addArguments("--start-maximized");
                //firefoxOptions.addArguments("--remote-allow-origins=*");
                //firefoxOptions.addArguments("--disable-search-engine-choice-screen");

            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                //edgeOptions.addArguments("--start-maximized");
                //edgeOptions.addArguments("--remote-allow-origins=*");
                //edgeOptions.addArguments("--disable-search-engine-choice-screen");

            }
            default -> throw new UnsupportedOperationException("Unsupported Browser Selected");


        }

        return driver;

    }
}