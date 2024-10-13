package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BaseTestWebTable {

        private final String browserName = "chrome";
        protected static WebDriver driver;
        private final String APP_URL = "http://cosmocode.io/automation-practice-webtable/";
        private Logger log = LoggerFactory.getLogger(Base.BaseTestWebTable.class);

        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class
        );


        @BeforeEach
        void setup() {

            driver = getDriver();
            driver.get(APP_URL);
            log.info("URL opened");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        }

        @AfterEach
        void teardown() {

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

                    default -> throw new UnsupportedOperationException("Unsupported Browser Selected");


                }

                return driver;

            }

        protected void clickCheckbox(WebElement element){
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            log.info("Element" + element.getText() + " is clickable");
        }

        protected WebElement findCheckbox(List<WebElement> list, int index){
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(list.get(index)));
            return checkbox;
        }


        }




